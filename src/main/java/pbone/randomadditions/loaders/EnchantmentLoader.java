package pbone.randomadditions.loaders;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;
import pbone.randomadditions.content.enchantments.AutoSmeltEnchantment;
import pbone.randomadditions.content.enchantments.ProsperousDiggerEnchantment;
import pbone.randomadditions.content.enchantments.ProsperousWeaponEnchantment;
import pbone.randomadditions.utilities.LoaderUtils;

public class EnchantmentLoader {
    public static final Enchantment AUTO_SMELT = Registry.register(
            Registry.ENCHANTMENT,
            LoaderUtils.ModId("auto_smelt"),
            new AutoSmeltEnchantment()
    );

    public static final Enchantment PROSPEROUS_PICKAXE = Registry.register(
            Registry.ENCHANTMENT,
            LoaderUtils.ModId("prosperous_pickaxe"),
            new ProsperousDiggerEnchantment()
    );

    public static final Enchantment PROSPEROUS_SWORD = Registry.register(
            Registry.ENCHANTMENT,
            LoaderUtils.ModId("prosperous_sword"),
            new ProsperousWeaponEnchantment()
    );

    public static void onInitialize() {
    }
}
