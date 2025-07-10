package gui.mainframe;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class CardLayoutPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	CardLayout c = new CardLayout();
	
	public CardLayoutPanel() {
		setLayout(c);
		MainFrameState.card = this;
		setBackground(new Color(217, 217, 217));
	}
	
	public void next() {
		c.next(this);
	}
	
	public void prev() {
		c.previous(this);
	}
	
	public void show(String value) {
		c.show(this, value);
	}
}