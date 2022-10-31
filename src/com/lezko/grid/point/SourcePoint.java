package com.lezko.grid.point;

import com.lezko.grid.geometry.SourceObject;
import com.lezko.grid.geometry.TargetObject;

public class SourcePoint implements SourceObject {

    private final double x, y;

    public SourcePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public TargetPoint toTargetPoint(double dx, double dy, double scaleRate) {
        return new TargetPoint((int) (x * scaleRate + dx * scaleRate), (int) (y * scaleRate + dy * scaleRate));
    }

    @Override
    public TargetObject toTargetObject(double dx, double dy, double scaleRate) {
        return toTargetPoint(dx, dy, scaleRate);
    }
}
