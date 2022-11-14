package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import java.io.IOException;

public class ResetButton extends IconButton {
    private static final String iconPath = "img/center.png";

    public ResetButton(ScreenPanel screenPanel) throws IOException {
        super(iconPath);
        setOnClick(() -> {
            screenPanel.animateReset();
        });
    }
}
