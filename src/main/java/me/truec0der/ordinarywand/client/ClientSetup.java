package me.truec0der.ordinarywand.client;

import me.truec0der.ordinarywand.OrdinaryWand;
import me.truec0der.ordinarywand.entity.lightning_ball.LightningBallModel;
import me.truec0der.ordinarywand.entity.lightning_ball.LightningBallRenderer;
import me.truec0der.ordinarywand.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdinaryWand.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LIGHTNING_BALL.get(), LightningBallRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntities.LIGHTNING_BALL_MODEL, LightningBallModel::createLayer);
    }
}
