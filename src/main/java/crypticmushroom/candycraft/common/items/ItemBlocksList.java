package crypticmushroom.candycraft.common.items;

import crypticmushroom.candycraft.common.Globals;
import crypticmushroom.candycraft.common.RegistryHelper;
import crypticmushroom.candycraft.common.blocks.BlocksList;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemBlocksList {

    @SubscribeEvent
    public static void onRegisterItemEvent(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        Item.Properties defaultProperties = new Item.Properties();
        RegistryHelper.register(registry, new ItemBlockBase(BlocksList.SUGAR, defaultProperties), BlocksList.SUGAR.getRegistryName());

    }
}
