package com.lezko.coordgrid.screen;

import com.lezko.coordgrid.drawer.PixelDrawer;
import com.lezko.coordgrid.geometry.Object;

import java.util.ArrayList;
import java.util.List;

public class Screen {

    private double scale = 1;
    private double x, y;
    private int width, height;

    private boolean xInverted = false;
    private boolean yInverted = false;

    private final List<Object> objects = new ArrayList<>();
    private final PixelDrawer pixelDrawer;

    public Screen(PixelDrawer pixelDrawer, int width, int height) {
        this(pixelDrawer, width, height, 0, 0);
    }

    public Screen(PixelDrawer pixelDrawer, int width, int height, double x, double y) {
        this.width = width;
        this.height = height;
        this.pixelDrawer = pixelDrawer;
        this.x = x;
        this.y = y;
    }

    public void update(double x, double y, double scale) {
        setX(x);
        setY(y);
        setScale(scale);
    }

    public void render() {
        for (Object object : objects) {
            object.render(pixelDrawer, x, y, width, height, scale, xInverted, yInverted);
        }
    }

    public void setXInverted(boolean xInverted) {
        this.xInverted = xInverted;
    }

    public void setYInverted(boolean yInverted) {
        this.yInverted = yInverted;
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
