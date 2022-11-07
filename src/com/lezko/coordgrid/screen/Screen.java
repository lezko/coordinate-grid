package com.lezko.coordgrid.screen;

import com.lezko.coordgrid.geometry.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Screen {

    private double scale = 1;
    private double x, y;
    private int width, height;

    private final List<Object> objects = new ArrayList<>();
    private final Graphics2D graphics;

    public Screen(Graphics2D graphics, int width, int height) {
        this.width = width;
        this.height = height;
        this.graphics = graphics;
    }

    public void update() {
        for (Object object : objects) {
            object.render(graphics, x, y, width, height, scale);
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
    }
}
