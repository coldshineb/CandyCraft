package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.entity.ai.EntityAIAvoidPlayerGinger;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGingerBreadMan extends EntityVillager implements IMerchant, INpc {
    private static final DataParameter<Integer> PROFESSION = EntityDataManager.createKey(EntityVillager.class, DataSerializers.VARINT);
    public final String[] jobs = {"Blacksmith", "Farmer", "Citizen", "Elder"};
    EntityAIAvoidPlayerGinger ai = new EntityAIAvoidPlayerGinger<>(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D);
    private MerchantRecipeList buyingList = new MerchantRecipeList();

    public EntityGingerBreadMan(World par1World) {
        super(par1World);
        ((PathNavigateGround) getNavigator()).setEnterDoors(true);
        tasks.taskEntries.clear();
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAITradePlayer(this));
        tasks.addTask(1, new EntityAILookAtTradePlayer(this));
        tasks.addTask(6, new EntityAIVillagerMate(this));
        tasks.addTask(8, new EntityAIPlay(this, 0.32D));
        tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        tasks.addTask(9, new EntityAIWatchClosest2(this, EntityGingerBreadMan.class, 5.0F, 0.02F));
        tasks.addTask(2, new EntityAIWander(this, 0.25F));
        tasks.addTask(3, new EntityAILookIdle(this));
        tasks.addTask(4, ai);
        setSize(0.3F, 0.9F);
        setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);

        if (buyingList != null) {
            par1NBTTagCompound.setTag("Offers", buyingList.getRecipiesAsTags());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("Offers", 10)) {
            NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("Offers");
            buyingList = new MerchantRecipeList(nbttagcompound1);
        }
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        if (getProfession() != 3) {
            if (getMoveHelper().isUpdating()) {
                double d0 = getMoveHelper().getSpeed();

                if (d0 == 0.6D) {
                    setSneaking(true);
                    setFlag(3, false);
                } else if (d0 == 1.33D) {
                    setSneaking(false);
                    setFlag(3, true);
                } else {
                    setSneaking(false);
                    setFlag(3, false);
                }
            } else {
                setSneaking(false);
                setFlag(3, false);
            }
        }
    }

    @Override
    //TODO: Null at PROFESSION
    public void setProfession(int professionId) {
        if (professionId == 3) {
            tasks.removeTask(ai);
            getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.83000000298023224D);
            buyingList.clear();
            buyingList.add(new MerchantRecipe(new ItemStack(CCItems.pez, 5), new ItemStack(CCItems.whiteKey)));
            buyingList.add(new MerchantRecipe(new ItemStack(CCItems.pez, 10), new ItemStack(CCItems.skyEmblem)));
            buyingList.add(new MerchantRecipe(new ItemStack(CCItems.pez, 20), new ItemStack(CCItems.CD3)));
        }
        dataManager.set(PROFESSION, professionId);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", rand.nextGaussian() * 0.05D, 1));
        int i = rand.nextInt(jobs.length - 1);
        this.setProfession(i);

        if (getProfession() == 0) {
            if (rand.nextInt(3) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 4), new ItemStack(CCItems.honeyArrow, rand.nextInt(4) + 1)));
            }
            if (rand.nextInt(3) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(40) + 4), new ItemStack(CCItems.honeyBolt, rand.nextInt(4) + 1)));
            }
            if (rand.nextInt(5) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, 64), new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 32), new ItemStack(CCItems.caramelBow, 1)));
            }
            if (rand.nextInt(4) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 50), new ItemStack(CCItems.honeySword, 1)));
            }
            if (rand.nextInt(3) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 40), new ItemStack(CCItems.honeyHelmet, 1)));
            }
            if (rand.nextInt(6) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(8) + 55), new ItemStack(CCItems.honeyPlate, 1)));
            }
            if (rand.nextInt(4) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(8) + 50), new ItemStack(CCItems.honeyLeggings, 1)));
            }
            if (rand.nextInt(3) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(15) + 35), new ItemStack(CCItems.honeyBoots, 1)));
            }
            if (rand.nextInt(3) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 15), new ItemStack(CCItems.honeycomb, 1)));
            }
            if (rand.nextInt(3) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 35), new ItemStack(CCItems.pez, 1)));
            }
            if (buyingList.size() == 0) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 4), new ItemStack(CCItems.honeyArrow, rand.nextInt(4) + 1)));
            }
        } else if (getProfession() == 1) {
            if (rand.nextInt(3) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 40), new ItemStack(CCItems.fork, rand.nextInt(2) + 1)));
            }
            if (rand.nextInt(3) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 24), new ItemStack(CCItems.lollipopSeeds, rand.nextInt(2) + 1)));
            }
            buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 2), new ItemStack(CCItems.dragibus, rand.nextInt(2) + 1)));
            if (rand.nextInt(3) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 4), new ItemStack(CCBlocks.pinkSeaweed, rand.nextInt(4) + 1)));
            }
            if (rand.nextInt(5) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(12) + 4), new ItemStack(CCBlocks.greenSeaweed, rand.nextInt(6) + 1)));
            }
            if (rand.nextInt(5) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(12) + 4), new ItemStack(CCBlocks.bananaSeaweed, rand.nextInt(6) + 1)));
            }
            if (rand.nextInt(8) == 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 15), new ItemStack(CCBlocks.lollipopBlock, rand.nextInt(1) + 1)));
            }
            if (rand.nextInt(3) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 8), new ItemStack(CCItems.lollipop, rand.nextInt(8) + 3)));
            }
            if (buyingList.size() == 0) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, rand.nextInt(10) + 24), new ItemStack(CCItems.lollipopSeeds, rand.nextInt(2) + 1)));
            }
        } else if (getProfession() == 2) {
            if (rand.nextInt(4) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.waffle, rand.nextInt(6) + 5), new ItemStack(CCItems.chocolateCoin, rand.nextInt(15) + 5)));
            }
            if (rand.nextInt(3) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.candyCane, rand.nextInt(6) + 5), new ItemStack(CCItems.chocolateCoin, rand.nextInt(15) + 5)));
            }
            if (rand.nextInt(3) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.licorice, rand.nextInt(6) + 5), new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 5)));
            }
            if (rand.nextInt(5) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.honeyShard, rand.nextInt(15) + 30), new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 5)));
            }
            if (rand.nextInt(5) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCBlocks.trampojelly, rand.nextInt(5) + 10), new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 5)));
            }
            if (rand.nextInt(5) < 1) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCBlocks.caramelBlock, rand.nextInt(5) + 10), new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 5)));
            }
            if (rand.nextInt(5) < 3) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.honeyArrow, rand.nextInt(5) + 20), new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 5)));
            }
            if (rand.nextInt(5) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCBlocks.marshmallowLog, rand.nextInt(5) + 20), new ItemStack(CCItems.chocolateCoin, rand.nextInt(20) + 5)));
            }
            if (rand.nextInt(10) < 2) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.chocolateCoin, 64), new ItemStack(CCItems.chocolateCoin, 64), new ItemStack(CCItems.gingerbreadEmblem, 1)));
            }
            if (buyingList.size() == 0) {
                buyingList.add(new MerchantRecipe(new ItemStack(CCItems.candyCane, rand.nextInt(6) + 5), new ItemStack(CCItems.chocolateCoin, rand.nextInt(15) + 5)));
            }
        }
        return par1EntityLivingData;
    }

    @Override
    public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer) {
        return buyingList;
    }

    @Override
    public void setRecipes(MerchantRecipeList merchantrecipelist) {
    }

    @Override
    public void useRecipe(MerchantRecipe merchantrecipe) {
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.33000000298023224D);
    }

    @Override
    public EntityVillager createChild(EntityAgeable par1EntityAgeable) {
        EntityGingerBreadMan entityvillager = new EntityGingerBreadMan(world);
        entityvillager.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(this)), null);
        return entityvillager;
    }

    @Override
    protected float getSoundPitch() {
        return isChild() ? (rand.nextFloat() - rand.nextFloat()) * 0.2F + 0.2F : (rand.nextFloat() - rand.nextFloat()) * 0.2F + 0.2F;
    }

    @Override
    public ITextComponent getDisplayName() {
        String s1 = "blacksmith";

        if (getProfession() == 1) {
            s1 = "farmer";
        } else if (getProfession() == 2) {
            s1 = "citizen";
        } else if (getProfession() == 3) {
            s1 = "elder";
        }

        TextComponentTranslation chatcomponenttranslation = new TextComponentTranslation("gingerbread.job." + s1, new Object[0]);
        chatcomponenttranslation.getStyle().setHoverEvent(getHoverEvent());
        chatcomponenttranslation.getStyle().setInsertion(getUniqueID().toString());
        return chatcomponenttranslation;
    }
}
