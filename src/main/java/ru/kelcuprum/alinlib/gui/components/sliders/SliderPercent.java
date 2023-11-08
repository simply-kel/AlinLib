package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;

import java.awt.*;

public class SliderPercent extends AbstractSliderButton {
    public final double defaultConfig;
    public final Config config;
    public final String typeConfig;
    public SliderPercent(int x, int y, int width, int height, Config config, String typeConfig, double defaultConfig, Component component) {
        super(x, y, width, height, component, config.getDouble(typeConfig, defaultConfig));
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        // this.getX() + (int)(this.value * (double)(this.width - 8)), this.getY(), 8, this.getHeight()
        //
        float state = !active ? 3 : isHovered ? 2 : 1;
        final float f = state / 2 * 0.9F + 0.1F;
        final int color = (int) (255.0F * f);

        guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
        guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(isFocused() ? 0xFFFFEE31 : 0xFF31FF83, true).getRGB());
        if(isHoveredOrFocused()){
            int x = this.getX() + (int)(this.value * (double)(this.width - 4));
            int y = this.getY()+(getHeight() - 8) / 2;
            guiGraphics.fill(x, y, x+4, y+Minecraft.getInstance().font.lineHeight, new Color(isFocused() ? 0xFFFFEE31 : 0xFF31FF83, true).getRGB());
        }

        guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        Component volumeState = Component.translatable(Localization.getRounding(this.value * 100,   true)+"%");
        guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth()-Minecraft.getInstance().font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
    }

    @Override
    protected void updateMessage() {

    }

    @Override
    protected void applyValue() {
        this.config.setDouble(this.typeConfig, this.value);

    }
}