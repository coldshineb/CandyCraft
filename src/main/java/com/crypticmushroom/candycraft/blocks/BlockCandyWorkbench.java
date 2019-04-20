package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.gui.ContainerCandyWorkbench;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCandyWorkbench extends BlockWorkbench implements ModelRegisterCallback {

    public BlockCandyWorkbench() {
        super();
        setSoundType(SoundType.WOOD);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            player.displayGui(new BlockCandyWorkbench.InterfaceCraftingTable(world, pos));
            return true;
        }
    }

    public static class InterfaceCraftingTable extends BlockWorkbench.InterfaceCraftingTable {
        private final World world;
        private final BlockPos position;

        public InterfaceCraftingTable(World worldIn, BlockPos blockPos) {
            super(worldIn, blockPos);
            world = worldIn;
            position = blockPos;
        }

        @Override
        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return new ContainerCandyWorkbench(playerInventory, world, position);
        }
    }
}
