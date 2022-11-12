package com.lezko.coordgrid.geometry;

import com.lezko.coordgrid.drawer.PixelDrawer;
import java.util.Arrays;
import java.util.List;


public class Rect implements Object {

    private double x, y, width, height;
    private final Line top, bottom, left, right;
    private final List<Line> lines;

    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        top = new Line(x, y, x + width, y);
        bottom = new Line(x, y + height, x + width, y + height);
        left = new Line(x, y, x, y + height);
        right = new Line(x + width, y, x + width, y + height);

        lines = Arrays.asList(top, bottom, left, right);
    }

    @Override
    public void render(PixelDrawer pixelDrawer, double screenX, double screenY, int screenWidth, int screenHeight, double scale) {
        for (Line line : lines) {
            line.render(pixelDrawer, screenX, screenY, screenWidth, screenHeight, scale);
        }
    }
}
