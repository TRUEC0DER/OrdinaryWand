package me.truec0der.ordinarywand.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class VectorUtil {
    public static Vec3 getLookAtPosition(Player player, double distance) {
        Vec3 lookVector = player.getLookAngle();

        Vec3 playerPosition = player.position();

        return playerPosition.add(lookVector.scale(distance));
    }

    public static Vec3 getHighestPosition(Level level, Vec3 position) {
        BlockPos startPos = new BlockPos((int) position.x, level.getMaxBuildHeight() - 1, (int) position.z);

        for (int y = startPos.getY(); y >= 0; y--) {
            BlockPos testPos = new BlockPos((int) position.x, y, (int) position.z);
            BlockState state = level.getBlockState(testPos);

            if (state.getBlock() != Blocks.AIR) {
                return testPos.getCenter().add(0, 1, 0);
            }
        }

        return new BlockPos((int) position.x, (int) position.y, (int) position.z).getCenter();
    }
}
