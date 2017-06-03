package it.unibo.battleship.samples;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.common.Ruleset;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private final List<FieldButton> fieldList;
    private final Boundary boundary;

    public MyFrame(final String title, final LayoutManager lm, Boundary boundary) {
        super(title);
        this.getContentPane().add(new JPanel(lm));
    	this.fieldList = new ArrayList<>();
        this.boundary = boundary;
        initialize();
    }

    private void addButton(FieldButton btn) {
        this.getContentPane().add(btn.getButton());
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        for(int i = 0; i < boundary.getHorizontalBound(); i++)  {
            this.fieldList.add(new FieldButton(i, 0));
            addButton(this.fieldList.get(i));
        }
    }
}
