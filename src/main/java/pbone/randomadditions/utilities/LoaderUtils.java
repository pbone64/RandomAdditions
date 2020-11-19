package pbone.randomadditions.utilities;

import pbone.randomadditions.RandomAdditions;
import net.minecraft.util.Identifier;

public class LoaderUtils {
    public static Identifier ModId(String id) {
        return new net.minecraft.util.Identifier(RandomAdditions.MOD_ID, id);
    }
}
