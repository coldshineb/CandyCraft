package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEmblem extends ItemCandyBase {
    private String attr;

    public ItemEmblem(String desc) {
        super();
        setMaxStackSize(1);
        setCreativeTab(CandyCraft.getCandyTab());
        attr = desc;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String str = "\2472" + I18n.format("Desc." + attr);
        tooltip.add(str.replaceAll("Format error: ", ""));
        tooltip.add("\247a" + I18n.format("Desc.Emblem"));
    }
}
