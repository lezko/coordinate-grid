package com.lezko.coordgrid.geometry;

import com.lezko.coordgrid.drawer.PixelDrawer;
import java.util.Arrays;
import java.util.List;


public class Rect implements Object {

    private double x1, y1, x2, y2;
    private final Line top, bottom, left, right;
    private final List<Line> lines;

    public Rect(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        top = new Line(x1, y1, x2, y1);
        bottom = new Line(x1, y2, x2, y2);
        left = new Line(x1, y1, x1, y2);
        right = new Line(x2, y1, x2, y2);

        lines = Arrays.asList(top, bottom, left, right);
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
        for (Line line : lines) {
            line.render(pixelDrawer, screenX, screenY, screenWidth, screenHeight, scale, xInverted, yInverted);
        }
    }
}
