package gui.mainframe.wldb_main_frame.material;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import function.connector.Sinmungo;
import gui.mainframe.components.IconButton;
import gui.mainframe.model.Petition;
import gui.popup.wldb.pop_up_material.Get_pop_up_frames;

public class Get_main_base {
	
	static Font title_font = new Font("맑은 고딕", Font.BOLD, 30);
	static Font normal_font = new Font("맑은 고딕", Font.PLAIN, 18);
	static Font font = new Font("맑은 고딕", Font.PLAIN, 14);
	static Font headerFont = new Font("맑은 고딕", Font.BOLD, 15);
	
	static Color_list_main cols = new Color_list_main(); 
	static Get_pop_up_frames pop = new Get_pop_up_frames();
	
	private static Double[] petition_gap = {0.1, 0.3, 0.2, 0.2};
	static ArrayList<Double> petition_gaps = new ArrayList<>(Arrays.asList(petition_gap));

	private static int page_input_limit = 10;
	private static String card_name = "employees petition card ";
	private static int new_page = 1;
	private static CardLayout page = new CardLayout();
	
	
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
		
		jpc.setLayout(new BoxLayout(jpc, BoxLayout.X_AXIS));
		
		JLabel title_jl = new JLabel(title);
		
		title_jl.setHorizontalAlignment(JLabel.LEFT);		
		title_jl.setFont(title_font);
		
