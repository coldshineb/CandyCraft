package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import com.crypticmushroom.candycraft.world.generator.ThreadCheckDungeon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDungeonKey extends ItemCandyBase {
    public final int keyId;

    public ItemDungeonKey(int key) {
        super();
        keyId = key;
        setMaxStackSize(1);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (keyId <= 1) {
            if (!player.canPlayerEdit(pos.offset(facing), facing, player.getHeldItem(hand)) || !CandyCraftConfig.canGenerateDungeon) {
                return EnumActionResult.FAIL;
            } else {
                IBlockState bl = worldIn.getBlockState(pos);
                if (bl.isOpaqueCube() && worldIn.isAirBlock(pos.up()) && !worldIn.isRemote) {
                    worldIn.setBlockState(pos.up(), CCBlocks.blockTeleporter.getStateFromMeta(keyId));
                    player.setHeldItem(hand, ItemStack.EMPTY);
                    player.sendStatusMessage(new TextComponentString("\247e" + new TextComponentTranslation("chat.generating").getUnformattedText()), true);
                    ThreadCheckDungeon d = new ThreadCheckDungeon(keyId);
                    d.teleport = (TileEntityTeleporter) worldIn.getTileEntity(pos.up());
                    d.player = player;
                    d.px = pos.getX();
                    d.py = pos.getY() + 1;
                    d.pz = pos.getZ();
                    d.dim = worldIn.provider.getDimension();
                    d.start();
                    return EnumActionResult.SUCCESS;
                }
                return EnumActionResult.FAIL;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this == CCItems.orangeKey || this == CCItems.blueKey) {
            tooltip.add("\247a" + I18n.format("Desc.key"));
        }
    }
}
