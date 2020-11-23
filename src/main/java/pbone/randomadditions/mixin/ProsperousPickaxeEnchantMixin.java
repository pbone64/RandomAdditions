package pbone.randomadditions.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pbone.randomadditions.loaders.EnchantmentLoader;

import java.util.Random;

@Mixin(OreBlock.class)
public abstract class ProsperousPickaxeEnchantMixin extends Block {
    public ProsperousPickaxeEnchantMixin(Settings settings) {
        super(settings);
    }

    @Shadow protected abstract int getExperienceWhenMined(Random random);

    @Inject(method="onStacksDropped", at = @At("TAIL"))
    public void onStacksDropped(BlockState blockState, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        // NOTE TO SELF: Don't cancel the vanilla method.
        // I still the vanilla method to be called since this just basically tries to duplicate the XP it would normally drop manually.
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            for (int i = 0; i < EnchantmentHelper.getLevel(EnchantmentLoader.PROSPEROUS_PICKAXE, stack); i++) {
                int exp = getExperienceWhenMined(world.random);

                if (exp > 0) {
                    dropExperience(world, pos, exp);
                }
            }
        }
    }
}
