package me.truec0der.ordinarywand.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

public class VectorUtil {
    public static Vec3 getLookAtPosition(Player player, double distance) {
        Vec3 lookVector = player.getLookAngle();
        Vec3 playerPosition = player.position();

        return playerPosition.add(lookVector.scale(distance));
    }

    public static Vec3 getHighestPosition(Level level, Vec3 position) {
        int highestY = level.getHeight(Heightmap.Types.WORLD_SURFACE, (int) position.x, (int) position.z);
        BlockPos highestPos = new BlockPos((int) position.x, highestY, (int) position.z);

        return highestPos.getCenter().add(0, 1, 0);
    }
}
