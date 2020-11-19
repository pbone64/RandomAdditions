package pbone.randomadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import pbone.randomadditions.loaders.EnchantmentLoader;
import pbone.randomadditions.loaders.ItemLoader;

public class RandomAdditions implements ModInitializer {
    public static final String MOD_ID = "randomadditions";
    public static ModContainer RandomAdditionsMod;
    public static String OptionsVersion = "0.1.0";

    @Override
    public void onInitialize() {
        // Initialize our loaders.
        ItemLoader.onInitialize();
        EnchantmentLoader.onInitialize();

        for (ModContainer mod : FabricLoader.getInstance().getAllMods())
            if (mod.getMetadata().getName().equals("Random Additions"))
                RandomAdditionsMod = mod;
    }
}
