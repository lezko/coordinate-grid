package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import java.io.IOException;

public class ShowAxisButton extends IconButton {
    private static final String iconPath = "img/show-axis.png";
    private static final String iconActivePath = "img/show-axis-active.png";

    public ShowAxisButton() throws IOException {
        super(iconPath, iconActivePath);setOnClick(() -> {
            if (getActive()) {
            }
        });
    }
}
