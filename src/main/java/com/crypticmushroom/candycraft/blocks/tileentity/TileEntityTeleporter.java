package com.crypticmushroom.candycraft.blocks.tileentity;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.world.generator.WorldGenSlimeDungeon;
import com.crypticmushroom.candycraft.world.generator.WorldGenSuguardDungeon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityTeleporter extends TileEntity implements ITickable {
    public int dungeonID = -1;
    public boolean generated = false;
    public EntityPlayer player = null;
    public int x = 0;
    public int y = 64;
    public int z = 0;
    public int oX, oY, oZ, dim = 0;
    public int tickExisted = 0;
    public int keyId;

    public void genDungeon(World world) {
        if (keyId == 0) {
            new WorldGenSlimeDungeon(oX, oY, oZ, dim).generate(world.getMinecraftServer().getWorld(CandyCraft.getDungeonDimensionID()), world.rand, new BlockPos(dungeonID * 1000, 64, 0));
        } else if (keyId == 1) {
            new WorldGenSuguardDungeon(oX, oY, oZ, dim).generate(world.getMinecraftServer().getWorld(CandyCraft.getDungeonDimensionID()), world.rand, new BlockPos(dungeonID * 1000, 64, 10000));
        }

        if (player != null) {
            player.sendStatusMessage(new TextComponentString("\247e" + new TextComponentTranslation("chat.generated").getUnformattedText()), true);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        generated = par1NBTTagCompound.getBoolean("generated");
        x = par1NBTTagCompound.getInteger("xPort");
        y = par1NBTTagCompound.getInteger("yPort");
        z = par1NBTTagCompound.getInteger("zPort");
        dim = par1NBTTagCompound.getInteger("dim");
        tickExisted = par1NBTTagCompound.getInteger("tickExisted");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("generated", generated);
        par1NBTTagCompound.setInteger("xPort", x);
        par1NBTTagCompound.setInteger("yPort", y);
        par1NBTTagCompound.setInteger("zPort", z);
        par1NBTTagCompound.setInteger("dim", dim);
        par1NBTTagCompound.setInteger("tickExisted", tickExisted);

        return par1NBTTagCompound;
    }

    @Override
    public void update() {
        if (dungeonID != -1 && !generated) {
            generated = true;
            genDungeon(world);
        }
        tickExisted++;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new SPacketUpdateTileEntity(pos, 1, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2);
    }
}
