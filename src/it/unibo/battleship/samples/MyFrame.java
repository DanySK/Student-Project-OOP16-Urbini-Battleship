package it.unibo.battleship.samples;

import it.unibo.battleship.common.Ruleset;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final List<FieldButton> fieldList;

    public MyFrame(final String title, final LayoutManager lm) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().add(new JPanel(lm));
    	this.fieldList = new ArrayList<>();
    }

    private List<JButton> getGridOfButtons() {
        final List<JButton> jcomponents = new ArrayList<>();

        // Aggiunta di MxN bottoni.
        /* DECORATOR di JButton? JButton ha anche il valore della posizione
         */
        return jcomponents;
    }

    public JPanel getMainPanel() {
        return (JPanel)this.getContentPane().getComponent(0);
    }

    private void initialize() {

    }
}
