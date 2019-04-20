package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BlockCandyLeave extends BlockLeaves implements IShearable, ModelRegisterCallback {

    private final Supplier<Item> sapling;

    protected BlockCandyLeave(Supplier<Item> drop) {
        setCreativeTab(CandyCraft.getCandyTab());
        setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.TRUE).withProperty(DECAYABLE, Boolean.TRUE));
        sapling = drop;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube(IBlockState state) {
        setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        return !leavesFancy;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        return leavesFancy ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (!state.getValue(DECAYABLE)) {
            i |= 4;
        }

        if (state.getValue(CHECK_DECAY)) {
            i |= 8;
        }

        return i;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
    }

    /* TODO: Allow for metadata-free version
    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return state.getValue(VARIANT_PROP) == BlockPlanks.EnumType.JUNGLE ? 40 : super.getSaplingDropChance(state);
    }
*/
    @Override
    public void dropBlockAsItemWithChance(World thisWorld, BlockPos pos, IBlockState state, float par6, int par7) {
        if (!thisWorld.isRemote && this == CCBlocks.candyLeave) {
            byte var8 = 20;
            int i = getMetaFromState(state);
            if ((i & 3) == 3) {
                var8 = 40;
            }

            if (thisWorld.rand.nextInt(var8) == 0) {
                Item var9 = getItemDropped(state, thisWorld.rand, par7);
                spawnAsEntity(thisWorld, pos, new ItemStack(var9, 1, damageDropped(state)));
            }

            if ((i & 3) == 0 && thisWorld.rand.nextInt(60) == 0) {
                spawnAsEntity(thisWorld, pos, new ItemStack(Items.DYE, 1, 3));
            }
        }
    }

    @Override
    public void updateTick(World thisWorld, BlockPos pos, IBlockState state, Random par5Random) {
        if (!thisWorld.isRemote) {
            int l = getMetaFromState(state);

            if ((l & 3) == 3) {
                if (thisWorld.isAirBlock(pos.down())) {
                    if (par5Random.nextInt(100) == 0) {
                        thisWorld.setBlockState(pos.down(), CCBlocks.cherryBlock.getDefaultState());
                    }
                }
            }
        }
        super.updateTick(thisWorld, pos, state, par5Random);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return sapling.get();
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(CHECK_DECAY, DECAYABLE).build());
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
