package com.lezko.grid.screen;

import com.lezko.grid.geometry.TargetObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TargetScreen {

    private int width, height;
    private final List<TargetObject> objects = new ArrayList<>();

    public TargetScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void render(Graphics2D graphics) {
        for (TargetObject object : objects) {
            object.render(graphics);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<TargetObject> getObjects() {
        return objects;
    }

    public void addObject(TargetObject obj) {
        objects.add(obj);
    }
}
