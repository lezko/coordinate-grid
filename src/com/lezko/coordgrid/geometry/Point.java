package com.lezko.coordgrid.geometry;

import com.lezko.coordgrid.drawer.GraphicsPixelDrawer;

import java.awt.*;

public class Point implements Object {

    private final double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public void render(Graphics2D graphics, double screenX, double screenY, int screenWidth, int screenHeight, double scale) {
        new GraphicsPixelDrawer(graphics).drawPixel((int) (x + screenX), (int) (y + screenY), graphics.getColor());
    }
}
