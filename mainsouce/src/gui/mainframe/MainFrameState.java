package gui.mainframe;

import java.util.Stack;

import function.connector.Civil_Connector;

public class MainFrameState {
	public static CardLayoutPanel card;
	public static Civil_Connector civil;
	public static String login_id;
	public static FrameTop frameTop;
	
	public static Stack<String> history = new Stack<>();   // 뒤로가기 스택
	public static Stack<String> future = new Stack<>();    // 앞으로가기 스택
	public static String currentCard = "firstPage";

	static{
		civil = new Civil_Connector();
		civil.start();
	}
}