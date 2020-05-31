package entities;

import com.crypticmushroom.candycraft.items.ColoredGummyItem;
import com.crypticmushroom.candycraft.registry.CCEntitys;
import com.crypticmushroom.candycraft.registry.CCItems;
import com.crypticmushroom.candycraft.utils.CandyCraftUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class GummyBunnyEntity extends AnimalEntity {
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;

    private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(GummyBunnyEntity.class, DataSerializers.VARINT);

    public GummyBunnyEntity(EntityType<? extends GummyBunnyEntity> type, World worldIn) {
        super(type, worldIn);
        this.jumpController = new GummyBunnyEntity.JumpHelperController(this);
        this.moveController = new GummyBunnyEntity.MoveHelperController(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.fromItems(Items.CARROT, Items.GOLDEN_CARROT, Blocks.DANDELION), false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, WolfEntity.class, 10.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, MonsterEntity.class, 4.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(12, new LookAtGoal(this, GummyBunnyEntity.class, 6.0F));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(COLOR, 0x5f5f5f);
    }

    public void setColor(int color) {
        this.dataManager.set(COLOR, color);
    }

    public int getColor() {
        return this.dataManager.get(COLOR);
    }

    @Override
    protected float getJumpUpwardsMotion() {
        if (!this.collidedHorizontally && (!this.moveController.isUpdating() || !(this.moveController.getY() > this.getPosY() + 0.5D))) {
            Path path = this.navigator.getPath();
            if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                Vec3d vec3d = path.getPosition(this);
                if (vec3d.y > this.getPosY() + 0.5D) {
                    return 0.5F;
                }
            }

            return this.moveController.getSpeed() <= 0.6D ? 0.2F : 0.3F;
        } else {
            return 0.5F;
        }

    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    @Override
    protected void jump() {
        super.jump();
        double d0 = this.moveController.getSpeed();
        if (d0 > 0.0D) {
            double d1 = horizontalMag(this.getMotion());
            if (d1 < 0.01D) {
                this.moveRelative(0.1F, new Vec3d(0.0D, 0.0D, 1.0D));
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 1);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getJumpCompletion(float p_175521_1_) {
        return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + p_175521_1_) / (float) this.jumpDuration;
    }

    public void setMovementSpeed(double newSpeed) {
        this.getNavigator().setSpeed(newSpeed);
        this.moveController.setMoveTo(this.moveController.getX(), this.moveController.getY(), this.moveController.getZ(), newSpeed);
    }

    @Override
    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }

    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    @Override
    public void updateAITasks() {
        if (this.currentMoveTypeDuration > 0) {
            --this.currentMoveTypeDuration;
        }

        if (this.onGround) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            GummyBunnyEntity.JumpHelperController rabbitentity$jumphelpercontroller = (GummyBunnyEntity.JumpHelperController) this.jumpController;
            if (!rabbitentity$jumphelpercontroller.getIsJumping()) {
                if (this.moveController.isUpdating() && this.currentMoveTypeDuration == 0) {
                    Path path = this.navigator.getPath();
                    Vec3d vec3d = new Vec3d(this.moveController.getX(), this.moveController.getY(), this.moveController.getZ());
                    if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                        vec3d = path.getPosition(this);
                    }

                    this.calculateRotationYaw(vec3d.x, vec3d.z);
                    this.startJumping();
                }
            } else if (!rabbitentity$jumphelpercontroller.canJump()) {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;
    }

    /**
     * Attempts to create sprinting particles if the entity is sprinting and not in water.
     */
    public void spawnRunningParticles() {
    }

    private void calculateRotationYaw(double x, double z) {
        this.rotationYaw = (float) (MathHelper.atan2(z - this.getPosZ(), x - this.getPosX()) * (double) (180F / (float) Math.PI)) - 90.0F;
    }

    private void enableJumpControl() {
        ((GummyBunnyEntity.JumpHelperController) this.jumpController).setCanJump(true);
    }

    private void disableJumpControl() {
        ((GummyBunnyEntity.JumpHelperController) this.jumpController).setCanJump(false);
    }

    private void updateMoveTypeDuration() {
        if (this.moveController.getSpeed() < 2.2D) {
            this.currentMoveTypeDuration = 10;
        } else {
            this.currentMoveTypeDuration = 1;
        }

    }

    private void checkLandingDelay() {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void livingTick() {
        super.livingTick();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }

    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.3F);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Color", this.getColor());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);

        this.setColor(compound.getInt("Color"));
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_RABBIT_JUMP;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_RABBIT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_RABBIT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_RABBIT_DEATH;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return this.isInvulnerableTo(source) ? false : super.attackEntityFrom(source, amount);
    }

    private boolean isRabbitBreedingItem(Item itemIn) {
        return itemIn == Items.CARROT || itemIn == Items.GOLDEN_CARROT || itemIn == Blocks.DANDELION.asItem();
    }

    @Override
    public GummyBunnyEntity createChild(AgeableEntity ageable) {
        GummyBunnyEntity rabbitentity = CCEntitys.GUMMY_BUNNY.create(this.world);

        int i;

        if (ageable instanceof GummyBunnyEntity && this.rand.nextBoolean()) {
            i = ((GummyBunnyEntity) ageable).getColor();
        } else {
            i = this.getColor();
        }

        rabbitentity.setColor(i);
        return rabbitentity;
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i = this.getRandomGummyBunnyType(worldIn);
        if (spawnDataIn instanceof GummyBunnyEntity.GummyBunnyData) {
            i = ((GummyBunnyEntity.GummyBunnyData) spawnDataIn).typeData;
        } else {
            spawnDataIn = new GummyBunnyEntity.GummyBunnyData(i);
        }

        this.setColor(i);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        Entity entity = source.getTrueSource();
        if (entity != this && !this.isChild() && world.getRandom().nextFloat() < 0.175F + looting * 0.01F) {
            ItemStack stack = new ItemStack(CCItems.COLORED_GUMMY, 1 + world.getRandom().nextInt(1 + looting));
            ColoredGummyItem gummyItem = (ColoredGummyItem) stack.getItem();
            gummyItem.setColor(stack, getColor());
            this.entityDropItem(stack);
        }
    }

    private int getRandomGummyBunnyType(IWorld world) {
        float i = 0.5F + world.getRandom().nextFloat() * 0.5F;
        float i2 = 0.5F + world.getRandom().nextFloat() * 0.5F;
        float i3 = 0.5F + world.getRandom().nextFloat() * 0.5F;
        return CandyCraftUtil.getColorCode(i, i2, i3);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return this.isRabbitBreedingItem(stack.getItem());
    }

    public static class GummyBunnyData extends AgeableEntity.AgeableData {
        public final int typeData;

        public GummyBunnyData(int type) {
            this.typeData = type;
            this.func_226258_a_(1.0F);
        }
    }

    public class JumpHelperController extends JumpController {
        private final GummyBunnyEntity rabbit;
        private boolean canJump;

        public JumpHelperController(GummyBunnyEntity rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public boolean getIsJumping() {
            return this.isJumping;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn) {
            this.canJump = canJumpIn;
        }

        /**
         * Called to actually make the entity jump if isJumping is true.
         */
        public void tick() {
            if (this.isJumping) {
                this.rabbit.startJumping();
                this.isJumping = false;
            }

        }
    }

    static class MoveHelperController extends MovementController {
        private final GummyBunnyEntity rabbit;
        private double nextJumpSpeed;

        public MoveHelperController(GummyBunnyEntity rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public void tick() {
            if (this.rabbit.onGround && !this.rabbit.isJumping && !((GummyBunnyEntity.JumpHelperController) this.rabbit.jumpController).getIsJumping()) {
                this.rabbit.setMovementSpeed(0.0D);
            } else if (this.isUpdating()) {
                this.rabbit.setMovementSpeed(this.nextJumpSpeed);
            }

            super.tick();
        }

        /**
         * Sets the speed and location to move to
         */
        public void setMoveTo(double x, double y, double z, double speedIn) {
            if (this.rabbit.isInWater()) {
                speedIn = 1.5D;
            }

            super.setMoveTo(x, y, z, speedIn);
            if (speedIn > 0.0D) {
                this.nextJumpSpeed = speedIn;
            }

        }
    }
}
