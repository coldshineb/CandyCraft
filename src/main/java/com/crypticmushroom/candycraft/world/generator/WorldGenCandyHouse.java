package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.world.WorldProviderCandy;
import net.minecraft.block.Block;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenCandyHouse extends WorldGenerator {
    public static Item[] itemToStock = {Item.getItemFromBlock(CCBlocks.pudding), CCItems.lollipopSeeds, Item.getItemFromBlock(CCBlocks.candyCaneBlock), CCItems.lollipop, CCItems.candyCane, CCItems.licorice, Item.getItemFromBlock(CCBlocks.trampojelly), Item.getItemFromBlock(CCBlocks.jellyShockAbsorber), CCItems.honeyEmblem, CCItems.honeyEmblem, CCItems.honeyEmblem, CCItems.cottonCandy, CCItems.waffleNugget, CCItems.PEZDust, CCItems.marshmallowFlower, CCItems.glueDynamite, CCItems.dynamite, CCItems.fork, CCItems.cranberryFish, CCItems.cranberryFishCooked, Item.getItemFromBlock(CCBlocks.poisonousFlower), CCItems.PEZ};
    World world;

    @Override
    public boolean generate(World par1World, Random par2Random, BlockPos pos) {
        world = par1World;
        int par3 = pos.getX();
        int par4 = pos.getY();
        int par5 = pos.getZ();

        if (par2Random.nextInt(2) == 0) {
            WorldProviderCandy.canGenHouses = 300;

            par3 -= 8;
            for (par5 -= 8; par4 > 5 && shouldGoDeeeper(par1World, pos); --par4) {
                pos = new BlockPos(par3, par4, par5);
            }
            par4++;
            pos = new BlockPos(par3, par4, par5);
            if (par1World.getBlockState(pos).getBlock() == CCBlocks.pudding) {
                for (int iGen = 0; iGen < 5; iGen++) {
                    for (int xGen = 0; xGen < 5; xGen++) {
                        for (int zGen = 0; zGen < 5; zGen++) {
                            world.setBlockToAir(new BlockPos(par3 - 2 + xGen, iGen + par4, par5 - 2 + zGen));
                        }
                    }
                }
                for (int genLayer = -1; genLayer <= 4; genLayer++) {
                    if (genLayer == -1 || genLayer == 3) {
                        this.setBlock(par3 - 2, par4 + genLayer, par5, CCBlocks.marshmallowLog, 8, 2);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 1, CCBlocks.marshmallowLog, 8, 2);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 1, CCBlocks.marshmallowLog, 8, 2);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5, CCBlocks.marshmallowLog, 8, 2);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 1, CCBlocks.marshmallowLog, 8, 2);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 1, CCBlocks.marshmallowLog, 8, 2);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 - 1, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 4, 2);
                        this.setBlock(par3, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 4, 2);
                        this.setBlock(par3 + 1, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 4, 2);
                        this.setBlock(par3 - 1, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 4, 2);
                        this.setBlock(par3, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 4, 2);
                        this.setBlock(par3 + 1, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 4, 2);
                        if (genLayer == -1) {
                            this.setBlock(par3 + 1, par4 + genLayer, par5, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 - 1, par4 + genLayer, par5, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3, par4 + genLayer, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3, par4 + genLayer, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 + 1, par4 + genLayer, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 - 1, par4 + genLayer, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 + 1, par4 + genLayer, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 - 1, par4 + genLayer, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3, par4 + genLayer, par5, CCBlocks.marshmallowPlanks, 0);
                        }
                    }
                    if (genLayer == 0 || genLayer == 2) {
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 2, CCBlocks.candyCaneWall);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 2, CCBlocks.candyCaneWall);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 2, CCBlocks.candyCaneWall);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 2, CCBlocks.candyCaneWall);
                    } else {
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                    }
                }
                this.setBlock(par3 - 2, par4, par5, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 2, par4, par5, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3, par4, par5 + 2, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3, par4, par5 - 2, CCBlocks.candyCaneBlock, 1, 2);

                this.setBlock(par3 - 2, par4 + 1, par5, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 2, par4 + 1, par5, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3, par4 + 1, par5 + 2, CCBlocks.candyCaneBlock, 0, 2);
                this.setBlock(par3, par4 + 1, par5 - 2, CCBlocks.candyCaneBlock, 0, 2);

                this.setBlock(par3 - 2, par4 + 1, par5 + 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 2, par4 + 1, par5 - 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 1, par4 + 1, par5 + 2, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 - 1, par4 + 1, par5 - 2, CCBlocks.candyCaneBlock, 1, 2);

                this.setBlock(par3 - 2, par4 + 1, par5 - 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 2, par4 + 1, par5 + 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 - 1, par4 + 1, par5 + 2, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 1, par4 + 1, par5 - 2, CCBlocks.candyCaneBlock, 1, 2);

                this.setBlock(par3 - 2, par4, par5 + 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 2, par4, par5 - 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 1, par4, par5 + 2, CCBlocks.candyCaneBlock, 0, 2);
                this.setBlock(par3 - 1, par4, par5 - 2, CCBlocks.candyCaneBlock, 0, 2);

                this.setBlock(par3 - 2, par4, par5 - 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 2, par4, par5 + 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 - 1, par4, par5 + 2, CCBlocks.candyCaneBlock, 0, 2);
                this.setBlock(par3 + 1, par4, par5 - 2, CCBlocks.candyCaneBlock, 0, 2);

                this.setBlock(par3 + 2, par4 + 2, par5, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 2, par4 + 2, par5 + 1, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 2, par4 + 2, par5 - 1, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 2, par4 + 2, par5, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 2, par4 + 2, par5 + 1, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 2, par4 + 2, par5 - 1, CCBlocks.caramelBlock, 0);

                this.setBlock(par3, par4 + 2, par5 + 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 1, par4 + 2, par5 + 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 1, par4 + 2, par5 + 2, CCBlocks.caramelBlock, 0);

                this.setBlock(par3, par4 + 2, par5 - 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 1, par4 + 2, par5 - 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 1, par4 + 2, par5 - 2, CCBlocks.caramelBlock, 0);

                this.setBlock(par3 + 1, par4 + 3, par5, CCBlocks.marshmallowHalfStep, 11, 2);
                this.setBlock(par3 - 1, par4 + 3, par5, CCBlocks.marshmallowHalfStep, 11, 2);
                this.setBlock(par3, par4 + 3, par5 - 1, CCBlocks.marshmallowHalfStep, 11, 2);
                this.setBlock(par3, par4 + 3, par5 + 1, CCBlocks.marshmallowHalfStep, 11, 2);

                this.setBlock(par3, par4 + 4, par5 + 2, CCBlocks.marshmallowHalfStep, 0);
                this.setBlock(par3, par4 + 4, par5 - 2, CCBlocks.marshmallowHalfStep, 0);
                this.setBlock(par3 + 2, par4 + 4, par5, CCBlocks.marshmallowHalfStep, 0);
                this.setBlock(par3 - 2, par4 + 4, par5, CCBlocks.marshmallowHalfStep, 0);

                this.setBlock(par3 + 1, par4 + 3, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                this.setBlock(par3 - 1, par4 + 3, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                this.setBlock(par3 + 1, par4 + 3, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                this.setBlock(par3 - 1, par4 + 3, par5 - 1, CCBlocks.marshmallowPlanks, 0);

                this.setBlock(par3 - 2, par4 - 2, par5, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 - 1, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 + 1, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 - 1, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 + 1, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3 - 1, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 1, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 - 1, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 1, par4 - 2, par5 + 2, CCBlocks.flour, 0);

                this.setBlock(par3, par4, par5, CCBlocks.marshmallowChest, 0, 3);
                TileEntityCandyChest chest = (TileEntityCandyChest) par1World.getTileEntity(new BlockPos(par3, par4, par5));
                int time = par2Random.nextInt(7) + 5;
                for (int chestTime = 0; chestTime <= time; chestTime++) {
                    Item it = WorldGenCandyHouse.itemToStock[par2Random.nextInt(itemToStock.length)];
                    int stack = par2Random.nextInt(7) + 4;
                    ItemStack st = new ItemStack(it);
                    st.setCount(stack > st.getMaxStackSize() ? st.getMaxStackSize() : stack);

                    if (par2Random.nextInt(15) != 12) {
                        chest.setInventorySlotContents(par2Random.nextInt(27), new ItemStack(st.getItem(), st.getCount()));
                    } else {
                        int arm = par2Random.nextInt(4);
                        ItemStack helmet = new ItemStack(CCItems.licoriceHelmet);
                        ItemStack plate = new ItemStack(CCItems.licoricePlate);
                        ItemStack leggings = new ItemStack(CCItems.licoriceLeggings);
                        ItemStack boots = new ItemStack(CCItems.licoriceBoots);
                        if (arm == 0) {
                            helmet.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                            if (par2Random.nextInt(5) != 0) {
                                helmet.addEnchantment(Enchantments.BLAST_PROTECTION, 1);
                            }
                            chest.setInventorySlotContents(par2Random.nextInt(27), helmet);
                        }
                        if (arm == 1) {
                            plate.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                            if (par2Random.nextInt(5) != 0) {
                                plate.addEnchantment(Enchantments.BLAST_PROTECTION, 1);
                            }
                            chest.setInventorySlotContents(par2Random.nextInt(27), plate);
                        }
                        if (arm == 2) {
                            leggings.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                            if (par2Random.nextInt(5) != 0) {
                                leggings.addEnchantment(Enchantments.BLAST_PROTECTION, 1);
                            }
                            chest.setInventorySlotContents(par2Random.nextInt(27), leggings);
                        }
                        if (arm == 3) {
                            boots.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                            if (par2Random.nextInt(5) != 0) {
                                boots.addEnchantment(Enchantments.BLAST_PROTECTION, 1);
                            }
                            chest.setInventorySlotContents(par2Random.nextInt(27), boots);
                        }
                    }
                }
                chest.setInventorySlotContents(0, new ItemStack(Items.LAVA_BUCKET));
                chest.setInventorySlotContents(1, new ItemStack(CCBlocks.sugarBlock, 12, 0));
                par1World.setBlockState(new BlockPos(par3, par4 - 1, par5), CCBlocks.honeyLamp.getDefaultState(), 3);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean shouldGoDeeeper(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return world.isAirBlock(pos) || block == CCBlocks.candyLeave || block == CCBlocks.tallCandyGrass;
    }

    public void setBlock(int x, int y, int z, Block bl) {
        world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState());
    }

    public void setBlock(int x, int y, int z, Block bl, int meta, int flag) {
        world.setBlockState(new BlockPos(x, y, z), bl.getStateFromMeta(meta), flag);
    }

    public void setBlock(int x, int y, int z, Block bl, int flag) {
        world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState(), flag);
    }
}
