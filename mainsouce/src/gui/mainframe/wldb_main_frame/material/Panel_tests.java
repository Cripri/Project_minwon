package gui.mainframe.wldb_main_frame.material;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel_tests {
	
	static Get_main_base gmb = new Get_main_base();
	
	public static void main(String[] args) {
		JFrame tttt = new JFrame();
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		
//		tttt.add(gmb.get_title_panel("실험"));
		
		String[] description = {"접수 번호", "내용", "처리상태", "만료일자", "추가신청"};
		String[] info = {"AA002-0001", "여권 재발급", "반려", "2025-11-11", "해줘"};
		//String[] description = {"번호", "제목", "처리기관", "등록일"};
		//String[] info = {"51", "노원구 어쩌구 아스팔트 파임", "도로 교통공사", "2025-07-03"};
		
		
		jp.add(gmb.get_string_width_panel(description));
		jp.add(gmb.get_string_width_panel(info));
				
//		tttt.add(gmb.get_string_box_panel(description));
//		tttt.add(gmb.get_string_box_panel(info));
		// 하다가 안되서 복붙했는데도 안된다
		// 뭔가 마지막에 없는 빈 공간이 멋대로 차지해서
		// 어긋나
		
		tttt.add(jp);
		tttt.setBounds(0, 300, 2000, 200);
		tttt.setVisible(true);
		tttt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