		jpc.add(Box.createHorizontalStrut(50));		
		jpc.add(title_jl);
		
		
		return jpc;
	}
	
	// 나도 페이지를 넣어보자
	public static JPanel get_card_employees_petition(
			String title, ArrayList<Sinmungo> petition_info
	) {
		JPanel jpc = get_public_panel();
		
		jpc.setLayout(new BorderLayout());		
		jpc.add(get_title_panel(title), BorderLayout.NORTH);
		
		JPanel jpb = new JPanel(new BorderLayout());
		
		String[] description = {"접수 번호", "내용", "처리상태", "만료일자"};		
		jpb.add(get_string_width_panel(description, true), BorderLayout.NORTH);
		
		JPanel jpcard = new JPanel(page);
		jpcard.setBorder(new LineBorder(Color.white, 5));
		
		
		int page_total = (int) Math.ceil((double)petition_info.size() / page_input_limit);
		
		if(page_total == 1) {
			jpcard.add(get_employees_petition_panel
					(Color_list_main.getInside_color(), petition_info));
		} else {
			
			for(int i = 0; i < page_total; i++) {
				ArrayList<Sinmungo> petitions = new ArrayList<Sinmungo>();
				
				int page_list = Math.min(
						page_input_limit, petition_info.size());
				
				for(int j = 0; j < page_list; j++) {
					petitions.add(petition_info.remove(0));
				}
				
				jpcard.add(get_employees_petition_panel(
								Color_list_main.getInside_color(),
								 petitions)
						, card_name + i
				);
				
			}
		}
		
		
		jpb.add(jpcard, BorderLayout.CENTER);
		
		jpc.add(jpb, BorderLayout.CENTER);
		jpc.add(get_paje_button(jpcard, page_total), BorderLayout.SOUTH);
		
		
		page.show(jpcard, card_name);
		
		return jpc;
	}
	
	
	public static JPanel get_employees_petition_panel(
			 Color col, ArrayList<Sinmungo> petition_info
	) {
		JPanel jpc = get_public_panel();
		// 타이틀은 해놨으니
		// 이제 참고 클레스 업데이트를 기다리며
		// 그 바탕 큰거랑 
		// 위에꺼랑 중간에 흰 박스 넣는 그런거 해두자	
		
		// 그냥 흰줄이고 뭐고 다 하나로 해서 넣으려했는데
		// 그러지말고 여기서 만들어 넣고 흰줄넣고를 for로 돌려야겠다
		
		
		jpc.setLayout(new BoxLayout(jpc, BoxLayout.Y_AXIS));
		
		///여기다가 클래스 분류해 String[]로 만들어 리스트 만들어 저장해줄애 뽑고
		/// 그 다음에 설명창 만들어서 넣어야지
		/// 그리고 설명창은 재활용항꺼니까 간격 외부입력으로 바꾸고
		
		jpc.setTransferHandler(null);
		
		for(Sinmungo sim : petition_info) {
			JPanel tm_jp = get_string_width_panel(get_simungo_info(sim), false);
			
			tm_jp.setMaximumSize(new Dimension(3000, 110));
			//tm_jp.setAlignmentX(Component.LEFT_ALIGNMENT);
			// 좀더 왼쪽으로 가주길 원했어...
			jpc.add(tm_jp);
		}
		
		int len = petition_info.size();
		if(len < page_input_limit) {
			for(int i = 0; i < page_input_limit - len; i++) {
				jpc.add(Box.createVerticalStrut(42));
			}
		}
		
		return jpc;
	}
	
	protected static JPanel get_paje_button(JPanel card, int page_total) {
		JPanel jp = get_public_panel();
		jp.setLayout(new GridLayout(2, 1));
		
		JPanel but_jp = get_public_panel();
		but_jp.setLayout(new GridLayout(1, 2));
		
		JPanel page_jp = get_public_panel();
		page_jp.setLayout(new GridLayout(1, 1));
		
		JLabel page_jl = new JLabel();
		page_jl.setFont(normal_font);
		page_jl.setHorizontalAlignment(JLabel.CENTER);
		page_update(page_jl, page_total);
		
		IconButton previous =  new IconButton(
				"이전 페이지", "./IconImage/이전페이지.png");
		IconButton next = new IconButton(
				"다음 페이지", "./IconImage/다음페이지.png");
		// 이런 공용 메서드를 원했어...
		
		previous.setHorizontalTextPosition(SwingConstants.RIGHT);
		next.setHorizontalTextPosition(SwingConstants.LEFT);

		next.addActionListener(e ->{
			if(new_page != page_total) {				
				page.next(card);
				new_page++;
				page_update(page_jl, page_total);
			}
		});
		
		previous.addActionListener(e ->{
			if(new_page != 1) {
				page.previous(card);
				new_page--;
				page_update(page_jl, page_total);
			}
		});
		
		but_jp.add(previous);
		but_jp.add(next);
		page_jp.add(page_jl);
		
		jp.add(but_jp);
		jp.add(page_jp);
		
		return jp;
	}
	
	private static void page_update(JLabel page_jl, int page_total) {
		page_jl.setText(new_page+ " / " + page_total);
	}
	
	// 여기서부터 문제
	protected static JPanel get_string_width_panel(
			String[] description, Boolean tf
	) {
		JPanel jpc = get_public_panel();
		
		jpc.setLayout(new GridBagLayout());
		jpc.setBorder(new LineBorder(Color.white));
		
		//System.out.println(jpc.getWidth());
		
		GridBagConstraints grc = new GridBagConstraints();
		
		grc.fill = GridBagConstraints.HORIZONTAL;
		grc.ipadx = 20;
        grc.insets = new Insets(0, 10, 0, 10);

        int size = description.length;
		for(int i = 0; i < size; i++) {			
			
			grc.anchor = GridBagConstraints.WEST;
			grc.gridy = 0;
			grc.gridx = i;
			
			JLabel jl = new JLabel(description[i],JLabel.CENTER);			
			
			
			if(tf) {
				jl.setFont(normal_font);
			} else {
				jl.setFont(headerFont);
				if(i == 1) {
					jl.setForeground(Color.blue.darker());
					jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
					jl.addMouseListener(new MouseAdapter() {
						
						@Override
						public void mouseEntered(MouseEvent e) {
							jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							jl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
						
						@Override
						public void mouseClicked(MouseEvent e) {
							pop.get_public_alarm_frame("현재 제작중");
						}
					});
				}
			}
			
			jl.setPreferredSize(new Dimension(130, 30));
			jl.setMaximumSize(new Dimension(130, 30));
			jl.setMinimumSize(new Dimension(130, 30));
			
			// 전부 같은 크기로 간주하게 만들기
			// 너무 긴글 한번 넣어서 확인
			// 약간 어긋나지만 크게 문제있을 정도는 아님
			// 해결
			// 이게 양끝 단어의 양끝은 맞는데 그거 맞추느라 다른건 다 신경을 안쓰더라
			// 뻐킹 새끼...
			
			// 이게 공간을 너무 늘려두니 누르는 범위가 너무 크게 나온다
			// 하지만 다른 방법이 생각나지 않는다...
			
			// 중간에 선 넣으려고 Box랑 섞어 썪더니 창을 늘리면 늘어나네...
			// 이걸 우찌해야하나...
			// Box에도 setsize 할수있으면 좋을텐데...
			// ... 잠까 닝거 되려나?
			jpc.add(jl, grc);
		}
		
		grc.anchor = GridBagConstraints.WEST;
		grc.gridy = 1;
		grc.gridx = 5;
		jpc.add(Box.createHorizontalStrut(200), grc);
		
		return jpc;
	}
	
	
	
	protected static String[] get_simungo_info(Sinmungo sim) {
		String status = get_status(sim.getStatus());
		
		String and_date = " ";
		Date d1 = sim.getCreate_date();
		LocalDate ld1;
		if(d1 != null) {
			
			ld1 = d1
					.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
			
			int working_day = 0;
			while(working_day > 13) {
				
				ld1.plusDays(1);
				DayOfWeek day_of_week = ld1.getDayOfWeek();
				if(
						!(
						day_of_week.getValue() == 6
						||
						day_of_week.getValue() == 7 
						)
				) {
					working_day++;
				}
			}
			and_date = ld1.toString();
			
		}
		
		
		
		String[] info = {
				sim.getSinmungo_code() == null ? " " : sim.getSinmungo_code().toString(),
				sim.getSinmungo_title() == null ? " " : sim.getSinmungo_title(),
				status,
				and_date
		};
		
		return info;
	}

	
	protected static String get_status(String string) {
		String status = " ";
		
		if(string.equals("P")) {
			status = "접수";
		} else if(string.equals("I")) {
			status = "처리중";
		} else if(string.equals("C")) {
			status = "완료";
		}
		
		return status;		
	}
	

}
