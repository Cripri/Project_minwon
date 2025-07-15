package gui.mainframe.wldb_main_frame.material;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import function.connector.Simungo;

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
		
		Simungo[] sims = {
				new Simungo(
						1, 10,
						"가", "나", "다",
						new Date(1000 * 60 * 60 * 60),
						"제목", "내용",
						100,
						new Date(),	new Date(1000 * 60 * 60),
						101010,
						"라", "마", "바", "사"
				),
				new Simungo(
						2, 20,
						"ㄱ", "ㄴ", "ㄷ",
						new Date(2000 * 60 * 60 * 60),
						"제목", "내용",
						200,
						new Date(),	new Date(2000 * 60 * 60),
						202020,
						"ㄹ", "ㅁ", "ㅂ", "ㅅ"
				)
		};
			
		tttt.add(
				gmb.get_employees_petition_panel
					(
					"클릭한 민원에대한 내용",
					Color_list_main.getInside_color(),
					sims
					)
				,
				BorderLayout.CENTER
		);
		
		//tttt.add(jp);
		tttt.setBounds(450, 300, 1000, 600);
		tttt.setVisible(true);
		tttt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
