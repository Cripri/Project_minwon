package gui.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.phs.AfterLoginPanel;
import gui.phs.CivilComplaintDetailPanel;
import gui.phs.ComplaintAnswerListPanel;
import gui.phs.DepartmentChangeRequestDetailPanel;
import gui.phs.DepartmentChangeRequestPanel;
import gui.phs.FirstPage;
import gui.phs.RrnApplicationPanel;
import gui.phs.WriteContent;
import gui.phs.ManagerMenu.ManagerMenuPanel;

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
		// SinmungoDetailPanel pppp = new SinmungoDetailPanel(1); // 필요 시 추가
		
		AfterLoginPanel afterLoginPanel = new AfterLoginPanel();
		CivilComplaintDetailPanel civilComplaintDetailPanel = new CivilComplaintDetailPanel();
		ComplaintAnswerListPanel complaintAnswerListPanel = new ComplaintAnswerListPanel();
		DepartmentChangeRequestPanel departmentChangeRequestPanel = new DepartmentChangeRequestPanel();
		DepartmentChangeRequestDetailPanel departmentChangeRequestDetailPanel = new DepartmentChangeRequestDetailPanel();
		RrnApplicationPanel rrnApplicationPanel = new RrnApplicationPanel();
		WriteContent writeContent = new WriteContent();
		ManagerMenuPanel managerMenuPanel = new ManagerMenuPanel(); 

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
		
		cardPage.add("CivilComplaintDetailPanel", civilComplaintDetailPanel);
		cardPage.add("AfterLoginPanel", afterLoginPanel);
		cardPage.add("ComplaintAnswerListPanel", complaintAnswerListPanel);
		cardPage.add("DepartmentChangeRequestPanel", departmentChangeRequestPanel);
		cardPage.add("DepartmentChangeRequestDetailPanel", departmentChangeRequestDetailPanel);
		cardPage.add("RrnApplicationPanel", rrnApplicationPanel);
		cardPage.add("WriteContent", writeContent);
		cardPage.add("ManagerMenuPanel", managerMenuPanel);

		MainFrameState.currentCard = "login";
		MainFrameState.history.clear();
		MainFrameState.future.clear();
		MainFrameState.history.push("login");
		add(cardPage, BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(FrameTest::new);
	}
}
