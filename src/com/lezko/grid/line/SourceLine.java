package com.lezko.grid.line;

import com.lezko.grid.geometry.SourceObject;
import com.lezko.grid.geometry.TargetObject;
import com.lezko.grid.point.SourcePoint;
import com.lezko.grid.point.TargetPoint;

public class SourceLine implements SourceObject {

    private final SourcePoint point1, point2;

    public SourceLine(SourcePoint point1, SourcePoint point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public SourcePoint getPoint1() {
        return point1;
    }

    public SourcePoint getPoint2() {
        return point2;
    }

    @Override
    public TargetObject toTargetObject(double dx, double dy, double scaleRate) {
        return new TargetLine(point1.toTargetPoint(dx, dy, scaleRate), point2.toTargetPoint(dx, dy, scaleRate));
    }
}
