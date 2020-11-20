package pbone.randomadditions.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pbone.randomadditions.RandomAdditions;

@Mixin(InGameHud.class)
public abstract class ArmorToughnessMixin extends DrawableHelper {
    private static final Identifier ICONS = new Identifier(RandomAdditions.MOD_ID, "textures/gui/icons.png");

    @Shadow private int scaledWidth;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Shadow private int scaledHeight;

    @Shadow @Final private MinecraftClient client;

    @Inject(method="renderStatusBars", at = @At("TAIL"))
    private void renderStatusBars(MatrixStack matrices, CallbackInfo ci) {
        client.getTextureManager().bindTexture(ICONS);
        client.getProfiler().push("armortoughness");

        PlayerEntity playerEntity = getCameraPlayer();
        int scaledScaledHeight = this.scaledHeight - 39;
        int scaledScaledWidth = scaledWidth / 2 - 91;
        float maxHealth = (float)playerEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);
        int absorptionAmount = MathHelper.ceil(playerEntity.getAbsorptionAmount());
        int yModifier = MathHelper.ceil((maxHealth + (float)absorptionAmount) / 2.0F / 10.0F);
        int clampedYModifier = Math.max(10 - (yModifier - 2), 3);
        int origY = scaledScaledHeight - (yModifier - 1) * clampedYModifier - 10;
        int armorToughness = (int)playerEntity.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS);

        for(int i = 0; i < 10; i++) {
            if (armorToughness > 0) {
                int origX = scaledScaledWidth + i * 8;
                if (i * 2 + 1 < armorToughness) {
                    this.drawTexture(matrices, origX, origY, 34, 9, 9, 9);
                }

                if (i * 2 + 1 == armorToughness) {
                    this.drawTexture(matrices, origX, origY, 25, 9, 9, 9);
                }

                if (i * 2 + 1 > armorToughness) {
                    this.drawTexture(matrices, origX, origY, 16, 9, 9, 9);
                }
            }
        }

        client.getProfiler().pop();
    }
}
