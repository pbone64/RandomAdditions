package pbone.randomadditions;

import net.fabricmc.api.ModInitializer;
import pbone.randomadditions.loaders.EnchantmentLoader;
import pbone.randomadditions.loaders.ItemLoader;

public class RandomAdditions implements ModInitializer {
    public static final String MOD_ID = "randomadditions";

    @Override
    public void onInitialize() {
        // Initialize our loaders.
        ItemLoader.onInitialize();
        EnchantmentLoader.onInitialize();
    }
}
