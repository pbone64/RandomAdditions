package pbone.randomadditions.loaders;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import pbone.randomadditions.content.items.TestItem;

public class ItemLoader {
    public static final TestItem TEST_ITEM = new TestItem(new FabricItemSettings().group(ItemGroup.MISC).rarity((Rarity.EPIC)));

    public static void onInitialize(){
        Registry.register(Registry.ITEM, new Identifier("randomadditions", "test_item"), TEST_ITEM);
    }
}
