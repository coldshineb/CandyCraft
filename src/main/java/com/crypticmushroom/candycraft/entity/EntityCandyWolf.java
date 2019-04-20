package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.blocks.BlockCandyLeave;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.CCAdvancements;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityCandyWolf extends EntityWolf {
    private static final DataParameter<Integer> FUR_TIME = EntityDataManager.createKey(EntityCandyWolf.class, DataSerializers.VARINT);

    public EntityCandyWolf(World par1World) {
        super(par1World);
    }

    public int getFurTime() {
        return dataManager.get(FUR_TIME);
    }

    public void setFurTime(int time) {
        dataManager.set(FUR_TIME, time);
    }

    @Override
    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == CCItems.candyCane;
    }

    @Override
    public EntityCandyWolf createChild(EntityAgeable par1EntityAgeable) {
        EntityCandyWolf stack = new EntityCandyWolf(world);
        UUID var3 = getOwnerId();

        if (var3 != null) {
            stack.setFurTime(world.rand.nextInt(6000) + 5000);
            stack.setOwnerId(var3);
            stack.setTamed(true);
        }

        return stack;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!world.isRemote) {
            if (isTamed()) {
                if (getFurTime() > 0) {
                    boolean tree = false;
                    for (int i = 0; i < 4; i++) {
                        IBlockState state = world.getBlockState(new BlockPos((int) posX, (int) posY + i, (int) posZ));
                        IBlockState metadata = state.getBlock().getDefaultState();
                        if (world.getBlockState(new BlockPos((int) posX, (int) posY + i, (int) posZ)).getBlock() instanceof BlockCandyLeave && metadata == CCBlocks.candyLeaveDark.getDefaultState()) {
                            tree = true;
                        }
                    }
                    int counter = tree ? 2 : 1;
                    setFurTime(getFurTime() - counter);
                }
            }
        } else {
            if (isTamed() && getFurTime() <= 0) {
                if (rand.nextInt(30) == 0) {
                    for (int var1 = 0; var1 < 2; ++var1) {
                        world.spawnParticle(EnumParticleTypes.SPELL_MOB, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.8D, 0.3D, 0.0D);
                    }
                }
            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(FUR_TIME, 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Caramel", getFurTime());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setFurTime(par1NBTTagCompound.getInteger("Caramel"));
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItemMainhand();

        if (isTamed()) {
            if (!stack.isEmpty()) {
                if (stack.getItem() == Items.DYE) {
                    EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());

                    if (enumdyecolor != getCollarColor()) {
                        setCollarColor(enumdyecolor);

                        if (!player.capabilities.isCreativeMode) {
                            stack.shrink(1);
                        }

                        return true;
                    }
                } else if (stack.getItem() == CCItems.lollipop) {
                    CCAdvancements.HEAL_CANDY_WOLF.trigger((EntityPlayerMP)player);
                    return false;
                } else if (stack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild()) {
                    stack.shrink(1);

                    if (stack.isEmpty()) {
                        player.setHeldItem(hand, CCItems.caramelBucket);
                    } else if (!player.inventory.addItemStackToInventory(CCItems.caramelBucket)) {
                        player.dropItem(CCItems.caramelBucket, false);
                    }

                    return true;
                } else if (isBreedingItem(stack) && getGrowingAge() == 0 && !isInLove()) {
                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }

                    heal(4);
                    setInLove(player);
                    setAttackTarget(null);

                    for (int var3 = 0; var3 < 7; ++var3) {
                        double var4 = rand.nextGaussian() * 0.02D;
                        double var6 = rand.nextGaussian() * 0.02D;
                        double var8 = rand.nextGaussian() * 0.02D;
                        world.spawnParticle(EnumParticleTypes.HEART, posX + rand.nextFloat() * width * 2.0F - width, posY + 0.5D + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0F - width, var4, var6, var8);
                    }

                    return true;
                }

            }
            if ((isOwner(player)) && !world.isRemote && !isBreedingItem(stack)) {
                aiSit.setSitting(!isSitting());
                isJumping = false;
                navigator.clearPath();
                setAttackTarget(null);
                return true;
            }
        } else {
            if (stack.getItem() == CCItems.candyCane && !isAngry()) {
                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }

                if (!world.isRemote) {
                    if (rand.nextInt(3) == 0) {
                        setTamed(true);
                        setFurTime(world.rand.nextInt(12000) + 10000);
                        navigator.clearPath();
                        setAttackTarget(null);
                        aiSit.setSitting(true);
                        setHealth(20);
                        setOwnerId(player.getUniqueID());
                        CCAdvancements.TAME_CANDY_WOLF.trigger((EntityPlayerMP)player);
                        playTameEffect(true);
                        world.setEntityState(this, (byte) 7);
                    } else {
                        playTameEffect(false);
                        world.setEntityState(this, (byte) 6);
                    }
                }

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!isTamed()) {
            return false;
        } else if (!(otherAnimal instanceof EntityCandyWolf)) {
            return false;
        } else {
            EntityCandyWolf entitywolf = (EntityCandyWolf) otherAnimal;
            return entitywolf.isTamed() && (!entitywolf.isSitting() && (isInLove() && entitywolf.isInLove()));
        }
    }
}
