package gui.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.phs.AfterLoginPanel;
import gui.phs.CivilComplaintDetailPanel;
import gui.phs.ComplaintAnswerListPanel;
import gui.phs.DepartmentChangeRequestDetailPanel;
import gui.phs.DepartmentChangeRequestPanel;
import gui.phs.RrnApplicationPanel;
import gui.phs.WriteContent;
import gui.phs.ManagerMenu.ManagerMenuPanel;
import gui.phs.firstpage.FirstPage;

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
		
		AfterLoginPanel afterLoginPanel = new AfterLoginPanel();
		CivilComplaintDetailPanel civilComplaintDetailPanel = new CivilComplaintDetailPanel();
		ComplaintAnswerListPanel complaintAnswerListPanel = new ComplaintAnswerListPanel();
		DepartmentChangeRequestPanel departmentChangeRequestPanel = new DepartmentChangeRequestPanel();
		DepartmentChangeRequestDetailPanel departmentChangeRequestDetailPanel = new DepartmentChangeRequestDetailPanel();
		RrnApplicationPanel rrnApplicationPanel = new RrnApplicationPanel();
		WriteContent writeContent = new WriteContent();
		ManagerMenuPanel managerMenuPanel = new ManagerMenuPanel(); 
		FirstPage firstPage = new FirstPage();

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

		cardPage.add("CivilComplaintDetailPanel", civilComplaintDetailPanel);
		cardPage.add("AfterLoginPanel", afterLoginPanel);
		cardPage.add("ComplaintAnswerListPanel", complaintAnswerListPanel);
		cardPage.add("DepartmentChangeRequestPanel", departmentChangeRequestPanel);
		cardPage.add("DepartmentChangeRequestDetailPanel", departmentChangeRequestDetailPanel);
		cardPage.add("RrnApplicationPanel", rrnApplicationPanel);
		cardPage.add("WriteContent", writeContent);
		cardPage.add("ManagerMenuPanel", managerMenuPanel);
		cardPage.add("FirstPage", firstPage);

		setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(FrameTest::new);
	}
}