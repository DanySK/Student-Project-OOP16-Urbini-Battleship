package it.unibo.battleship.samples;

import it.unibo.battleship.common.Boundary;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
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
        return (JPanel)this.getContentPane();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);

        for(int i = 0; i < boundary.getHorizontalBound(); i++)  {
            final FieldButton fb = new FieldButton(i, 0);
            this.fieldList.add(fb);
            this.addButton(fb);

            fb.getButton().addActionListener((ActionEvent e) ->
            {
                System.out.println("prova : " + fb.getX());
            });
        }
    }
}
