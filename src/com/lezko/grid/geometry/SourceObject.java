package com.lezko.grid.geometry;

public interface SourceObject {
    public TargetObject toTargetObject(double dx, double dy, double scaleRate);
}
