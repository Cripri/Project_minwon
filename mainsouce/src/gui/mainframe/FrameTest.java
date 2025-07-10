package gui.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FrameTest extends JFrame {
	private static final long serialVersionUID = 1L;

	public FrameTest() {
		setTitle("Login UI");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙
		setLayout(new BorderLayout());

		LoginPanel loginPanel = new LoginPanel();
		SimpleDocPanel simpleDocPanel = new SimpleDocPanel();
		MyPage myPage = new MyPage();
		
		FrameTop ft = new FrameTop();
		add(ft, BorderLayout.NORTH);

		CardLayoutPanel cardPage = new CardLayoutPanel();
		cardPage.add(loginPanel);
		cardPage.add(simpleDocPanel);
		cardPage.add(myPage);
		add(cardPage, BorderLayout.CENTER);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(FrameTest::new);
	}
}