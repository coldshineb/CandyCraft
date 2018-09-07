package com.valentin4311.candycraftmod.world;

import com.valentin4311.candycraftmod.CandyCraft;
import com.valentin4311.candycraftmod.client.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCandy extends WorldProvider
{
	public static final DimensionType CANDY_WORLD = EnumHelper.addEnum(DimensionType.class, "CANDY_WORLD_MOD", new Class[]{Integer.class, String.class, String.class, Class.class}, CandyCraft.getCandyDimensionID(), "Candy Valley", "_candyworld", WorldProviderCandy.class);

	public boolean isHellWorld = true;

	public static int canGenIsland = 0;
	public static int canGenVillage = 0;
	public static int canGenChewingGum = 0;
	public static int canGenGeyser = 0;
	public static int canGenHouses = 0;
	public static int canGenTemple = 0;
	public static int canGenIceTower = 0;

	private static int skyX, skyZ;
	private static boolean skyInit;
	private static int skyRGBMultiplier;

	@SideOnly(Side.CLIENT)
	public static int getFogBlendColour(World world, int playerX, int playerY, int playerZ)
	{
		if (playerX == skyX && playerZ == skyZ && skyInit)
		{
			return skyRGBMultiplier;
		}
		skyInit = true;

		GameSettings settings = Minecraft.getMinecraft().gameSettings;
		int[] ranges = ForgeModContainer.blendRanges;
		int distance = 0;
		if (settings.fancyGraphics && settings.renderDistanceChunks >= 0 && settings.renderDistanceChunks < ranges.length)
		{
			distance = ranges[settings.renderDistanceChunks];
		}

		int r = 0;
		int g = 0;
		int b = 0;

		int divider = 0;
		for (int x = -distance; x <= distance; ++x)
		{
			for (int z = -distance; z <= distance; ++z)
			{
				Biome biome = world.getBiomeGenForCoords(new BlockPos(playerX + x, 0, playerZ + z));
				int color = biome.getGrassColorAtPos(new BlockPos(playerX + x, playerY, playerZ + z));
				r += (color & 0xFF0000) >> 16;
				g += (color & 0x00FF00) >> 8;
				b += color & 0x0000FF;
				divider++;
			}
		}

		int multiplier = (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;

		skyX = playerX;
		skyZ = playerZ;
		skyRGBMultiplier = multiplier;
		return skyRGBMultiplier;
	}

	@SideOnly(Side.CLIENT)
	public Vec3d drawCloudsBody(float par1)
	{
		float f1 = worldObj.getCelestialAngle(par1);
		float f2 = MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}

		if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}

		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		int l = WorldProviderCandy.getFogBlendColour(worldObj, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));

		float f3 = ((l >> 16 & 255) / 255.0F) * f2;
		float f4 = ((l >> 8 & 255) / 255.0F) * f2;
		float f5 = ((l & 255) / 255.0F) * f2;
		return new Vec3d(f3, f4, f5);
	}

	@Override
	public void createBiomeProvider()
	{
		biomeProvider = new BiomeProvider(worldObj.getWorldInfo());// TODO
		isHellWorld = true;
		hasNoSky = false;
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkProviderCandyWorld(worldObj, worldObj.getSeed(), false, "");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getWeatherRenderer()
	{
		return ClientProxy.weatherRenderer;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getCloudColor(float partialTicks)
	{
		return drawCloudsBody(partialTicks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float par1, float par2)
	{
		float f1 = worldObj.getCelestialAngle(par2);
		float f2 = MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}

		if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}

		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		int l = WorldProviderCandy.getFogBlendColour(worldObj, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
		float f3 = ((l >> 16 & 255) / 255.0F) * f2;
		float f4 = ((l >> 8 & 255) / 255.0F) * f2;
		float f5 = ((l & 255) / 255.0F) * f2;
		return new Vec3d(f3, f4, f5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return true;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Entering the Candy Valley";
	}

	@Override
	public String getDepartMessage()
	{
		return "Leaving the Candy Valley";
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	@Override
	public boolean canDoRainSnowIce(net.minecraft.world.chunk.Chunk chunk)
	{
		return false;
	}

	@Override
	public DimensionType getDimensionType()
	{
		return CANDY_WORLD;
	}
}
