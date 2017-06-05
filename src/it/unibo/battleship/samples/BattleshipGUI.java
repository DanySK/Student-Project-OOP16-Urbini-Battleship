package it.unibo.battleship.samples;

import it.unibo.battleship.common.BoundaryImpl;

import java.awt.*;

/**
 * @author fabio.urbini
 */
public class BattleshipGUI {

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame("Battleship", new GridBagLayout(), BoundaryImpl.createBoundary(10, 10));

    }
}
