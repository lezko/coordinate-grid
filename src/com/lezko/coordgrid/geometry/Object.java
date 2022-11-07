package com.lezko.coordgrid.geometry;

import java.awt.*;

public interface Object {
    public void render(Graphics2D graphics, double screenX, double screenY, int screenWidth, int screenHeight, double scale);
}
