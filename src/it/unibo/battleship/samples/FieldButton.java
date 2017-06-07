package it.unibo.battleship.samples;

import java.awt.*;

import javax.swing.JButton;

public class FieldButton {
    private final JButton btn;
    private final int     x;
    private final int     y;

    public FieldButton(final int x, final int y) {
        btn = new JButton(x + ":" + y);
        btn.setSize(new Dimension(10, 10));
        this.x = x;
        this.y = y;
    }

    public JButton getButton() {
        return this.btn;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

