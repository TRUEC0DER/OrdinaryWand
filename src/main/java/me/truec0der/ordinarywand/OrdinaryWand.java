package me.truec0der.ordinarywand;

import me.truec0der.ordinarywand.init.ModEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.proxy.HLConstants;

@Mod(OrdinaryWand.MOD_ID)
public class OrdinaryWand {
    public static final String MOD_ID = "ordinarywand";

    @CreativeTab.RegisterTab
    public static final CreativeTab MOD_TAB = new CreativeTab(id("root"),
            builder -> builder
                    .icon(Items.SWEET_BERRIES::getDefaultInstance)
                    .withTabsBefore(HLConstants.HL_TAB.id())
    );

    public OrdinaryWand(IEventBus bus) {
        LanguageAdapter.registerMod(MOD_ID);

        ModEntities.ENTITY_TYPES.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}