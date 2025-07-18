package gui.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.phs.*;
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
		SinmungoDetailPanel pppp = new SinmungoDetailPanel(1, "마이페이지");
		
		//AfterLoginPanel afterLoginPanel = new AfterLoginPanel();
		CivilComplaintDetailPanel civilComplaintDetailPanel = new CivilComplaintDetailPanel();
		ComplaintAnswerListPanel complaintAnswerListPanel = new ComplaintAnswerListPanel();
		DepartmentChangeRequestPanel departmentChangeRequestPanel = new DepartmentChangeRequestPanel();
		DepartmentChangeRequestDetailPanel departmentChangeRequestDetailPanel = new DepartmentChangeRequestDetailPanel();
		RrnApplicationPanel rrnApplicationPanel = new RrnApplicationPanel();
		WriteContent writeContent = new WriteContent();
		ManagerMenuPanel managerMenuPanel = new ManagerMenuPanel();
		SinmungoinfoPanel sinmungoinfoPanel = new SinmungoinfoPanel();

		FrameTop ft = new FrameTop();
		add(ft, BorderLayout.NORTH);

		CardLayoutPanel cardPage = new CardLayoutPanel();
		cardPage.add("sinmungoInfoPanel",sinmungoinfoPanel);
		cardPage.add("userInfoEdit", userInfoEdit);
//		cardPage.add("CivilComplaintDetailPanel", civilComplaintDetailPanel);
//		cardPage.add("AfterLoginPanel", afterLoginPanel);
//		cardPage.add("ComplaintAnswerListPanel", complaintAnswerListPanel); // 주민번호 지워야함 
//		cardPage.add("DepartmentChangeRequestPanel", departmentChangeRequestPanel); // 완성 창 제목 넣어주면 좋음
//		cardPage.add("DepartmentChangeRequestDetailPanel", departmentChangeRequestDetailPanel); 주민번호 지워야함
//		cardPage.add("RrnApplicationPanel", rrnApplicationPanel); //선택창 더 만들어야 함
//		cardPage.add("WriteContent", writeContent); // 작성완료 버튼 지우기 
//		cardPage.add("ManagerMenuPanel", managerMenuPanel); // 위에 제목 만드
		
		
		cardPage.add("firstPage", firstPage);
		cardPage.add("login", login);
		cardPage.add("myPage", myPage);
		cardPage.add("simpleDoc", simpleDoc);
		cardPage.add("sinmungoList", sinmungoList);
		cardPage.add("employeeMain", employeeMain);
		cardPage.add("signUp", signUp);
		

		

		MainFrameState.history.clear();
		MainFrameState.future.clear();
		add(cardPage, BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(FrameTest::new);
	}
}
