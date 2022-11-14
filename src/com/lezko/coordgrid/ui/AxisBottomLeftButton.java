package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import java.io.IOException;

public class AxisBottomLeftButton extends IconButton {
    private static final String iconPath = "img/bottom-left.png";
    private static final String iconActivePath = "img/bottom-left-active.png";

    public AxisBottomLeftButton(Screen screen) throws IOException {
        super(iconPath, iconActivePath);
        setOnClick(() -> {
            if (getActive()) {
                screen.setXInverted(true);
                screen.setYInverted(false);
            }
        });
    }
}
