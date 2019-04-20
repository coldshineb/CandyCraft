package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMageSuguard extends EntitySuguard {
    private boolean wait = true;
    private boolean spawned = false;
    private int countDown = 0;

    public EntityMageSuguard(World par1World) {
        super(par1World);
        isImmuneToFire = true;
        isAngry = true;
    }

    private boolean isWaiting() {
        return wait;
    }

    private void setWaiting(boolean waiting) {
        wait = waiting;
    }

    public boolean isSpawned() {
        return spawned;
    }

    private void setSpawned(boolean spawn) {
        spawned = spawn;
    }

    public int getRandomPotion() {
        StringBuilder finalResult = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String id = String.valueOf(Potion.getPotionById(rand.nextInt(22) + 1));
            finalResult.append(id.length() == 1 ? "0" + id : id);
        }
        return Integer.valueOf(finalResult.toString());
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (super.attackEntityAsMob(par1Entity)) {
            par1Entity.setFire(6);
            return true;
        }
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public void onLivingUpdate() {
        if (isWaiting()) {
            getNavigator().clearPath();

            motionX = 0;
            motionZ = 0;

            posX = lastTickPosX;
            posZ = lastTickPosZ;
        }
        if (!world.isRemote && countDown > 0) {
            countDown--;
            if (countDown <= 0) {
                setSpawned(false);
            }
        }
        if (!world.isRemote && EntityUtil.getClosestVulnerablePlayerToEntity(world, this, 8) != null) {
            setWaiting(false);

            if (rand.nextFloat() < 0.05F) {
                world.setBlockState(new BlockPos(this), Blocks.FIRE.getDefaultState());
            }

            if (!isSpawned()) {
                for (int i = 0; i <= 8; i++) {
                    double d1 = -MathHelper.sin(i / 3.75F * (float) Math.PI) * 2.5F + posX;
                    double d2 = MathHelper.cos(i / 3.75F * (float) Math.PI) * 2.5F + posZ;
                    double d3 = posY + 2;

                    EntitySuguard suguard = new EntitySuguard(world);
                    suguard.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(CCItems.dynamite));
                    suguard.isAngry = true;
                    suguard.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
                    suguard.setHealth(10);

                    if (world.isAirBlock(new BlockPos((int) d1, (int) d3, (int) d2))) {
                        suguard.setPosition(d1, d3, d2);
                    } else {
                        suguard.setPosition(posX, posY, posZ);
                    }
                    world.spawnEntity(suguard);
                }
                setSpawned(true);
                countDown = 2400;
            }
        } else {
            setWaiting(true);
        }
        super.onLivingUpdate();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Waiting", isWaiting());
        par1NBTTagCompound.setInteger("countDown", countDown);
        par1NBTTagCompound.setBoolean("Spawned", isSpawned());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setWaiting(par1NBTTagCompound.getBoolean("Waiting"));
        countDown = (par1NBTTagCompound.getInteger("countDown"));
        setSpawned(par1NBTTagCompound.getBoolean("Spawned"));
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        setHeldItem(EnumHand.MAIN_HAND, null);
        ItemStack stack = new ItemStack(CCItems.sugarPill);
        int i = getRandomPotion();
        stack.setItemDamage(i);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger("MetaSystem", i);
        entityDropItem(stack, 0.0F);
    }
}
