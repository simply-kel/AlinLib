package ru.kelcuprum.alinlib.gui.components.sliders.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class Slider extends AbstractSliderButton {
    public final InterfaceUtils.DesignType type;
    public final String buttonMessage;

    public Slider(int x, int y, int width, int height, Component label) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, 0, label);
    }
    public Slider(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label) {
        this(x, y, width, height, type, 0, label);
    }
    public Slider(int x, int y, int width, int height, InterfaceUtils.DesignType type, double position, Component label) {
        super(x, y, width, height, label, position);
        this.type = type;
        this.buttonMessage = label.getString();
        this.setMessage(Component.literal(buttonMessage).append(": ").append(getComponentValue()));
    }
    // Получить
    public Component getComponentValue(){
        return Component.literal(Localization.getRounding(getValue() * 100,   true)+"%");
    }
    public double getValue(){
        return this.value;
    }

    // Заменить
    public void setActive(boolean active){
        this.active = active;
    }

    // Рендер
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        if(this.visible){
            renderBackground(guiGraphics, i, j, tick);
            renderText(guiGraphics, i, j, tick);
        }
    }
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float tick) {
        this.type.renderSliderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.value, this);
    }
    public void renderText(GuiGraphics guiGraphics, int i, int j, float tick) {

        if(InterfaceUtils.isDoesNotFit(getMessage(), getWidth(), getHeight())){
            if(isHoveredOrFocused()){
                this.setMessage(getComponentValue());
            } else {
                this.setMessage(Component.literal(buttonMessage).append(": ").append(getComponentValue()));
            }
            this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        } else {
            if(isHovered()){
                guiGraphics.drawString(Minecraft.getInstance().font, getComponentValue(), getX() + (getWidth()/2) - (Minecraft.getInstance().font.width(getComponentValue().getString())/2), getY() + (getHeight() - 8) / 2, 0xffffff);
            } else {
                guiGraphics.drawString(Minecraft.getInstance().font, buttonMessage, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
                // VOLUME
                guiGraphics.drawString(Minecraft.getInstance().font, getComponentValue(), getX() + getWidth() - Minecraft.getInstance().font.width(getComponentValue().getString()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
            }
        }
    }



    // BASE
    @Override
    protected void updateMessage() {

    }

    @Override
    protected void applyValue(){

    }
    // Мелочи
    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(Slider onPress);
    }
}
