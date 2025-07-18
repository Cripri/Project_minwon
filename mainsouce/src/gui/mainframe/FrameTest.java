package gui.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//import gui.phs.AfterLoginPanel;
import gui.phs.CivilComplaintDetailPanel;
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
		LoginPanel login = new LoginPanel();
		SimpleDocPanel simpleDoc = new SimpleDocPanel();
		MyPage myPage = new MyPage();
		SinmungoListPanel sinmungoList = new SinmungoListPanel();
		EmployeeMainPanel employeeMain = new EmployeeMainPanel();
		SignUpPanel signUp = new SignUpPanel();
		UserInfoEditPanel userInfoEdit = new UserInfoEditPanel();
		SinmungoDetailPanel pppp = new SinmungoDetailPanel(1, "마이페이지");
		GuestLoginPanel guestLogin = new GuestLoginPanel();
		
		SinmungoinfoPanel sinmungoinfoPanel = new SinmungoinfoPanel();
		CivilComplaintDetailPanel civilComplaintDetailPanel = new CivilComplaintDetailPanel();
		ComplaintAnswerListPanel complaintAnswerListPanel = new ComplaintAnswerListPanel();
		DepartmentChangeRequestPanel departmentChangeRequestPanel = new DepartmentChangeRequestPanel();
		DepartmentChangeRequestDetailPanel departmentChangeRequestDetailPanel = new DepartmentChangeRequestDetailPanel();
		RrnApplicationPanel rrnApplicationPanel = new RrnApplicationPanel();
		WriteContent writeContent = new WriteContent();
		ManagerMenuPanel managerMenuPanel = new ManagerMenuPanel(); 
		MainFrameState.employeeMainPanel = employeeMain;

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
		cardPage.add("guestLogin", guestLogin);
		
		cardPage.add("civilComplaintDetailPanel", civilComplaintDetailPanel);
		cardPage.add("SinmungoinfoPanel", sinmungoinfoPanel);
		cardPage.add("complaintAnswerListPanel", complaintAnswerListPanel); // 주민번호 지워야함 
		cardPage.add("departmentChangeRequestPanel", departmentChangeRequestPanel); // 완성 창 제목 넣어주면 좋음
		cardPage.add("departmentChangeRequestDetailPanel", departmentChangeRequestDetailPanel); // 주민번호 지워야함
		cardPage.add("rrnApplicationPanel", rrnApplicationPanel); //선택창 더 만들어야 함
		cardPage.add("writeContent", writeContent); // 작성완료 버튼 지우기 
		cardPage.add("managerMenuPanel", managerMenuPanel); // 위에 제목 만드
		
		MainFrameState.history.clear();
		MainFrameState.future.clear();
		add(cardPage, BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(FrameTest::new);
	}
}
