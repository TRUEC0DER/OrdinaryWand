package me.truec0der.ordinarywand.content.item;

import net.minecraft.world.item.Item;

public abstract class BaseItem extends Item {
    public BaseItem() {
        this(new Item.Properties());
    }

    public BaseItem(Properties properties) {
        super(properties);
    }
}
