package me.truec0der.ordinarywand.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.zeith.hammerlib.api.fml.ICustomRegistrar;
import org.zeith.hammerlib.util.java.Cast;

import java.util.function.Supplier;

public class EntityFactory<T extends Entity> implements ICustomRegistrar {
    public final EntityType<T> entityType;

    public EntityFactory(Supplier<EntityType<T>> typeSupplier) {
        this.entityType = typeSupplier.get();
    }

    public static <T extends Entity> Builder<T> builder(Supplier<EntityType<T>> typeSupplier) {
        return new Builder<>(typeSupplier);
    }

    @Override
    public void performRegister(RegisterEvent event, ResourceLocation entityId) {
        var key = event.getRegistryKey();

        if (ForgeRegistries.Keys.ENTITY_TYPES.equals(key)) {
            event.register(
                    ForgeRegistries.Keys.ENTITY_TYPES,
                    entityId,
                    Cast.constant(entityType)
            );
        }
    }

    public static class Builder<T extends Entity> {
        protected final Supplier<EntityType<T>> typeSupplier;

        public Builder(Supplier<EntityType<T>> typeSupplier) {
            this.typeSupplier = typeSupplier;
        }

        public EntityFactory<T> build() {
            return new EntityFactory<>(typeSupplier);
        }
    }
}
