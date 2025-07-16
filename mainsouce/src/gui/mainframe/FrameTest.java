package gui.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.phs.WriteContent;
//import gui.phs.WriteContent;
import gui.phs.firstpage.FirstPage;

public class FrameTest extends JFrame {
	private static final long serialVersionUID = 1L;

	public FrameTest() {
		setTitle("Login UI");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙
		setLayout(new BorderLayout());
		
		FirstPage firstPage = new FirstPage();
		LoginPanel login = new LoginPanel();
		SimpleDocPanel simpleDoc = new SimpleDocPanel();
		MyPage myPage = new MyPage();
		SinmungoListPanel sinmungoList = new SinmungoListPanel();
		EmployeeMainPanel employeeMain = new EmployeeMainPanel();
		SignUpPanel signUp = new SignUpPanel();
		UserInfoEditPanel userInfoEdit = new UserInfoEditPanel();
		WriteContent write = new WriteContent();
		SinmungoDetailPanel pppp = new SinmungoDetailPanel(1);
		
		FrameTop ft = new FrameTop();
		add(ft, BorderLayout.NORTH);

		CardLayoutPanel cardPage = new CardLayoutPanel();
		cardPage.add("firstPage", firstPage);
		cardPage.add("login", login);
		cardPage.add("myPage", myPage);
		cardPage.add("simpleDoc", simpleDoc);
		cardPage.add("sinmungoList", sinmungoList);
		cardPage.add("employeeMain", employeeMain);
		cardPage.add("signUp", signUp);
		cardPage.add("userInfoEdit", userInfoEdit);
		cardPage.add("write", write);
		
		add(cardPage, BorderLayout.CENTER);
		
		MainFrameState.history.clear();
		MainFrameState.future.clear();
		MainFrameState.history.push("firstPage");
		setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(FrameTest::new);
	}
}