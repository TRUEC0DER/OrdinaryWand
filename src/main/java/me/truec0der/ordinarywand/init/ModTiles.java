package me.truec0der.ordinarywand.init;

import me.truec0der.ordinarywand.tile.OrdinaryPowerTile;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.hammerlib.api.forge.BlockAPI;

@SimplyRegister
public interface ModTiles {
    @RegistryName("ordinary_power")
    BlockEntityType<OrdinaryPowerTile> ORDINARY_POWER = BlockAPI.createBlockEntityType(OrdinaryPowerTile::new, ModBlocks.ORDINARY_POWER);
}
