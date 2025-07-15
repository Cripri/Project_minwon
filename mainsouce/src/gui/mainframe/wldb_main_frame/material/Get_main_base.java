package gui.mainframe.wldb_main_frame.material;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import gui.mainframe.model.Petition;

public class Get_main_base {
	
	static Font title_font = new Font("맑은 고딕", Font.BOLD, 30);
	static Font normal_font = new Font("맑은 고딕", Font.PLAIN, 18);
	
	static Color_list_main cols = new Color_list_main(); 
	
	private static Double[] petition_gap = {0.1, 0.5, 0.2, 0.2, 0.1};
	static ArrayList<Double> petition_gaps = new ArrayList<>(Arrays.asList(petition_gap));
	
	
	
	public static ArrayList<Double> getPetitions_gap() {
		return petition_gaps;
	}// 시험 끝나면 지울것

	public static JPanel get_public_panel() {
		JPanel jp = new JPanel();
		
		jp.setOpaque(false);		
		return jp;
	}
	
	public static JPanel setting_public_panel(JPanel jp) {
		
		jp.setOpaque(false);
		
		
		return jp;
	}
	
	public static JPanel setting_public_panel(JPanel jp, Color col) {
		JPanel jpc = setting_public_panel(jp);
		
		jpc.setOpaque(true);
		jpc.setBackground(col);
		
		return jpc;
	}
	
	
	protected static JLabel get_label(String str, Font fon, int alignment) {
		JLabel jl = new JLabel(str, SwingConstants.CENTER);
		
		jl.setFont(fon);
		jl.setHorizontalAlignment(alignment);
		
		return jl;
	}
	
	public static JPanel get_title_panel(String title) {
		
		JPanel jpc = get_public_panel();
		
		jpc.setLayout(new BoxLayout(jpc, BoxLayout.Y_AXIS));
		
		JLabel title_jl = new JLabel(title);
		
		title_jl.setHorizontalAlignment(JLabel.LEFT);		
		title_jl.setFont(title_font);
		
		jpc.add(Box.createVerticalStrut(10));		
		jpc.add(title_jl);
		
		return jpc;
	}
	
	public static JPanel get_Employees_petition_panel(
			String title, Color col, Petition[] petition_info
	) {
		JPanel jpc = get_public_panel();
		// 타이틀은 해놨으니
		// 이제 참고 클레스 업데이트를 기다리며
		// 그 바탕 큰거랑 
		// 위에꺼랑 중간에 흰 박스 넣는 그런거 해두자	
		
		// 그냥 흰줄이고 뭐고 다 하나로 해서 넣으려했는데
		// 그러지말고 여기서 만들어 넣고 흰줄넣고를 for로 돌려야겠다
		String[] description = {"접수 번호", "내용", "처리상태", "만료일자", "추가신청"};
		
		jpc.setLayout(new BoxLayout(jpc, BoxLayout.Y_AXIS));
		
		ArrayList<JPanel> p_list = new ArrayList<>();
		
		///여기다가 클래스 분류해 String[]로 만들어 리스트 만들어 저장해줄애 뽑고
		/// 그 다음에 설명창 만들어서 넣어야지
		/// 그리고 설명창은 재활용항꺼니까 간격 외부입력으로 바꾸고
		
		jpc.add(get_title_panel(title));
		
		jpc.add(get_string_width_panel(description));
		
		return jpc;
	}
	
	// 여기서부터 문제
	protected static JPanel get_string_width_panel(
			String[] description
	) {
		JPanel jpc = get_public_panel();
		
		jpc.setLayout(new GridBagLayout());
		jpc.setBorder(new LineBorder(Color.white));
		
		GridBagConstraints grc = new GridBagConstraints();
		
		grc.fill = GridBagConstraints.HORIZONTAL;
		grc.ipadx = 10;
        grc.insets = new Insets(5, 5, 5, 5);

		for(int i = 0; i < description.length; i++) {			
			//String str = ;
			
			// 나는 선임이 한것과 다르게 문자열 크기가 달라지면 얘가 오류가 난가
			// 모든 문자열의 크기를 같게 만들수있다면....
			// 아니면 문자열의 중간만을 기준점 삼게 만들수 있다면...
			
			grc.anchor = GridBagConstraints.WEST;
			grc.gridy = 0;
			grc.gridx = i;
			
			// 이거 한번 쓰고나면 재입력 해줘야함
			int alignment = JLabel.CENTER;
			
			
			grc.weightx = petition_gaps.get(i);
			
			JLabel jl = new JLabel(description[i],JLabel.CENTER);
			
			jpc.add(jl, grc);
		}
		
		return jpc;
	}
	
//	protected static JPanel get_string_box_panel(
//			String[] description
//	) {
//		JPanel jpc = new JPanel();
//		
//		
//		jpc.setLayout(new BoxLayout(jpc, BoxLayout.X_AXIS));
//		jpc.setBorder(new LineBorder(Color.white));
//		
//		for(int i = 0; i < description.length; i++) {			
//			String str = description[i];
//			
//			int alignment = JLabel.CENTER;
//			int nomal  = 100;
//			jpc.add(Box.createHorizontalStrut(nomal));
//			
//			if(i != description.length - 1) {
//				
//				jpc.add(get_label(str, normal_font, alignment));
//				jpc.add(Box.createHorizontalStrut(nomal - str.length() * 10));
//			} else {
//				
//				jpc.add(get_label(str, normal_font, alignment));
//				
//			}
//			
//		}
//		
//		return jpc;
//	}
	

}
