package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import java.io.IOException;

public class AxisTopLeftButton extends IconButton {

    private static final String iconPath = "img/top-left.png";
    private static final String iconActivePath = "img/top-left-active.png";

    public AxisTopLeftButton(Screen screen) throws IOException {
        super(iconPath, iconActivePath);setOnClick(() -> {
            if (getActive()) {
                screen.setXInverted(true);
                screen.setYInverted(true);
            }
        });
    }
}
