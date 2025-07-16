package gui.mainframe.wldb_main_frame.material;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import function.connector.Sinmungo;

public class Panel_tests {
	
	static Get_main_base gmb = new Get_main_base();
	
	public static void main(String[] args) {
		JFrame tttt = new JFrame();
		tttt.setLayout(new BorderLayout());
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		
//		tttt.add(gmb.get_title_panel("실험"));
		
//		String[] description = {"접수 번호", "내용", "처리상태", "만료일자"};
//		String[] info = {"AA002-0001", "여권 재발급", "반려", "2025-11-11"};
		//String[] description = {"번호", "제목", "처리기관", "등록일"};
		//String[] info = {"51", "노원구 어쩌구 아스팔트 파임", "도로 교통공사", "2025-07-03"};
		
		
//		jp.add(gmb.get_string_width_panel(description, true));
//		jp.add(gmb.get_string_width_panel(info, false));
				
//		tttt.add(gmb.get_string_box_panel(description));
//		tttt.add(gmb.get_string_box_panel(info));
		
		Sinmungo[] sims = {
				new Sinmungo(
						1, 10,
						"가", "나", "다",
						new Date(1000 * 60 * 60 * 60),
						"제목", "내용",
						100,
						new Date(),	new Date(1000 * 60 * 60),
						101010,
						"라", "마", "바", "C"
				),
				new Sinmungo(
						2, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "C"
				),
				new Sinmungo(
						3, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "C"
				)
				,
				new Sinmungo(
						4, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "C"
				),
				new Sinmungo(
						5, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "I"
				),
				new Sinmungo(
						6, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "I"
				),
				new Sinmungo(
						7, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "I"
				),
				new Sinmungo(
						8, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "P"
				),
				new Sinmungo(
						9, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "P"
				),
				new Sinmungo(
						10, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "P"
				),
				new Sinmungo(
						11, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "P"
				),
				new Sinmungo(
						12, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "P"
				)
		};
			
		tttt.add(
				gmb.get_card_employees_petition("타이틀",	sims)
				,BorderLayout.CENTER
		);
		
		//tttt.add(jp);
		tttt.setBounds(450, 300, 1000, 600);
		tttt.setVisible(true);
		tttt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
