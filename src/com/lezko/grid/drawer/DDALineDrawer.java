package com.lezko.grid.drawer;


import com.lezko.grid.drawer.LineDrawer;
import com.lezko.grid.drawer.PixelDrawer;

import java.awt.*;

public class DDALineDrawer implements LineDrawer {

    private PixelDrawer pd;

    public DDALineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

        if (x2 < x1){
            int t = x2;
            x2 = x1;
            x1 = t;
            t = y2;
            y2 = y1;
            y1 = t;
        }

        int dx = x2 - x1;
        double dy = y2 - y1;

        if (dx != 0){
            for (int i = 0; i < dx; i++) {
                pd.drawPixel(x1 + i, (int)(y1 + i*dy/dx), Color.black);

            }
        }
    }
}