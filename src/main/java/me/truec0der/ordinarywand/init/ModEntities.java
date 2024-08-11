package me.truec0der.ordinarywand.init;

import me.truec0der.ordinarywand.OrdinaryWand;
import me.truec0der.ordinarywand.entity.lightning_ball.LightningBall;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdinaryWand.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OrdinaryWand.MOD_ID);

    public static final RegistryObject<EntityType<LightningBall>> LIGHTNING_BALL = ENTITY_TYPES.register("lightning_ball",
            () -> EntityType.Builder.of(LightningBall::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .build("lightning_ball"));

    public static ModelLayerLocation LIGHTNING_BALL_MODEL = new ModelLayerLocation(OrdinaryWand.id("textures/lightning_ball"), "lightning_ball");
}
