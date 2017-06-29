package it.unibo.battleship.samples;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.commons.Point2dImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {
  private static final long serialVersionUID = -4540296256743795166L;
  private static final int WIDTH = 600;
  private static final int HEIGHT = 600;
  private final List<FieldButton> fieldList;
  private final Boundary boundary;

  // The constructor is private until everything works
  private MyFrame(final String title, final LayoutManager lm,
      final Boundary boundary) {
    super(title);
    this.getContentPane().add(new JPanel(lm));
    this.fieldList = new ArrayList<>();
    this.boundary = boundary;
    this.initialize();
  }

  private void addButton(final FieldButton btn) {
    this.getContentPane().add(btn.getButton());
  }

  private void initialize() {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(WIDTH, HEIGHT);
    this.setVisible(true);

    for (int i = 0; i < this.boundary.getColumnnsCount(); i++) {
      final FieldButton fb = new FieldButton(new Point2dImpl(i,0));
      this.fieldList.add(fb);
      this.addButton(fb);
      fb.getButton().addActionListener(
          (ActionEvent e) -> System.out.println("x : " + fb.getPosition().getX()));
    }
  }

  private List<JButton> getGridOfButtons() {
    final List<JButton> jcomponents = new ArrayList<>();

    // Aggiunta di MxN bottoni.

    /*
     * DECORATOR di JButton? JButton ha anche il valore della posizione
     */
    return jcomponents;
  }

  public JPanel getMainPanel() {
    return (JPanel) this.getContentPane();
  }
}
