package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.entity.CCEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCandyMonsterPlacer extends ItemMonsterPlacer {
    public ItemCandyMonsterPlacer() {
        super();
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String s1 = getEntityIdFromItem(stack);
        if (s1 != null) {
            return I18n.translateToLocal("item.candy_spawn_egg." + s1 + ".name");
        }
        return "Empty Egg";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        for (String name : CCEntities.CANDYCRAFT_EGGS.keySet()) {
            ItemStack stack = new ItemStack(itemIn);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("entity_name", name);
            stack.setTagCompound(nbt);
            subItems.add(stack);
        }
    }
    /*
     * TODO check private static EntityList.EntityEggInfo getEggInfo(ItemStack
     * stack) { if (stack.hasTagCompound() &&
     * stack.getTagCompound().hasKey("entity_name", 8)) { return
     * CCEntities.CANDYCRAFT_EGGS.get(stack.getTagCompound().getString(
     * "entity_name")); } return (EntityList.EntityEggInfo)
     * EntityList.ENTITY_EGGS.get(stack.getMetadata()); }
     */
}
