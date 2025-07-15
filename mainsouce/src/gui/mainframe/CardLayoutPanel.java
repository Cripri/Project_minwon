package gui.mainframe;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class CardLayoutPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private CardLayout c = new CardLayout();

	public CardLayoutPanel() {
		setLayout(c);
		MainFrameState.card = this;
		setBackground(new Color(217, 217, 217));
	}

	public void show(String value) {
		if (!value.equals(MainFrameState.currentCard)) {
			if (MainFrameState.currentCard != null) {
				MainFrameState.history.push(MainFrameState.currentCard);
			}
			
			// 새로운 이동이 발생하면 future 초기화
			MainFrameState.future.clear(); 
			MainFrameState.currentCard = value;
			c.show(this, value);
		}
	}

	public void prev() {
		if (!MainFrameState.history.isEmpty()) {
	        String previous = MainFrameState.history.pop();
	        
	        if (MainFrameState.login_id != null && previous.equals("login")) {
                previous = "myPage";
	        }
	        
	        MainFrameState.future.push(MainFrameState.currentCard);
	        MainFrameState.currentCard = previous;
	        c.show(this, previous);
	    }
	}

	public void next() {
		if (!MainFrameState.future.isEmpty()) {
			String next = MainFrameState.future.pop();
			MainFrameState.history.push(MainFrameState.currentCard);
			MainFrameState.currentCard = next;
			c.show(this, next);
		}
	}
}