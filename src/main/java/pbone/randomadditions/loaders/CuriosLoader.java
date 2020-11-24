package pbone.randomadditions.loaders;

import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo;
import top.theillusivec4.curios.api.SlotTypePreset;

public class CuriosLoader {
    public static final String RING_ID = SlotTypePreset.RING.getIdentifier();

    public static void onInitialize() {
        registerSlot(SlotTypePreset.RING.getInfoBuilder().size(2).build());
        registerSlot(SlotTypePreset.BACK.getInfoBuilder().build());
        registerSlot(SlotTypePreset.CHARM.getInfoBuilder().build());
    }

    private static void registerSlot(SlotTypeInfo info) {
        CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, info);
    }
}
