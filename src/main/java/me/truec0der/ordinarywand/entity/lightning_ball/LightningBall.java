package me.truec0der.ordinarywand.entity.lightning_ball;

import me.truec0der.ordinarywand.init.ModItems;
import me.truec0der.ordinarywand.util.ParticleUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class LightningBall extends Projectile implements ItemSupplier {
    Level level;

    public LightningBall(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
        this.level = level;
        this.clearFire();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        handleHit(result.getLocation());
    }

    @Override
    protected void onHit(HitResult result) {
        handleHit(result.getLocation());
    }

    private void handleHit(Vec3 location) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setPos(location.x, location.y, location.z);
        level.addFreshEntity(lightningBolt);

        remove();
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3 direction = new Vec3(x, y, z).normalize().scale(velocity);
        this.setDeltaMovement(direction);
    }

    @Override
    public void tick() {
        Entity entity = this.getOwner();
        Level tickLevel = this.level();
        if (tickLevel.isClientSide || (entity == null || !entity.isRemoved()) && tickLevel.hasChunkAt(this.blockPosition())) {
            super.tick();

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
            }

            this.checkInsideBlocks();

            Vec3 vec3 = this.getDeltaMovement();
            double d0 = this.getX() + vec3.x;
            double d1 = this.getY() + vec3.y;
            double d2 = this.getZ() + vec3.z;

            ProjectileUtil.rotateTowardsMovement(this, 0.2F);

            float f = 0.25F;
            if (this.isInWater()) {
                for (int i = 0; i < 4; ++i) {
                    tickLevel.addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25D, d1 - vec3.y * 0.25D, d2 - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
                }

                f = 0.8F;
            }

            Vec3 newMovement = vec3.normalize().scale(f);
            this.setDeltaMovement(newMovement);

            double speedThreshold = 0.05;
            if (!(newMovement.lengthSqr() < speedThreshold * speedThreshold)) {
                ParticleUtil.spawnParticlesInRadius(
                        ParticleTypes.ELECTRIC_SPARK,
                        tickLevel,
                        this.blockPosition().getCenter(),
                        0.5,
                        10
                );
            }

            this.setPos(d0, d1, d2);
        } else {
            remove();
        }
    }

    public void remove() {
        ParticleUtil.spawnParticlesInRadius(
                ParticleTypes.ASH,
                level(),
                this.blockPosition().getCenter(),
                0.5,
                15
        );

        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public @NotNull ItemStack getItem() {
        return ModItems.LIGHTNING_BALL.getDefaultInstance();
    }
}
