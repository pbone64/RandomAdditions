package pbone.randomadditions.loaders;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;
import pbone.randomadditions.content.enchantments.AutoSmeltEnchantment;
import pbone.randomadditions.utilities.LoaderUtils;

public class EnchantmentLoader {
    public static final Enchantment AUTO_SMELT = Registry.register(
            Registry.ENCHANTMENT,
            LoaderUtils.ModId("auto_smelt"),
            new AutoSmeltEnchantment()
    );

    public static void onInitialize() {
    }
}
