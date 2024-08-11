package me.truec0der.ordinarywand.item;

import net.minecraft.world.item.Item;

public abstract class AbstractItem extends Item {
    public AbstractItem() {
        this(new Item.Properties());
    }

    public AbstractItem(Properties properties) {
        super(properties);
    }
}
