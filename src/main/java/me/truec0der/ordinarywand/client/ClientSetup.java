package me.truec0der.ordinarywand.client;

import me.truec0der.ordinarywand.OrdinaryWand;
import me.truec0der.ordinarywand.client.model.LightningBallModel;
import me.truec0der.ordinarywand.client.render.entity.LightningBallRenderer;
import me.truec0der.ordinarywand.init.ModEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdinaryWand.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static ModelLayerLocation LIGHTNING_BALL_MODEL = new ModelLayerLocation(OrdinaryWand.id("textures/lightning_ball"), "lightning_ball");

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LIGHTNING_BALL, LightningBallRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LIGHTNING_BALL_MODEL, LightningBallModel::createLayer);
    }
}
