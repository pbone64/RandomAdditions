package pbone.randomadditions.utilities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;

// If we ever need to create more than 1 options screen, I plan on making them extend from a base Options class so this class and stuff will actually be useful.
public class OptionUtils {
    public static CompoundTag DataFix(CompoundTag tag) {
        int version = 0;

        try {
            version = Integer.parseInt(tag.getString(("version")));
        } catch (Exception ignored) {
        }

        return NbtHelper.update(MinecraftClient.getInstance().getDataFixer(), DataFixTypes.OPTIONS, tag, version);
    }
}