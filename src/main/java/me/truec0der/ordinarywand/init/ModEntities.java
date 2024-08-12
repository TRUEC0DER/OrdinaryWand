package me.truec0der.ordinarywand.init;

import me.truec0der.ordinarywand.entity.lightning_ball.LightningBall;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ModEntities {
    @RegistryName("lightning_ball")
    EntityType<LightningBall> LIGHTNING_BALL = EntityType.Builder.of(LightningBall::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
            .build("lightning_ball");
}
