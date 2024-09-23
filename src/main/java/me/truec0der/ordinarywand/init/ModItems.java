package me.truec0der.ordinarywand.init;

import me.truec0der.ordinarywand.OrdinaryWand;
import me.truec0der.ordinarywand.content.item.LightningBallItem;
import me.truec0der.ordinarywand.content.item.WandItem;
import net.minecraft.world.item.Item;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ModItems {
    @RegistryName("ordinary_wand")
    Item ORDINARY_WAND = OrdinaryWand.MOD_TAB.add(new WandItem());

    @RegistryName("lightning_ball")
    Item LIGHTNING_BALL = new LightningBallItem();
}