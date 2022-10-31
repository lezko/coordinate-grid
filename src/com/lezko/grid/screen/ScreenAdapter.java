package com.lezko.grid.screen;

import com.lezko.grid.geometry.SourceObject;

import java.awt.*;

public class ScreenAdapter {

    private final SourceScreen sourceScreen;
    private final TargetScreen targetScreen;

    private double scaleRate = 1;
    private double targetX, targetY;

    public ScreenAdapter(SourceScreen sourceScreen, TargetScreen targetScreen) {
        this.sourceScreen = sourceScreen;
        this.targetScreen = targetScreen;
    }

    public void updateTarget() {
        targetScreen.getObjects().clear();
        for (SourceObject object : sourceScreen.getObjects()) {
            targetScreen.addObject(object.toTargetObject(-targetX, -targetY, scaleRate));
        }
    }

    public void renderTarget(Graphics2D graphics) {
        targetScreen.render(graphics);
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public double getScaleRate() {
        return scaleRate;
    }

    public void setScaleRate(double scaleRate) {
        this.scaleRate = scaleRate;
    }
}
