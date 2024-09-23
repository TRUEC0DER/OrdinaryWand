package me.truec0der.ordinarywand.content.block;

import me.truec0der.ordinarywand.OrdinaryWand;
import me.truec0der.ordinarywand.content.tile.OrdinaryPowerTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zeith.hammerlib.api.blocks.ICreativeTabBlock;
import org.zeith.hammerlib.api.forge.BlockAPI;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.BlockHarvestAdapter;

public class OrdinaryPowerBlock extends BaseEntityBlock implements ICreativeTabBlock {
    public OrdinaryPowerBlock(Properties properties) {
        super(properties);
        BlockHarvestAdapter.bindTool(BlockHarvestAdapter.MineableType.PICKAXE, Tiers.IRON, this);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OrdinaryPowerTile(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return BlockAPI.ticker();
    }

    @Override
    public @NotNull CreativeTab getCreativeTab() {
        return OrdinaryWand.MOD_TAB;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
