package com.lezko.coordgrid.geometry;

import java.awt.*;

public class Rect implements Object {

    private double x, y, width, height;

    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics2D graphics, double screenX, double screenY, int screenWidth, int screenHeight, double scale) {
        graphics.drawRect((int) (x + screenX), (int) (y + screenY), (int) width, (int) height);
    }
}
