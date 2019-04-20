package com.crypticmushroom.candycraft.blocks.tileentity;

import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.entity.EntityDragon;
import com.crypticmushroom.candycraft.entity.EntityKingBeetle;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class TileEntityEgg extends TileEntity implements ITickable {
    public int timeLeft;
    public int hatchDelay = 50;
    private Random rand = new Random();

    public TileEntityEgg() {
        timeLeft = rand.nextInt(24000) + 48000;
    }

    @Override
    public void update() {
        boolean readyToHatch = true;
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                if (x == 0 || x == 4 || z == 0 || z == 4) {
                    if (world.getBlockState(new BlockPos(pos.getX() - 2 + x, pos.getY(), pos.getZ() - 2 + z)).getBlock() != CCBlocks.sugarEssenceFlower) {
                        readyToHatch = false;
                    }
                }
            }
        }
        if (readyToHatch || timeLeft <= hatchDelay) {
            timeLeft--;

            if (!world.isRemote) {
                if (timeLeft == hatchDelay) {
                    if (world.getBlockState(pos).getBlock() == CCBlocks.dragonEggBlock && CandyCraftConfig.allowDragons) {
                        EntityDragon dragon = new EntityDragon(world);
                        dragon.setPosition(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ());
                        world.spawnEntity(dragon);
                    } else if (CandyCraftConfig.allowBeetleKings) {
                        EntityKingBeetle beetle = new EntityKingBeetle(world);
                        beetle.setPosition(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ());
                        world.spawnEntity(beetle);
                        beetle.entityDropItem(new ItemStack(CCItems.chewingGumEmblem), 0.5F);
                    }
                }
                if (timeLeft <= 0) {
                    world.setBlockToAir(pos);

                    for (int x = 0; x < 5; x++) {
                        for (int z = 0; z < 5; z++) {
                            if (x == 0 || x == 4 || z == 0 || z == 4) {
                                if (world.getBlockState(new BlockPos(pos.getX() - 2 + x, pos.getY(), pos.getZ() - 2 + z)).getBlock() == CCBlocks.sugarEssenceFlower) {
                                    world.setBlockState(new BlockPos(pos.getX() - 2 + x, pos.getY(), pos.getZ() - 2 + z), CCBlocks.fraiseTagadaFlower.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new SPacketUpdateTileEntity(pos, 1, nbttagcompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        timeLeft = par1NBTTagCompound.getInteger("hatch");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("hatch", timeLeft);

        return par1NBTTagCompound;
    }
}
