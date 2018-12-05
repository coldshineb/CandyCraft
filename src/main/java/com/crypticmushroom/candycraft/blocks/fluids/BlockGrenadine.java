package com.crypticmushroom.candycraft.blocks.fluids;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.init.CCFluids;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.crypticmushroom.candycraft.CandyCraft.MODID;

public class BlockGrenadine extends BlockFluidClassic {

    public static final ResourceLocation GRENADINE = new ResourceLocation(MODID, "grenadine");

    public BlockGrenadine() {
        super(CCFluids.GRENADINE, Material.WATER);
        setCreativeTab(CandyCraft.ccTab);
        setRegistryName("grenadine");
        setTranslationKey(MODID + ".grenadine");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation fluidLocation = new ModelResourceLocation(GRENADINE, "fluid");
        StateMapperBase customState = new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState iBlockState) {
                return fluidLocation;
            }
        };
        ModelLoader.setCustomStateMapper(this, customState);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(GRENADINE, "inventory"));
    }
}
