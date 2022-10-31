package com.lezko.grid.screen;

import com.lezko.grid.geometry.SourceObject;

import java.util.ArrayList;
import java.util.List;

public class SourceScreen {

    private final double width, height;
    private final List<SourceObject> objects = new ArrayList<>();

    public SourceScreen(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<SourceObject> getObjects() {
        return objects;
    }

    public void addObject(SourceObject object) {
        objects.add(object);
    }
}
