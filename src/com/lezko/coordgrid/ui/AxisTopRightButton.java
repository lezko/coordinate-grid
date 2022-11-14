package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import java.io.IOException;

public class AxisTopRightButton extends IconButton {
    private static final String iconPath = "img/top-right.png";
    private static final String iconActivePath = "img/top-right-active.png";

    public AxisTopRightButton(Screen screen) throws IOException {
        super(iconPath, iconActivePath);
        setOnClick(() -> {
            if (getActive()) {
                screen.setXInverted(false);
                screen.setYInverted(true);
            }
        });
    }
}
