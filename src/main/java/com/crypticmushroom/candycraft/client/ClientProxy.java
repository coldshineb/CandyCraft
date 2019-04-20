package com.crypticmushroom.candycraft.client;

import com.crypticmushroom.candycraft.CommonProxy;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityAlchemy;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityEgg;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import com.crypticmushroom.candycraft.client.entity.models.*;
import com.crypticmushroom.candycraft.client.entity.renders.*;
import com.crypticmushroom.candycraft.client.tileentity.RenderEgg;
import com.crypticmushroom.candycraft.client.tileentity.TileEntityAlchemyRenderer;
import com.crypticmushroom.candycraft.client.tileentity.TileEntityCandyChestRenderer;
import com.crypticmushroom.candycraft.client.tileentity.TileEntityRendererTeleporter;
import com.crypticmushroom.candycraft.entity.*;
import com.crypticmushroom.candycraft.entity.boss.*;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {
    public static IRenderHandler weatherRenderer = new RenderWeather();
    public static ModelBiped crown = new ModelBiped(0.1F);
    public static KeyBinding unleashMountPower = new KeyBinding("key.mountPower.desc", Keyboard.KEY_P, "key.candycraft.category");

    @Override
    public void doPreLoadRegistration() {

        RenderingRegistry.registerEntityRenderingHandler(EntityCandyPig.class, RenderCandyPig::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCandyCreeper.class, RenderCandyCreeper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySuguard.class, RenderSuguard::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMageSuguard.class, RenderMageSuguard::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCandyWolf.class, RenderCandyWolf::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityJellyQueen.class, m -> new RenderJellyQueen(m, new ModelSlime(16)));
        RenderingRegistry.registerEntityRenderingHandler(EntityBunny.class, m -> new RenderBunny(m, new ModelBunny(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, m -> new RenderBee(m, new ModelBee(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGingerBreadMan.class, m -> new RenderGingerBreadMan(m, new ModelBiped(0.0F), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCandyArrow.class, RenderCandyArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGummyBall.class, m -> new RenderGummyBall<>(m, CCItems.gummy_ball, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityBossSuguard.class, RenderSuguardeBoss::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCottonCandySpider.class, RenderCottonSpider::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFish.class, m -> new RenderEntityFish(m, new ModelFish(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityNessie.class, RenderNessie::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityWaffleSheep.class, m -> new RenderWaffleSheep(m, new ModelWaffleSheep2(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityYellowJelly.class, m -> new RenderSprinter(m, new ModelSlime(16)));
        RenderingRegistry.registerEntityRenderingHandler(EntityRedJelly.class, m -> new RenderKamikazeJelly(m, new ModelSlime(16)));
        RenderingRegistry.registerEntityRenderingHandler(EntityTornadoJelly.class, m -> new RenderTornadoJelly(m, new ModelSlime(16)));
        RenderingRegistry.registerEntityRenderingHandler(EntityPEZJelly.class, m -> new RenderPEZJelly(m, new ModelSlime(16)));
        RenderingRegistry.registerEntityRenderingHandler(EntityKingSlime.class, m -> new RenderKingJelly(m, new ModelSlime(16)));
        RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, m -> new RenderDynamite<>(m, false, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGlueDynamite.class, m -> new RenderDynamite<>(m, true, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityNougatGolem.class, RenderNougatGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, m -> new RenderBeetle(m, new ModelBeetle(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBossBeetle.class, m -> new RenderBossBeetle(m, new ModelBeetle(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityPingouin.class, m -> new RenderPingouin(m, new ModelPingouin(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityDragon.class, m -> new RenderDragon(m, new ModelDragon(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityKingBeetle.class, m -> new RenderKingBeetle(m, new ModelBeetle(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMermaid.class, RenderMermaid::new);

        TileEntityCandyChestRenderer render = new TileEntityCandyChestRenderer();
        TileEntityRendererDispatcher.instance.renderers.put(TileEntityCandyChest.class, render);
        render.setRendererDispatcher(TileEntityRendererDispatcher.instance);

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemy.class, new TileEntityAlchemyRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeleporter.class, new TileEntityRendererTeleporter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEgg.class, new RenderEgg());

        crown.bipedHead = new ModelRenderer(crown, 0, 0);
        crown.bipedHead.addBox(-4.0F, -9.5F, -4.0F, 8, 8, 8, 0.1F);
        crown.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    }

    @Override
    public void init() {
        ClientRegistry.registerKeyBinding(ClientProxy.unleashMountPower);
    }
}
