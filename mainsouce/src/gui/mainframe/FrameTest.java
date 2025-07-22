package gui.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.phs.ComplaintAnswerListPanel;
import gui.phs.DepartmentChangeRequestDetailPanel;
import gui.phs.DepartmentChangeRequestPanel;
import gui.phs.FirstPage;
import gui.phs.RrnApplicationPanel;
import gui.phs.SinmungoinfoPanel;
import gui.phs.WriteContent;
import gui.phs.ManagerMenu.ManagerMenuPanel;

public class FrameTest extends JFrame {
	private static final long serialVersionUID = 1L;

	public FrameTest() {
		setTitle("Project_minwon");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙
		setLayout(new BorderLayout());
		
		FirstPage firstPage = new FirstPage();

		FrameTop ft = new FrameTop();
		add(ft, BorderLayout.NORTH);

		CardLayoutPanel cardPage = new CardLayoutPanel();
		
		cardPage.add("firstPage", firstPage);

		MainFrameState.history.clear();
		MainFrameState.future.clear();
		add(cardPage, BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(FrameTest::new);
	}
}