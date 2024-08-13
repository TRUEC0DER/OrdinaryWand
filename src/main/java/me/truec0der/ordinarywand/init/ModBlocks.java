package me.truec0der.ordinarywand.init;

import me.truec0der.ordinarywand.block.ordinary_power.OrdinaryPowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ModBlocks {
    @RegistryName("ordinary_power")
    OrdinaryPowerBlock ORDINARY_POWER = new OrdinaryPowerBlock(BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .strength(4F)
            .requiresCorrectToolForDrops()
    );
}
