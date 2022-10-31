package com.lezko.grid.point;

import com.lezko.grid.drawer.GraphicsPixelDrawer;
import com.lezko.grid.drawer.PixelDrawer;
import com.lezko.grid.geometry.SourceObject;
import com.lezko.grid.geometry.TargetObject;

import java.awt.*;

public class TargetPoint implements TargetObject {

    private int x, y;

    public TargetPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SourcePoint toSourcePoint(double dx, double dy) {
        return new SourcePoint((double) x + dx, (double) y + dy);
    }

    @Override
    public SourceObject toSourceObject(double dx, double dy) {
        return toSourcePoint(dx, dy);
    }

    @Override
    public void render(Graphics2D graphics) {
        PixelDrawer pd = new GraphicsPixelDrawer(graphics);
        pd.drawPixel(x, y, graphics.getColor());
    }
}
