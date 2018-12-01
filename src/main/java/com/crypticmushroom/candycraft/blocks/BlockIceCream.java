package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.IModelProvider;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

import static com.crypticmushroom.candycraft.CandyCraft.MODID;

public class BlockIceCream extends Block implements IModelProvider {

    private static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockIceCream() {
        super(Material.SAND);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.BLUEBERRY));
        setCreativeTab(CandyCraft.ccTab);
        setRegistryName("icecream");
        setTranslationKey(MODID + ".icecream");
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType blockicecream$enumtype : EnumType.values()) {
            items.add(new ItemStack(this, 1, blockicecream$enumtype.getMeta()));
        }
    }

    @Override
    @SuppressWarnings("deprecated")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public void gatherVariants(Int2ObjectMap<String> variants) {
        variants.remove(0);
        for (EnumType enumType : EnumType.META_LOOKUP) {
            variants.put(enumType.getMeta(), enumType.getName());
        }
    }

    public enum EnumType implements IStringSerializable {
        BLUEBERRY(0, "blueberry"),
        CHOCOLATE(1, "chocolate"),
        MINT(2, "mint"),
        plain(3, "plain"),
        STRAWBERRY(4, "strawberry"),
        VANILLA(5, "vanilla");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];

        static {
            for (EnumType blockplanks$enumtype : values()) {
                META_LOOKUP[blockplanks$enumtype.getMeta()] = blockplanks$enumtype;
            }
        }

        private final int meta;
        private final String name;

        EnumType(int metaIn, String nameIn) {
            this.meta = metaIn;
            this.name = nameIn;
        }

        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }
            return META_LOOKUP[meta];
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getMeta() {
            return meta;
        }
    }
}
