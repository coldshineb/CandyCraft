package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.oredict.OreDictionary.*;

@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
public class CCRecipes {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        registerOre("planksMarshmallow", new ItemStack(CCBlocks.marshmallowPlanks, 1));
        registerOre("planksMarshmallow", new ItemStack(CCBlocks.marshmallowPlanksDark, 1));
        registerOre("planksMarshmallow", new ItemStack(CCBlocks.marshmallowPlanksLight, 1));

        CCRecipeHandler.addAlchemyRecipe(CCItems.candiedCherry, MobEffects.INSTANT_HEALTH);
        CCRecipeHandler.addAlchemyRecipe(CCItems.pezDust, MobEffects.FIRE_RESISTANCE);
        CCRecipeHandler.addAlchemyRecipe(CCItems.pez, MobEffects.RESISTANCE);
        CCRecipeHandler.addAlchemyRecipe(CCItems.candyCane, MobEffects.REGENERATION);
        CCRecipeHandler.addAlchemyRecipe(CCItems.cottonCandy, MobEffects.ABSORPTION);
        CCRecipeHandler.addAlchemyRecipe(CCItems.gummy, MobEffects.JUMP_BOOST);
        CCRecipeHandler.addAlchemyRecipe(CCItems.honeyShard, MobEffects.HASTE);
        CCRecipeHandler.addAlchemyRecipe(CCItems.waffle, MobEffects.SATURATION);
        CCRecipeHandler.addAlchemyRecipe(CCBlocks.fraiseTagadaFlower, MobEffects.HASTE);
        CCRecipeHandler.addAlchemyRecipe(CCBlocks.bananaBlock, MobEffects.WATER_BREATHING);
        CCRecipeHandler.addAlchemyRecipe(CCBlocks.mintBlock, MobEffects.WATER_BREATHING);
        CCRecipeHandler.addAlchemyRecipe(CCBlocks.raspberryBlock, MobEffects.WATER_BREATHING);
        CCRecipeHandler.addAlchemyRecipe(CCItems.sugarCrystal, MobEffects.INVISIBILITY);
        CCRecipeHandler.addAlchemyRecipe(CCBlocks.poisonousFlower, MobEffects.POISON);
        CCRecipeHandler.addAlchemyRecipe(CCItems.marshmallowFlower, MobEffects.SPEED);
        CCRecipeHandler.addAlchemyRecipe(CCItems.dragibus, MobEffects.REGENERATION);
        CCRecipeHandler.addAlchemyRecipe(CCItems.lollipop, MobEffects.INSTANT_HEALTH);
        CCRecipeHandler.addAlchemyRecipe(CCItems.lollipopSeeds, MobEffects.WEAKNESS);
        CCRecipeHandler.addAlchemyRecipe(CCItems.gummy_ball, MobEffects.SLOWNESS);
        CCRecipeHandler.addAlchemyRecipe(CCItems.caramelBucket, MobEffects.STRENGTH);
        CCRecipeHandler.addAlchemyRecipe(CCItems.licorice, MobEffects.NIGHT_VISION);
        CCRecipeHandler.addAlchemyRecipe(CCItems.honeycomb, MobEffects.SPEED);
        CCRecipeHandler.addAlchemyRecipe(CCItems.cranberryFish, MobEffects.INSTANT_DAMAGE);
        CCRecipeHandler.addAlchemyRecipe(CCItems.chewingGum, MobEffects.MINING_FATIGUE);
        CCRecipeHandler.addAlchemyRecipe(CCItems.waffleNugget, MobEffects.HUNGER);
        CCRecipeHandler.addAlchemyRecipe(CCItems.nougatPowder, MobEffects.NAUSEA);
        CCRecipeHandler.addAlchemyRecipe(Items.COOKIE, MobEffects.BLINDNESS);
        CCRecipeHandler.addAlchemyRecipe(Items.SUGAR, MobEffects.HUNGER);
    }
}
