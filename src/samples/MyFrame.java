package samples;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

    private final int WIDTH = 400;
    private final int HEIGHT = 400;

    public MyFrame(final String title, final LayoutManager lm) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().add(new JPanel(lm));
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
}
