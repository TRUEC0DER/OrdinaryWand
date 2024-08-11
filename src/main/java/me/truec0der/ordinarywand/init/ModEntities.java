package me.truec0der.ordinarywand.init;

import me.truec0der.ordinarywand.OrdinaryWand;
import me.truec0der.ordinarywand.entity.EntityFactory;
import me.truec0der.ordinarywand.entity.lightning_ball.LightningBall;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.zeith.hammerlib.annotations.OnlyIf;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ModEntities {
    @RegistryName("lightning_ball")
    EntityFactory<LightningBall> LIGHTNING_BALL = EntityFactory.builder(() -> {
        return EntityType.Builder.of(LightningBall::new, MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .build("lightning_ball");
    }).build();
}
