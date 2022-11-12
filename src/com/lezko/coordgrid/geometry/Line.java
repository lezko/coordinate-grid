package com.lezko.coordgrid.geometry;

import com.lezko.coordgrid.drawer.BHLineDrawer;
import com.lezko.coordgrid.drawer.PixelDrawer;

public class Line implements Object {

    private final Point point1, point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    @Override
    public void render(PixelDrawer pixelDrawer, double screenX, double screenY, int screenWidth, int screenHeight, double scale) {
        new BHLineDrawer(pixelDrawer)
                .drawLine((int) ((point1.getX() - screenX) * scale), (int) ((point1.getY() - screenY) * scale), (int) ((point2.getX() - screenX) * scale), (int) ((point2.getY() - screenY) * scale));
    }
}
