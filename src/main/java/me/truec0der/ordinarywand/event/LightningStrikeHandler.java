package me.truec0der.ordinarywand.event;

import me.truec0der.ordinarywand.OrdinaryWand;
import me.truec0der.ordinarywand.init.ModBlocks;
import me.truec0der.ordinarywand.init.ModTiles;
import me.truec0der.ordinarywand.content.tile.OrdinaryPowerTile;
import me.truec0der.ordinarywand.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = OrdinaryWand.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LightningStrikeHandler {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();
        BlockPos entityPos = entity.blockPosition();

        int maxEnergy = 16000;

        if (entity.getType() != EntityType.LIGHTNING_BOLT) return;

        List<BlockPos> nearBlocks = BlockUtil.findBlocksInRadius(level, entityPos, 8, ModBlocks.ORDINARY_POWER);
        nearBlocks.forEach(blockPos -> {
            BlockState blockState = level.getBlockState(blockPos);

            boolean isValidBlock = ModTiles.ORDINARY_POWER.isValid(blockState);
            if (!isValidBlock) return;

            OrdinaryPowerTile blockEntity = (OrdinaryPowerTile) level.getBlockEntity(blockPos);
            if (blockEntity == null) return;

            int distance = (int) Math.sqrt(entityPos.distSqr(blockPos));
            int finalEnergy = maxEnergy / (distance == 0 ? 1 : distance);

            blockEntity.addEnergy(finalEnergy);

            BlockState newState = level.getBlockState(blockPos);
            level.sendBlockUpdated(blockPos, newState, newState, 3);
        });
    }
}
