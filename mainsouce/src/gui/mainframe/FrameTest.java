package gui.mainframe;

import function.connector.Civil_Connector;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import static gui.mainframe.MainFrameState.civil;

public class FrameTest extends JFrame {
	private static final long serialVersionUID = 1L;

	public FrameTest() {
		setTitle("Login UI");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙
		setLayout(new BorderLayout());

		LoginPanel login = new LoginPanel();
		SimpleDocPanel simpleDoc = new SimpleDocPanel();
		MyPage myPage = new MyPage();
		SinmungoListPanel sinmungoList = new SinmungoListPanel();
		EmployeeMainPanel employeeMain = new EmployeeMainPanel();
		SignUpPanel signUp = new SignUpPanel();
		UserInfoEditPanel userInfoEdit = new UserInfoEditPanel();
		SinmungoDetailPanel pppp = new SinmungoDetailPanel(1);
		
		FrameTop ft = new FrameTop();
		add(ft, BorderLayout.NORTH);

		CardLayoutPanel cardPage = new CardLayoutPanel();
		cardPage.add("login", login);
		cardPage.add("myPage", myPage);
		cardPage.add("simpleDoc", simpleDoc);
		cardPage.add("sinmungoList", sinmungoList);
		cardPage.add("employeeMain", employeeMain);
		cardPage.add("signUp", signUp);
		cardPage.add("userInfoEdit", userInfoEdit);
		add(cardPage, BorderLayout.CENTER);
		
		MainFrameState.currentCard = "login";
		MainFrameState.history.clear();
		MainFrameState.future.clear();
		MainFrameState.history.push("login");
		setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(FrameTest::new);
	}
}