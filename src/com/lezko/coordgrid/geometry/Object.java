package com.lezko.coordgrid.geometry;

import com.lezko.coordgrid.drawer.PixelDrawer;

import java.awt.*;

public interface Object {
    public void render(PixelDrawer pixelDrawer, double screenX, double screenY, int screenWidth, int screenHeight, double scale);
}
