package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.entity.EntityBeetle;
import com.crypticmushroom.candycraft.entity.EntityKingBeetle;
import com.crypticmushroom.candycraft.entity.boss.EntityBossBeetle;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import static com.crypticmushroom.candycraft.misc.CCSoundTypes.SOUND_JELLY_FOOTSTEP;

public class BlockChewingGum extends BlockCandyBase {
    protected static final AxisAlignedBB GUM_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);

    public BlockChewingGum(Material material) {
        super(material, SOUND_JELLY_FOOTSTEP);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return GUM_AABB;
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if (!(entity instanceof EntityBeetle) && !(entity instanceof EntityBossBeetle) && !(entity instanceof EntityKingBeetle)) {
            if (entity instanceof EntityPlayer && ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(CCItems.chewingGumEmblem))) {
                return;
            }

            entity.motionX *= 0.2D;
            entity.motionZ *= 0.2D;
            entity.motionY = 0;
        }
    }
}
