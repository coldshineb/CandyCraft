package crypticmushroom.candycraft.common.blocks;

import crypticmushroom.candycraft.common.Globals;
import crypticmushroom.candycraft.common.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Globals.Mod.ID)
public class BlocksList {

    @ObjectHolder(Globals.Blocks.SUGAR)
    public static Block SUGAR;

    @SubscribeEvent
    public static void onRegisterBlockEvent(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        Block.Properties properties = Block.Properties.create(Material.SAND);
        RegistryHelper.register(registry, new BlockBase(properties), Globals.Blocks.SUGAR);

    }

}
