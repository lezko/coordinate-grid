package com.lezko.coordgrid.screen;

import com.lezko.coordgrid.drawer.PixelDrawer;
import com.lezko.coordgrid.geometry.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Screen {

    private double scale = 1;
    private double x, y, areaWidth, areaHeight;
    private int width, height;

    private final List<Object> objects = new ArrayList<>();
    private final PixelDrawer pixelDrawer;

    public Screen(PixelDrawer pixelDrawer, int width, int height) {
        this(pixelDrawer, width, height, 0, 0);
    }

    public Screen(PixelDrawer pixelDrawer, int width, int height, double x, double y) {
        this.width = width;
        this.height = height;
        this.areaWidth = width;
        this.areaHeight = height;
        this.pixelDrawer = pixelDrawer;
        this.x = x;
        this.y = y;
    }

    public void update() {
        for (Object object : objects) {
            object.render(pixelDrawer, x, y, width, height, scale);
        }
    }


    public void addObject(Object object) {
        objects.add(object);
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
        areaWidth = width / scale;
        areaHeight = height / scale;
    }

    public double getAreaWidth() {
        return areaWidth;
    }

    public double getAreaHeight() {
        return areaHeight;
    }
}
