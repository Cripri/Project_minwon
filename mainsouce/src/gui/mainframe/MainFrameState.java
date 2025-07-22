package gui.mainframe;

import java.util.Stack;

import function.connector.Civil_Connector;
import function.connector.Employees;
import function.connector.Members;

public class MainFrameState {
	public static CardLayoutPanel card;
	public static Civil_Connector civil;
	public static Members member;
	public static Employees employee;
	public static FrameTop frameTop;
	public static EmployeeMainPanel employeeMainPanel;
	
	public static Stack<String> history = new Stack<>();   // 뒤로가기 스택
	public static Stack<String> future = new Stack<>();    // 앞으로가기 스택
	public static String currentCard = "firstPage";
	public static String postLoginTarget = null;  // 로그인 성공 후 이동할 화면

	static{
		civil = new Civil_Connector();
		civil.start();
	}
}