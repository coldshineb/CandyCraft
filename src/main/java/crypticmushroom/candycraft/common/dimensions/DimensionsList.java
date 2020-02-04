package crypticmushroom.candycraft.common.dimensions;

import crypticmushroom.candycraft.common.Globals;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionsList {

    @ObjectHolder(Globals.Mod.ID + ":" + Globals.Dimensions.CANDY)
    public static ModDimension CANDY;

    public static DimensionType CANDY_TYPE;

    @SubscribeEvent
    public static void onRegisterModDimensionEvent(final RegistryEvent.Register<ModDimension> event) {
        IForgeRegistry<ModDimension> registry = event.getRegistry();
        registry.register(new ModDimensionCandy().setRegistryName(Globals.Mod.ID, Globals.Dimensions.CANDY));
    }
}

