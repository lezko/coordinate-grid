package com.lezko.grid.geometry;

import java.awt.*;

public interface TargetObject {
    public SourceObject toSourceObject(double dx, double dy);

    public void render(Graphics2D graphics);
}
