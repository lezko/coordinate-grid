package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IconButton extends JComponent implements MouseListener {

    private final int WIDTH = 32, HEIGHT = 32;
    private BufferedImage icon, iconActive;
    private boolean active = false;
    private ButtonGroup buttonGroup;

    private Runnable onClick;

    public IconButton(String iconPath, String iconActivePath) throws IOException {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        if (iconPath != null) {
            icon = ImageIO.read(new File(iconPath));
        }
        if (iconActivePath != null) {
            iconActive = ImageIO.read(new File(iconActivePath));
        }
        addMouseListener(this);
    }

    public void setOnClick(Runnable callback) {
        onClick = callback;
    }

    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

    public IconButton(String iconPath) throws IOException {
        this(iconPath, null);
    }

    public void setActive(boolean active) {
        this.active = active;
        if (onClick != null) {
            onClick.run();
        }
        repaint();
    }

    public boolean getActive() {
        return this.active;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (active && iconActive != null) {
            graphics.drawImage(iconActive, 0, 0, null);
        }
        if (!active && icon != null) {
            graphics.drawImage(icon, 0, 0, null);
        }
    }

    public IconButton() throws IOException {
        this(null, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (iconActive != null) {
            if (buttonGroup != null && active) {
                return;
            } else if (buttonGroup != null) {
                for (IconButton button : buttonGroup.getButtons()) {
                    button.setActive(false);
                }
                setActive(true);
                buttonGroup.update();
                return;
            }

            setActive(!active);
            if (buttonGroup != null) {
                buttonGroup.update();
            }
        } else {
            onClick.run();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
