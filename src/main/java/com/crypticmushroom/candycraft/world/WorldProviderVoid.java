package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.world.biomes.CCBiomes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderVoid extends WorldProvider {

    @Override
    protected void init() {
        biomeProvider = new BiomeProviderSingle(CCBiomes.candyVoid);
        nether = true;
        hasSkyLight = false;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderCandyVoid(world, world.getSeed());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float par1, float par2) {
        float f2 = MathHelper.cos(par1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        float f3 = 0.7529412F;
        float f4 = 0.5705883F;
        float f5 = 1.0F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return new Vec3d(f3, f4, f5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return true;
    }

    /**
     * @Override public String getWelcomeMessage() {
     * return "Entering the Dungeon";
     * }
     * @Override public String getDepartMessage() {
     * return "Leaving the Dungeon";
     * }
     */

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        return CandyCraftConfig.candyDimID;
    }

    @Override
    public DimensionType getDimensionType() {
        return CandyCraft.dungeonDim;
    }

}
