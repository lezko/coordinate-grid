package com.lezko.grid;

import com.lezko.grid.line.SourceLine;
import com.lezko.grid.point.SourcePoint;
import com.lezko.grid.screen.SourceScreen;

public class Grid {

    public static void createGrid(SourceScreen screen, int interval) {
        for (int i = 40; i < 1000; i += interval) {
            screen.addObject(new SourceLine(new SourcePoint(i, 40), new SourcePoint(i, 1000)));
            screen.addObject(new SourceLine(new SourcePoint(40, i), new SourcePoint(1000, i)));

            if (Math.abs(500 - i) < 20) {
                screen.addObject(new SourceLine(new SourcePoint(i, 0), new SourcePoint(i - 20, 20)));
                screen.addObject(new SourceLine(new SourcePoint(i, 0), new SourcePoint(i + 20, 20)));
                screen.addObject(new SourceLine(new SourcePoint(i, 0), new SourcePoint(i, 40)));

                screen.addObject(new SourceLine(new SourcePoint(1040, i), new SourcePoint(1020, i - 20)));
                screen.addObject(new SourceLine(new SourcePoint(1040, i), new SourcePoint(1020, i + 20)));
                screen.addObject(new SourceLine(new SourcePoint(1040, i), new SourcePoint(1000, i)));
            }
        }
    }
}
