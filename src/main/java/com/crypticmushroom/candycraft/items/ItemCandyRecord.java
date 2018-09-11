package com.crypticmushroom.candycraft.items;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandyRecord extends ItemRecord {
    String name;

    public ItemCandyRecord(SoundEvent sound, String par2Str, String par3) {
        super(par2Str, sound);
        name = par3;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getRecordNameLocal() {
        return name;
    }
}
