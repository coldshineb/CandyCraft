package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class CCLootTables {
    public static final CCLootTables candy_house = new CCLootTables("candy_house");
    public static final CCLootTables ice_tower = new CCLootTables("ice_tower");
    public static final CCLootTables honey_dungeon = new CCLootTables("honey_dungeon");
    //TODO: Make this table
    public static final CCLootTables slime_dungeon = new CCLootTables("slime_dungeon");

    private final ResourceLocation lootTable;

    private CCLootTables(String name) {
        lootTable = new ResourceLocation(CandyCraft.MODID, String.format("structure/%s/%s", name, name));
        LootTableList.register(lootTable);
    }

    public void generateChest(World world, BlockPos pos) {
        world.setBlockState(pos, CCBlocks.marshmallowChest.getDefaultState(), 2);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityCandyChest) {
            ((TileEntityCandyChest)te).setLootTable(lootTable, world.getSeed() * pos.getX() + pos.getY() ^ pos.getZ());
        }
    }
}
