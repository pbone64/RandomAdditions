package pbone.randomadditions.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pbone.randomadditions.loaders.EnchantmentLoader;

@Mixin(Block.class)
public abstract class AutoSmeltMixin {
    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;"),
            cancellable = true)
    private static void dropStacksMixin(BlockState state, World world, BlockPos pos, @Nullable BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfo ci) {
        // Only run on players
        if (entity instanceof PlayerEntity) {
            // Only run if you have autosmelt
            if (EnchantmentHelper.getLevel(EnchantmentLoader.AUTO_SMELT, ((PlayerEntity) entity).getMainHandStack()) > 0) {
                // Get all items the block should drop
                Block.getDroppedStacks(state, (ServerWorld) world, pos, null).forEach(itemStack -> {
                    ItemStack drop = itemStack;
                    // See if there's a smelted recipe that takes this one of the blocks drops
                    SmeltingRecipe recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory((itemStack)), world).orElse(null);
                    if (recipe != null) {
                        // Drop it if there is
                        drop = recipe.getOutput();
                    }
                    // Drop the smelted version
                    Block.dropStack(world, pos, drop);

                    // Don't run the vanilla dropping code if I ran my own
                    if (!ci.isCancelled())
                        ci.cancel();
                });
            }
        }

        // Normally called by vanilla, call it myself
        if (ci.isCancelled())
            state.onStacksDropped((ServerWorld) world, pos, ItemStack.EMPTY);
    }
}
