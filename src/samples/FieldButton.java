package samples;

import javax.swing.JButton;

public class FieldButton {

	private final JButton btn;
	private final int x;
	private final int y;
	
	public FieldButton(final int x, final int y) {
		btn = new JButton(x + ":" + y);
		this.x = x;
		this.y= y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	
}
