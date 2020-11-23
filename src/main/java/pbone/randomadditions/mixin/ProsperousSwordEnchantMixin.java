package pbone.randomadditions.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pbone.randomadditions.loaders.EnchantmentLoader;

@Mixin(LivingEntity.class)
public abstract class ProsperousSwordEnchantMixin extends Entity {
    @Shadow @Nullable protected PlayerEntity attackingPlayer;

    public ProsperousSwordEnchantMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow protected abstract int getCurrentExperience(PlayerEntity player);

    @Shadow protected abstract boolean shouldAlwaysDropXp();

    @Shadow protected int playerHitTimer;

    @Shadow protected abstract boolean canDropLootAndXp();

    @Inject(method = "dropXp", at = @At("HEAD"))
    protected void dropXp(CallbackInfo ci) {
        // NOTE: Don't cancel the vanilla method.
        // We want the vanilla method to be called since this just basically tries to duplicate the XP it would normally drop manually.
        if (attackingPlayer != null && EnchantmentHelper.getLevel(EnchantmentLoader.PROSPEROUS_SWORD, attackingPlayer.getMainHandStack()) > 0 && !world.isClient && (shouldAlwaysDropXp() || playerHitTimer > 0 && canDropLootAndXp() && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT))) {
            for (int i = 0; i < EnchantmentHelper.getLevel(EnchantmentLoader.PROSPEROUS_SWORD, attackingPlayer.getMainHandStack()); i++) {
                int currEXP = getCurrentExperience(attackingPlayer);

                while (currEXP > 0) {
                    int orbSize = ExperienceOrbEntity.roundToOrbSize(currEXP);
                    currEXP -= orbSize;

                    world.spawnEntity(new ExperienceOrbEntity(world, getX(), getY(), getZ(), orbSize));
                }
            }
        }
    }
}
