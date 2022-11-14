package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import java.io.IOException;

public class AxisBottomRightButton extends IconButton {
    private static final String iconPath = "img/bottom-right.png";
    private static final String iconActivePath = "img/bottom-right-active.png";

    public AxisBottomRightButton(Screen screen) throws IOException {
        super(iconPath, iconActivePath);
        setOnClick(() -> {
            if (getActive()) {
                screen.setXInverted(false);
                screen.setYInverted(false);
            }
        });
    }
}
