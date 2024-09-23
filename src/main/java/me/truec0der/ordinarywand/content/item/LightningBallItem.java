package me.truec0der.ordinarywand.content.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LightningBallItem extends BaseItem {
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, InteractionHand hand) {
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
