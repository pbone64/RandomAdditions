package pbone.randomadditions.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pbone.randomadditions.gui.RandomAdditionsOptionsScreen;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init",
            at = @At("TAIL"))
    private void addConfigButtonOptions(CallbackInfo ci) {
        boolean modMenuLoaded = false;

        for (ModContainer mod : FabricLoader.getInstance().getAllMods())
            if (mod.getMetadata().getName().equals("Mod Menu"))
                //modMenuLoaded = true;

        if (!modMenuLoaded)
            this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 144 - 6, 200, 20, new TranslatableText("gui.randomadditions.randomadditionsoptions"), (buttonWidget) -> {
                MinecraftClient.getInstance().openScreen(new RandomAdditionsOptionsScreen(this));
            }));
    }
}
