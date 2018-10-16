package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import com.crypticmushroom.candycraft.items.CCItems;
import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.List;
import java.util.Random;

public class WorldGenHoneyDungeons extends WorldGenerator {
    //private static final WeightedRandomChestContent[] itemsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(CCItems.PEZ, 0, 2, 8, 10), new WeightedRandomChestContent(Items.SADDLE, 0, 1, 1, 10), new WeightedRandomChestContent(CCItems.honeyEmblem, 0, 1, 1, 10), new WeightedRandomChestContent(CCItems.honeycomb, 0, 1, 8, 10), new WeightedRandomChestContent(CCItems.honeyHelmet, 0, 1, 1, 10), new WeightedRandomChestContent(CCItems.honeySword, 0, 1, 1, 10), new WeightedRandomChestContent(CCItems.honeyShovel, 0, 1, 1, 10), new WeightedRandomChestContent(CCItems.honeycomb, 0, 1, 4, 10), new WeightedRandomChestContent(CCItems.licorice, 0, 1, 4, 10), new WeightedRandomChestContent(CCItems.lollipop, 0, 1, 15, 10), new WeightedRandomChestContent(Item.getItemFromBlock(CCBlocks.jellyOre), 0, 1, 4, 10), new WeightedRandomChestContent(CCItems.candyCane, 0, 1, 8, 10), new WeightedRandomChestContent(CCItems.honeyShard, 0, 1, 4, 10), new WeightedRandomChestContent(CCItems.caramelBucket, 0, 1, 1, 10), new WeightedRandomChestContent(CCItems.licoricePlate, 0, 1, 1, 1), new WeightedRandomChestContent(CCItems.PEZ, 0, 1, 4, 6), new WeightedRandomChestContent(Item.getItemFromBlock(CCBlocks.honeyTorch), 0, 1, 32, 10)};
    //private final List chestContent = Lists.newArrayList(itemsList);

	// TODO REIMPLEMENT CHEST
	
    @Override
    public boolean generate(World par1World, Random par2Random, BlockPos pos) {
        int par3 = pos.getX();
        int par4 = pos.getY();
        int par5 = pos.getZ();
        byte b0 = 3;
        int l = par2Random.nextInt(2) + 2;
        int i1 = par2Random.nextInt(2) + 2;
        int j1 = 0;
        int k1;
        int l1;
        int i2;

        for (k1 = par3 - l - 1; k1 <= par3 + l + 1; ++k1) {
            for (l1 = par4 - 1; l1 <= par4 + b0 + 1; ++l1) {
                for (i2 = par5 - i1 - 1; i2 <= par5 + i1 + 1; ++i2) {
                    Material material = par1World.getBlockState(new BlockPos(k1, l1, i2)).getMaterial();

                    if (l1 == par4 - 1 && !material.isSolid()) {
                        return false;
                    }

                    if (l1 == par4 + b0 + 1 && !material.isSolid()) {
                        return false;
                    }

                    if ((k1 == par3 - l - 1 || k1 == par3 + l + 1 || i2 == par5 - i1 - 1 || i2 == par5 + i1 + 1) && l1 == par4 && par1World.isAirBlock(new BlockPos(k1, l1, i2)) && par1World.isAirBlock(new BlockPos(k1, l1 + 1, i2))) {
                        ++j1;
                    }
                }
            }
        }
        if (j1 >= 1 && j1 <= 5) {
            for (k1 = par3 - l - 1; k1 <= par3 + l + 1; ++k1) {
                for (l1 = par4 + b0; l1 >= par4 - 1; --l1) {
                    for (i2 = par5 - i1 - 1; i2 <= par5 + i1 + 1; ++i2) {
                        if (k1 != par3 - l - 1 && l1 != par4 - 1 && i2 != par5 - i1 - 1 && k1 != par3 + l + 1 && l1 != par4 + b0 + 1 && i2 != par5 + i1 + 1) {
                            par1World.setBlockToAir(new BlockPos(k1, l1, i2));
                        } else if (l1 >= 0 && !par1World.getBlockState(new BlockPos(k1, l1 - 1, i2)).getMaterial().isSolid()) {
                            par1World.setBlockToAir(new BlockPos(k1, l1, i2));
                        } else if (par1World.getBlockState(new BlockPos(k1, l1, i2)).getMaterial().isSolid()) {
                            par1World.setBlockState(new BlockPos(k1, l1, i2), CCBlocks.honeyBlock.getDefaultState(), 2);
                        }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2) {
                l1 = 0;

                while (true) {
                    if (l1 < 3) {
                        label101:
                        {
                            i2 = par3 + par2Random.nextInt(l * 2 + 1) - l;
                            int j2 = par5 + par2Random.nextInt(i1 * 2 + 1) - i1;

                            if (par1World.isAirBlock(new BlockPos(i2, par4, j2))) {
                                int k2 = 0;

                                if (par1World.getBlockState(new BlockPos(i2 - 1, par4, j2)).getMaterial().isSolid()) {
                                    ++k2;
                                }

                                if (par1World.getBlockState(new BlockPos(i2 + 1, par4, j2)).getMaterial().isSolid()) {
                                    ++k2;
                                }

                                if (par1World.getBlockState(new BlockPos(i2, par4, j2 - 1)).getMaterial().isSolid()) {
                                    ++k2;
                                }

                                if (par1World.getBlockState(new BlockPos(i2, par4, j2 + 1)).getMaterial().isSolid()) {
                                    ++k2;
                                }

                                if (k2 == 1) {
                                    par1World.setBlockState(new BlockPos(i2, par4, j2), CCBlocks.marshmallowChest.getDefaultState(), 2);
                                    TileEntityCandyChest tileentitychest = (TileEntityCandyChest) par1World.getTileEntity(new BlockPos(i2, par4, j2));
                                    if (tileentitychest != null) {
                                        //WeightedRandomChestContent.generateChestContents(par2Random, chestContent, tileentitychest, 8);
                                    }
                                    break label101;
                                }
                            }

                            ++l1;
                            continue;
                        }
                    }

                    ++k1;
                    break;
                }
            }

            par1World.setBlockState(new BlockPos(par3, par4, par5), Blocks.MOB_SPAWNER.getDefaultState(), 2);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) par1World.getTileEntity(new BlockPos(par3, par4, par5));

            if (tileentitymobspawner != null) {
                tileentitymobspawner.getSpawnerBaseLogic().setEntityId(new ResourceLocation("candycraftmod.CaramelBee"));
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
            }

            return true;
        } else {
            return false;
        }
    }
}
