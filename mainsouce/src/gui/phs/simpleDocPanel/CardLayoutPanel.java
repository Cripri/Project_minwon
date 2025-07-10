package gui.phs.simpleDocPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class CardLayoutPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public CardLayoutPanel() {
		setLayout(new CardLayout());
		setBackground(new Color(217, 217, 217));
	}
}