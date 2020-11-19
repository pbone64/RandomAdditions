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
    @Shadow private int scaledWidth;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Shadow private int scaledHeight;

    @Shadow @Final private MinecraftClient client;

    @Inject(method="renderStatusBars", at = @At("TAIL"))
    private void renderStatusBars(MatrixStack matrices, CallbackInfo ci) {
        client.getProfiler().push("armortoughness");
        PlayerEntity playerEntity = getCameraPlayer();
        int scaledWidthScaled = scaledWidth / 2;
        int armorToughness = MathHelper.floor(playerEntity.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
        int armorToughnessYPos = scaledHeight - 39 - (MathHelper.ceil(((float)playerEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) + (float)MathHelper.ceil(playerEntity.getAbsorptionAmount())) / 2.0F / 10.0F) - 1) * Math.max(10 - (MathHelper.ceil(((float)playerEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) + (float)MathHelper.ceil(playerEntity.getAbsorptionAmount())) / 2.0F / 10.0F) - 2), 3) - 10 + 11;

        client.getTextureManager().bindTexture(new Identifier(RandomAdditions.MOD_ID, "textures/icons/toughness.png"));

        for (int i = 0; i < 10; i++) {
            if (armorToughness > 0) {
                int armorToughnessXPos = scaledWidthScaled + (i * 8);

                if (i * 2 + 1 < armorToughness) {
                    drawTexture(matrices, armorToughnessXPos, armorToughnessYPos, 18, 0, 9, 9);
                }

                if (i * 2 + 1 == i) {
                    drawTexture(matrices, armorToughnessXPos, armorToughnessYPos, 9, 0, 9, 9);
                }

                if (i * 2 + 1 > i) {
                    drawTexture(matrices, armorToughnessXPos, armorToughnessYPos, 0, 0, 9, 9);
                }
            }
        }
    }
}
