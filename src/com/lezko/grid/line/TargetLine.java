package com.lezko.grid.line;

import com.lezko.grid.drawer.*;
import com.lezko.grid.geometry.SourceObject;
import com.lezko.grid.geometry.TargetObject;
import com.lezko.grid.point.TargetPoint;

import java.awt.*;

public class TargetLine implements TargetObject {

    private final TargetPoint point1, point2;

    public TargetLine(TargetPoint point1, TargetPoint point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public SourceObject toSourceObject(double dx, double dy) {
        return null;
    }

    @Override
    public void render(Graphics2D graphics) {
        LineDrawer ld = new BHLineDrawer(new GraphicsPixelDrawer(graphics));
        ld.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
}
