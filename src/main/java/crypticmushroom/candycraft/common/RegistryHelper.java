package crypticmushroom.candycraft.common;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryHelper {

    public static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> reg, IForgeRegistryEntry<T> thing, ResourceLocation name) {
        reg.register(thing.setRegistryName(name));
    }

    public static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> reg, IForgeRegistryEntry<T> thing, String name) {
        register(reg, thing, new ResourceLocation(Globals.Mod.ID, name));
    }
}
