package pbone.randomadditions.loaders;

import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import pbone.randomadditions.content.items.QuiverItem;
import pbone.randomadditions.content.items.TestItem;

public class ItemLoader {
    public static final TestItem TEST_ITEM = new TestItem(new FabricItemSettings().group(ItemGroup.MISC).rarity((Rarity.EPIC)));
    public static final QuiverItem QUIVER = new QuiverItem(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));

    public static void onInitialize(){
        Registry.register(Registry.ITEM, LoaderUtils.ModId("test_item"), TEST_ITEM);
        Registry.register(Registry.ITEM, LoaderUtils.ModId("quiver"), QUIVER);
    }
}
