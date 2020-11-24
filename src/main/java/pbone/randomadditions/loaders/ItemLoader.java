package pbone.randomadditions.loaders;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import pbone.randomadditions.content.items.QuiverItem;
import pbone.randomadditions.content.items.TestItem;
import pbone.randomadditions.content.items.curios.RingItem;
import pbone.randomadditions.utilities.LoaderUtils;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;

public class ItemLoader {
    public static final TestItem TEST_ITEM = new TestItem(new FabricItemSettings().group(ItemGroup.MISC).rarity((Rarity.EPIC)));
    public static final QuiverItem QUIVER = new QuiverItem(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));

    public static final RingItem RING_BASE = new RingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
    public static final RingItem RING_LUCK = new RingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));

    public static void onInitialize() {
        Registry.register(Registry.ITEM, LoaderUtils.ModId("test_item"), TEST_ITEM);
        Registry.register(Registry.ITEM, LoaderUtils.ModId("quiver"), QUIVER);
        Registry.register(Registry.ITEM, LoaderUtils.ModId("ring_base"), RING_BASE);
        Registry.register(Registry.ITEM, LoaderUtils.ModId("ring_luck"), RING_LUCK);

        registerCurioAttributeModifier(RING_LUCK, EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier("Luck bonus", 1.0d, EntityAttributeModifier.Operation.ADDITION));
    }

    public static void registerCurioAttributeModifier(Item curio, EntityAttribute attribute, EntityAttributeModifier modifier) {
        ItemComponentCallbackV2.event(curio).register(
                ((item, itemStack, componentContainer) -> componentContainer
                        .put(CuriosComponent.ITEM, new ICurio() {
                            @Override
                            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                                    String identifier) {
                                Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap
                                        .create();

                                if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem())
                                        .contains(identifier)) {
                                    attributes.put(attribute, modifier);
                                }
                                return attributes;
                            }
                        })));
     }
}
