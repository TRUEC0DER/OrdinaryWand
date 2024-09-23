package me.truec0der.ordinarywand.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import me.truec0der.ordinarywand.content.entity.LightningBallEntity;
import me.truec0der.ordinarywand.init.ModItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LightningBallRenderer extends ThrownItemRenderer<LightningBallEntity> implements ItemSupplier {
    public LightningBallRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(@NotNull LightningBallEntity entity, float entityYaw, float partialTick, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource bufferSource, int light) {
        super.render(entity, entityYaw, partialTick, matrixStack, bufferSource, light);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return ModItems.LIGHTNING_BALL.getDefaultInstance();
    }
}
