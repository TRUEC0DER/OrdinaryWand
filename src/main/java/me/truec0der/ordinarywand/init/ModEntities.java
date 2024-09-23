package me.truec0der.ordinarywand.init;

import me.truec0der.ordinarywand.content.entity.LightningBallEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ModEntities {
    @RegistryName("lightning_ball")
    EntityType<LightningBallEntity> LIGHTNING_BALL = EntityType.Builder.of(LightningBallEntity::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
            .build("lightning_ball");
}
