package ru.kelcuprum.alinlib.gui.components.buttons.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class Button extends AbstractButton {
    protected InterfaceUtils.DesignType type;
    int color;
    private final boolean isCentred;
    private OnPress onPress;


    public Button(int x, int y, int width, int height, Component label){
        this(x, y, width, height, label, null);
    }
    public Button(int x, int y, int width, int height, Component label, OnPress onPress){
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress){
        this(x, y, width, height, type, InterfaceUtils.Colors.SEADRIVE, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, Component label, OnPress onPress){
        this(x, y, width, height, type, color, true, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, boolean isCentred, Component label, OnPress onPress) {
        super(x, y, width, height, label);
        this.type = type;
        this.color = color;
        this.onPress = onPress;
        this.isCentred = isCentred;
    }

    // Изменение элементов в кнопке
    // С возвращением класса
    public Button setType(InterfaceUtils.DesignType type) {
        this.type = type;
        return this;
    }
    public Button setActive(boolean active){
        this.active = active;
        return this;
    }
    public Button setColor(int color) {
        this.color = color;
        return this;
    }
    public Button setOnPress(OnPress onPress) {
        this.onPress = onPress;
        return this;
    }


    // Рендер
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
            renderText(guiGraphics, mouseX, mouseY, partialTicks);
        }
    }
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(type != null) this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.color);
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(InterfaceUtils.isDoesNotFit(getMessage(), getWidth(), getHeight())) this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        else if(isCentred) InterfaceUtils.drawCenteredString(guiGraphics, Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff, type == InterfaceUtils.DesignType.VANILLA);
        else guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff, type == InterfaceUtils.DesignType.VANILLA);
    }

    // Мелочи
    @Override
    public void onPress() {
        if(this.onPress != null) {
            this.onPress.onPress(this);
            setFocused(false);
        }
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(Button button);
    }
}
