package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemGrenadineCandy extends ItemCandyFood {
    public ItemGrenadineCandy() {
        super(0, 0.6F, false);
        setHasSubtypes(true);
        setAlwaysEdible();
    }

    public PotionEffect upgradePotion(PotionEffect potion) {
        if (potion.getAmplifier() == 0) {
            return potion.getDuration() == 60 * 20 ? new PotionEffect(potion.getPotion(), 20 * 120, 0) : new PotionEffect(potion.getPotion(), 20 * 120, 1);
        } else {
            return new PotionEffect(potion.getPotion(), 20 * 180, 1);
        }
    }

    public int containPotion(PotionEffect[] tab, int pot) {
        for (int i = 0; i < tab.length; i++) {
            try {
                if (tab[i] != null && pot == Potion.getIdFromPotion(tab[i].getPotion())) {
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer) {
        String metaString = String.valueOf(stack.getItemDamage());
        if (metaString.length() < 8) {
            metaString = "0" + metaString;
        }
        if (metaString.length() < 8) {
            return;
        }
        par3EntityPlayer.clearActivePotions();
        PotionEffect[] tabl = new PotionEffect[4];
        for (int i = 0; i < 4; i++) {
            try {
                int j = Integer.valueOf(metaString.substring(i * 2, i * 2 + 2));
                if (Potion.getPotionById(j) != null) {
                    int k;
                    if ((k = containPotion(tabl, j)) != -1) {
                        tabl[k] = upgradePotion(tabl[k]);
                    } else {
                        tabl[i] = new PotionEffect(Potion.getPotionById(j), 20 * 60, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int l = 0; l < 4; l++) {
            try {
                if (tabl[l] != null) {
                    par3EntityPlayer.addPotionEffect(tabl[l].getPotion().isInstant() ? new PotionEffect(tabl[l].getPotion(), 1, tabl[l].getAmplifier()) : tabl[l]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getDamage(ItemStack stack) {
        return stack.getTagCompound() != null ? stack.getTagCompound().getInteger("MetaSystem") : 0;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("");
        tooltip.add("\2476" + I18n.format("Gui.GrenadineCandy.Potion"));
        String metaString = String.valueOf(stack.getItemDamage());
        if (metaString.length() < 8) {
            metaString = "0" + metaString;
        }
        if (metaString.length() < 8) {
            return;
        }
        PotionEffect[] tabl = new PotionEffect[4];
        for (int i = 0; i < 4; i++) {
            try {
                int j = Integer.valueOf(metaString.substring(i * 2, i * 2 + 2));
                if (Potion.getPotionById(j) != null) {
                    int k;
                    if ((k = containPotion(tabl, j)) != -1) {
                        tabl[k] = upgradePotion(tabl[k]);
                    } else {
                        tabl[i] = new PotionEffect(Potion.getPotionById(j), 20 * 60, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int l = 0; l < 4; l++) {
            try {
                if (tabl[l] != null) {
                    tooltip.add((tabl[l].getPotion().isBadEffect() ? "\247c" : "\247a") + I18n.format(tabl[l].getPotion().getName()) + (tabl[l].getAmplifier() != 0 ? " " + I18n.format("enchantment.level." + (tabl[l].getAmplifier() + 1)) : "")
                            + (tabl[l].getPotion().isInstant() ? ""
                            : " (" + Potion.getPotionDurationString(tabl[l],
                            1.0F/* TODO Check duration factor */) + ")"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(CCItems.sugarPill.getTranslationKey().substring(5), "inventory"));
    }
}
