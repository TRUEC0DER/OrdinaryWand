package me.truec0der.ordinarywand.init;

import net.minecraft.world.item.Items;
import org.zeith.hammerlib.annotations.ProvideRecipes;
import org.zeith.hammerlib.api.IRecipeProvider;
import org.zeith.hammerlib.event.recipe.RegisterRecipesEvent;

@ProvideRecipes
public class ModRecipes implements IRecipeProvider {
    @Override
    public void provideRecipes(RegisterRecipesEvent event) {
        event.shaped()
                .result(ModItems.ORDINARY_WAND)
                .shape("bcb", "csc", "brb")
                .map('b', Items.SWEET_BERRIES)
                .map('c', Items.CHICKEN)
                .map('s', Items.NETHER_STAR)
                .map('r', Items.BLAZE_ROD)
                .register();
    }
}