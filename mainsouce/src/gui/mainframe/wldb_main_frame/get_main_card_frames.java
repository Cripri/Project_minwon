package gui.mainframe.wldb_main_frame;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.MainFrameState;
import gui.mainframe.wldb_main_frame.material.Get_main_base;

public class get_main_card_frames {
	static Get_main_base gmb = new Get_main_base();
	
	public static JPanel get_employees_sinmungo_list_panel(String title) {
		
		QueryRequest<Sinmungo> request = new QueryRequest<>(
				"SELECT * FROM Sinmungo",
				null,
				Sinmungo.class,
				MainFrameState.civil
			);
		ArrayList<Sinmungo> sins = new ArrayList<>(request.getResultList());
		
		return gmb.get_card_employees_petition(title, sins);
	}// 이거 조건따라서 달라지게 하려면...
	// null 쪽이냐?
	// null 자리에 where 어쩌고가 되긴하나?
	// 그러면 where 다음에 id like id 같은게 되나?"
	// 그냥 버전 세개 더 만들까?
	// 아짐짜 생ㄱ가좀 하게 만드내........................
	// 일단 직원 배정은 빼고
	// 결제 확이니앙 처리 완료된만 미리 볼까?

	public static JPanel get_employees_sinmungo_list_panel(
			String title, int kind
	) {
		String condition = "";
		if(kind == -1) {
			condition = "P";
		} else if(kind == 0) {
			condition = "I";
		} else if(kind == 1) {
			condition = "C";
		}
		
		QueryRequest<Sinmungo> request = new QueryRequest<>(
				"SELECT * FROM Sinmungo where status like ?",
				condition,
				Sinmungo.class,
				MainFrameState.civil
			);
		ArrayList<Sinmungo> sins = new ArrayList<>(request.getResultList());
		
		return gmb.get_card_employees_petition(title, sins);
	}
	
}
