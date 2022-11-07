package com.lezko.coordgrid.geometry;

import java.awt.*;

public class Circle implements Object {

    private double x, y, r;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public void render(Graphics2D graphics, double screenX, double screenY, int screenWidth, int screenHeight, double scale) {
        graphics.drawOval((int) ((x - screenX) * scale), (int) ((y - screenY) * scale), (int) (r * 2 * scale), (int) (r * 2 * scale));
    }
}
