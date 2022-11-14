package com.lezko.coordgrid.geometry;

import com.lezko.coordgrid.drawer.PixelDrawer;

import java.awt.*;

public class Point implements Object {

    private static final Color DEFAULT_COLOR = Color.BLACK;

    private final double x, y;
    private Color color;

    public Point(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Point(double x, double y) {
        this(x, y, DEFAULT_COLOR);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void render(
        PixelDrawer pixelDrawer,
        double screenX,
        double screenY,
        int screenWidth,
        int screenHeight,
        double scale,
        boolean xInverted,
        boolean yInverted
    ) {
        pixelDrawer.drawPixel(
            (int) ((x * (xInverted ? -1 : 1) - screenX) * scale),
            (int) ((y * (yInverted ? -1 : 1) - screenY) * scale),
            color
        );
    }
}
