package com.lezko.coordgrid.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonGroup extends JComponent {

    private List<IconButton> buttons = new ArrayList<>();
    private final Runnable update;

    public ButtonGroup(Runnable update) {
        this.update = update;
        setLayout(new FlowLayout());
//        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    public void update() {
        update.run();
    }

    public void addButton(IconButton button) {
        button.setButtonGroup(this);
        buttons.add(button);
        add(button);
        revalidate();
    }

    public List<IconButton> getButtons() {
        return buttons;
    }
}
