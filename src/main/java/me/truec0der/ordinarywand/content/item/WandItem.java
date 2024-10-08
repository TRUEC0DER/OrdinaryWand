package me.truec0der.ordinarywand.content.item;

import me.truec0der.ordinarywand.content.entity.LightningBallEntity;
import me.truec0der.ordinarywand.init.ModEntities;
import me.truec0der.ordinarywand.util.ParticleUtil;
import me.truec0der.ordinarywand.util.VectorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WandItem extends BaseItem {
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        if (player.isShiftKeyDown()) return spawnFox(level, player, hand);
        return spawnLightning(level, player, hand);
    }

    private InteractionResultHolder<ItemStack> spawnFox(Level level, Player player, InteractionHand hand) {
        int spawnDistance = 10;

        Vec3 playerLookVec = VectorUtil.getLookAtPosition(player, spawnDistance);
        Vec3 highestLookVec = VectorUtil.getHighestPosition(level, playerLookVec);
        BlockPos highestLookPos = new BlockPos((int) highestLookVec.x(), (int) highestLookVec.y(), (int) highestLookVec.z());

        Fox fox = new Fox(EntityType.FOX, level);
        fox.setPos(highestLookVec);

        level.addFreshEntity(fox);

        ParticleUtil.spawnParticlesInRadius(
                ParticleTypes.FIREWORK,
                level,
                highestLookVec,
                2,
                50
        );

        List<Player> nearestPlayers = level.getEntitiesOfClass(Player.class, new AABB(highestLookPos).inflate(spawnDistance * 2));

        for (Player nearestPlayer : nearestPlayers) {
            nearestPlayer.playSound(SoundEvents.FIREWORK_ROCKET_BLAST_FAR, 1.0F, 1.0F);
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    private InteractionResultHolder<ItemStack> spawnLightning(Level level, Player player, InteractionHand hand) {
        Vec3 playerLookVec = player.getLookAngle();

        LightningBallEntity lightningBall = new LightningBallEntity(ModEntities.LIGHTNING_BALL, level);
        lightningBall.setPos(player.getX(), player.getEyeY(), player.getZ());

        lightningBall.shoot(playerLookVec.x, playerLookVec.y, playerLookVec.z, 1.0F, 1.5F);

        level.addFreshEntity(lightningBall);

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
