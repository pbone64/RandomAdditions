package pbone.randomadditions.mixin;

import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import pbone.randomadditions.content.items.QuiverItem;

@Mixin(BowItem.class)
public class QuiverMixin {
    @Redirect(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private void consumeQuiverArrow(ItemStack itemStack, int amount) {
        if (itemStack.getItem() instanceof QuiverItem) {
            QuiverItem quiver = (QuiverItem)itemStack.getItem();
        } else {
            itemStack.decrement(amount);
        }
    }
}
