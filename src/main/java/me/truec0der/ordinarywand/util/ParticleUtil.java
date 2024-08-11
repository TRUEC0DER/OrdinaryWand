package me.truec0der.ordinarywand.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ParticleUtil {
    public static void spawnParticlesInRadius(ParticleOptions particle, Level level, Vec3 center, double radius, int count) {
        if (level.isClientSide) {
            Random random = new Random();

            for (int i = 0; i < count; i++) {
                double angle = random.nextDouble() * 2 * Math.PI;

                double distance = random.nextDouble() * radius;

                double offsetX = distance * Math.cos(angle);
                double offsetZ = distance * Math.sin(angle);
                double offsetY = (random.nextDouble() - 0.5) * 2 * radius;

                double x = center.x + offsetX;
                double y = center.y + offsetY;
                double z = center.z + offsetZ;

                level.addParticle(
                        particle,
                        x,
                        y,
                        z,
                        0.0D,
                        0.0D,
                        0.0D
                );
            }
        }
    }
}
