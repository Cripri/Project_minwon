package gui.phs.commonPanel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public TopPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        setBackground(new Color(217, 217, 217));

        add(createTopButton("메인페이지"));
        add(createTopButton("마이페이지"));
        add(createTopButton("로그인"));
    }

    private JButton createTopButton(String text) {
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }
}