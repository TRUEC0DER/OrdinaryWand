package me.truec0der.ordinarywand.tile;

import me.truec0der.ordinarywand.init.ModTiles;
import me.truec0der.ordinarywand.util.ParticleUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.zeith.hammerlib.api.io.NBTSerializable;
import org.zeith.hammerlib.api.tiles.IContainerTile;
import org.zeith.hammerlib.tiles.TileSyncableTickable;
import org.zeith.hammerlib.util.mcf.NormalizedTicker;

public class OrdinaryPowerTile extends TileSyncableTickable implements IEnergyStorage, IContainerTile {
    private final LazyOptional<IEnergyStorage> energyStorageTile = LazyOptional.of(() -> OrdinaryPowerTile.this);
    private final int maxTransferRate = 1000;
    @NBTSerializable("Energy")
    private long energy;
    private final NormalizedTicker ticker = NormalizedTicker.create(this::powerTick);

    public OrdinaryPowerTile(BlockPos pos, BlockState state) {
        super(ModTiles.ORDINARY_POWER, pos, state);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ForgeCapabilities.ENERGY ? energyStorageTile.cast() : super.getCapability(cap, side);
    }

    @Override
    public void update() {
        ticker.tick(level);

        ParticleUtil.spawnParticlesInRadius(
                ParticleTypes.CLOUD,
                level,
                getBlockPos().getCenter().add(0, 1, 0),
                0.5,
                1
        );
    }

    @Override
    public boolean atTickRate(int rate) {
        return ticker.atTickRate(rate);
    }

    public void powerTick(int suppressed) {
        if (!level.isClientSide) {
            distributeEnergy();
        }

        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
    }

    private void distributeEnergy() {
        for (Direction direction : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(worldPosition.relative(direction));
            if (neighbor == null) continue;

            LazyOptional<IEnergyStorage> neighborEnergy = neighbor.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite());
            if (!neighborEnergy.isPresent()) continue;

            IEnergyStorage energyStorage = neighborEnergy.orElseThrow(IllegalStateException::new);
            if (!canExtract() || !energyStorage.canReceive()) continue;

            int energyToTransfer = Math.min((int) energy, maxTransferRate);
            int acceptedEnergy = energyStorage.receiveEnergy(energyToTransfer, false);

            if (acceptedEnergy > 0) extractEnergy(acceptedEnergy, false);
        }
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(maxExtract, (int) energy);
        if (!simulate) {
            energy -= energyExtracted;
        }

        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return (int) Math.min(energy, Integer.MAX_VALUE);
    }

    @Override
    public int getMaxEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canExtract() {
        return energy > 0;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    public long getEnergy() {
        return energy;
    }

    public void addEnergy(long energy) {
        int finalEnergy = (int) (this.energy + energy);
        if (finalEnergy > getMaxEnergyStored()) return;
        this.energy = finalEnergy;
    }
}