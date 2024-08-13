package me.truec0der.ordinarywand.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockUtil {
    public static List<BlockPos> findBlocksInRadius(Level level, BlockPos centerPos, int radius, Block targetBlock) {
        List<BlockPos> foundBlocks = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = centerPos.offset(x, y, z);
                    if (level.getBlockState(checkPos).getBlock() == targetBlock) {
                        foundBlocks.add(checkPos);
                    }
                }
            }
        }

        return foundBlocks;
    }
}
