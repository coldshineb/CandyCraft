package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandyRecord extends ItemRecord implements ModelRegisterCallback {
    String name;

    public ItemCandyRecord(SoundEvent sound, String par2Str, String par3) {
        super(par2Str, sound);
        setCreativeTab(CandyCraft.getCandyTab());
        name = par3;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getRecordNameLocal() {
        return name;
    }
}
