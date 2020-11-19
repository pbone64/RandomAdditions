package pbone.randomadditions.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class RandomAdditionsOptionsScreen extends Screen {
    private final Screen lastScreen;

    public RandomAdditionsOptionsScreen(Screen lastScreen) {
        super(new TranslatableText("gui.randomadditions.randomadditionsoptions"));
        this.lastScreen = lastScreen;
    }

    @Override
    protected void init() {
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 168, 200, 20, new TranslatableText("gui.done"), (var1x) -> {
            this.client.openScreen(this.lastScreen);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, textRenderer, title, width / 2, 15, 16777215);
    }
}
