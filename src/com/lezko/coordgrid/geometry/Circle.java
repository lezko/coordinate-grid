package com.lezko.coordgrid.geometry;

import com.lezko.coordgrid.drawer.PixelDrawer;

import java.awt.*;

public class Circle implements Object {

    private static final double STEP = 0.1;
    private double x, y, r;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private Color color;

    public Circle(double x, double y, double r, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
    }

    public Circle(double x, double y, double r) {
        this(x, y, r, DEFAULT_COLOR);
    }

    @Override
    public void render(PixelDrawer pixelDrawer, double screenX, double screenY, int screenWidth, int screenHeight, double scale) {
        double targetX = (x - screenX) * scale;
        double targetY = (y - screenY) * scale;
        double targetR = r * scale;

        double a = targetX + targetR;
        double b = targetY + targetR;

        for (double i = targetX; i < targetX + 2 * targetR; i += STEP) {
            double sqrtExpr = Math.sqrt(Math.pow(targetR, 2) - Math.pow(i - a, 2));
            pixelDrawer.drawPixel((int) i, (int) (b + sqrtExpr), color);
            pixelDrawer.drawPixel((int) i, (int) (b - sqrtExpr), color);
        }
    }
}
