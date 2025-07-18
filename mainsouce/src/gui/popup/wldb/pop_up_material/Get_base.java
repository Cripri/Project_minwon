package gui.popup.wldb.pop_up_material;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Get_base {
	
	static int font_size = 15;
	static int set_x_word = 20;
	static int set_x_jf = 150;
	static int set_y = 30;
	static int set_width = 100;
	static int set_height = 20;
	
	static Font bold = new Font("굵게", Font.BOLD, font_size + 10);
	static Font center = new Font("기본", Font.CENTER_BASELINE, font_size);
	
	static Botton_input_state color = new Botton_input_state();
	
	
	protected static JPanel get_input_text_panel(
			Botton_input_state state, Color_list col, String word
	) {
		
		JPanel jps = get_panel(col);
		
		JLabel jl = get_word(word);		
		JTextField jt = get_jt(word, state);
				
		jl.setBounds(set_x_word, set_y, set_width, set_height);
		jt.setBounds(set_x_jf, set_y, set_width, set_height);
		
		state.Nexts_add(jt);

		
		jt.addActionListener(e ->{
			String input_text = jt.getText();
			if(!(input_text.equals(word))) {
				state.input_textsAdd(word, input_text);
			}
			
			
			
			
			find_next(state, jt);				
		});		
		
		jps.add(jl);
		jps.add(jt);
		
		return jps;
	}
	
	
	
	
	protected static JPanel get_department_panel(
			Botton_input_state state, Color_list col
	) {
			
		JPanel dpts = get_panel(col);
		
		JLabel dpt_jl = get_word("담당부서");		
				
		dpt_jl.setBounds(set_x_word, set_y, set_width, set_height);
		
		
		//department == dpt;
		String[] tm = {"1", "2","3", "4","5","6","7","9","10"};
		// 나중에 부서 목록 생기면...
		// DB.department_list.toArray(new 리스트 변수 종류[0]);
		// List<String> list 가 들어오게될꺼다, 아마
		
		
		JComboBox dpt_jco = new JComboBox(tm);
		
		dpt_jco.setBounds(set_x_jf, set_y, set_width, set_height);		
		dpt_jco.setFont(center);		
		dpt_jco.setBackground(new Color(255, 255, 255));
		
		state.Nexts_add(dpt_jco);
		
		dpt_jco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//dpt_jco.setSelectedIndex(0);
				//dpt_jco.setSelectedItem(null);
				// 얘네 둘중 하나로 기본 설정해놓을 예정
				
				int index = dpt_jco.getSelectedIndex() == -1 ?
						0 : dpt_jco.getSelectedIndex();
				state.setDpt(tm[index]);
		
				dpt_jco.hidePopup();
				
				find_next(state, dpt_jco);
			}
		});
		
		dpts.add(dpt_jl);
		dpts.add(dpt_jco);
		
		return dpts;
	}
	
	
	
	protected static JPanel get_position_panel(
			Botton_input_state state, Color_list col
	) {
		// 직원 부서 변경이 아니라
		// 민원 처리 받길수있는 권한 주는거라고...
		JPanel posins = get_panel(col);
		
		JLabel posin_jl = get_word("직급");		
				
		posin_jl.setBounds(set_x_word, set_y, set_width, set_height);
		
		
		//position == posin;
		String[] tm = {"a", "b","c", "d","e","f","g","h","i"};
		
		// DB.position_list.toArray(new 리스트 변수 종류[0]);
		
		
		JComboBox posin_jco = new JComboBox(tm);
		
		posin_jco.setFont(center);		
		posin_jco.setBounds(set_x_jf, set_y, set_width, set_height);		
		posin_jco.setBackground(new Color(255, 255, 255));
		
		state.Nexts_add(posin_jco);
		
		posin_jco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = posin_jco.getSelectedIndex() == -1 ?
						0 : posin_jco.getSelectedIndex();
				state.setPosin(tm[index]);
				
				posin_jco.hidePopup();
				
				find_next(state, posin_jco);
			}
		});
		
		posins.add(posin_jl);
		posins.add(posin_jco);
		
		return posins;
	}
	
	protected static JPanel get_change_dep_can_panel(
			Botton_input_state state, Color_list col
	) {
		
		JPanel change_dep = get_panel(col);
		
		JLabel change_dep_jl = get_word("민원 부서 변경");				
		change_dep_jl.setBounds(set_x_word, set_y, set_width, set_height);
		
		
		//department == dpt;
		String[] tm = {"불가", "가능"};
		
		JComboBox change_dep_jco = new JComboBox(tm);
		
		change_dep_jco.setFont(center);		
		change_dep_jco.setBounds(set_x_jf, set_y, set_width, set_height);		
		change_dep_jco.setBackground(new Color(255, 255, 255));
		
		state.Nexts_add(change_dep_jco);
		
		change_dep_jco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean can = change_dep_jco.getSelectedIndex() == 1 ?
						true : false;
				state.setChange_dep_authority(can);
				change_dep_jco.hidePopup();
				
				find_next(state, change_dep_jco);
								
			}
		});
		
		change_dep.add(change_dep_jl);
		change_dep.add(change_dep_jco);
		
		return change_dep;
	}
	
	
	
	protected static JPanel get_deleted_name_panel(String id, Color_list col) {
		JPanel tm = new JPanel();//set_panel(state);
		// 판낼에 색을 칠하니 글자가 안보인다, 근데 얘만 그럼, 딴애들은 말짱...
		tm.setBackground(col.getInside_color());
		// panel.setLayout(null); 때문
		// 근대 애 뺴면 다른애들은 이상해짐
		// 따로해야함
		
		JLabel ids = new JLabel("계정 " + id + " 을(를) 삭제하시겠습니까?");
		ids.setFont(bold);
		ids.setHorizontalAlignment(JLabel.CENTER);
		ids.setForeground(new Color(1, 1, 1));
		
		tm.add(ids);
		return tm;
	}
	
	protected static JPanel delet_yn_panel(
			Botton_input_state state, Color_list col
	) {
		
		JPanel delets = get_panel(col);
			
		JButton yes = new JButton("예");
		JButton no = new JButton("아니요");
		
		yes.setFont(bold);
		no.setFont(bold);
		yes.setBackground(new Color(241, 95, 95));
		no.setBackground(new Color(103, 153, 255));
		yes.setBounds(set_x_word + 55, set_y - 20,
				set_width * 2 - 30, set_height * 2);
		no.setBounds(set_x_jf * 2 + 55, set_y - 20,
				set_width * 2 - 30, set_height * 2);
		
		yes.addActionListener(e ->{			
			String passin = state.getInput_texts().get(state.getPassword());
			if(passin != "" && passin != null) {
				state.setDeleted(true);
				state.getPop_up().dispose();
			} else {
				JFrame not = get_now_not_input_panel("비밀번호");
				state.getNexts().get(0).grabFocus();
			}
			
		});
		
		no.addActionListener(e ->{
			state.setDeleted(false);
			//delets.disable();
			// ㅅㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂ
			state.getPop_up().dispose();
			// 내가 창이 아니라 패널을 닫으려 해서 이꼴이 났구나...
		});
		
		delets.add(yes);
		delets.add(no);

		return delets;
	}
	
	protected static JPanel sinmungo_memo_panel(
			Botton_input_state state,  Color_list col
	) {
		
		JPanel memo_jp = get_panel(col);
		memo_jp.setLayout(new BorderLayout());
		
		JTextArea memo = new JTextArea();		
		memo.setBorder(new LineBorder(col.getInside_color(), 5));
		state.Nexts_add(memo);
		
		String set_guide = "내용 입력";
		
		memo.setText(set_guide);		
		memo.setLineWrap(true);
		memo.setVisible(true);
		
		memo.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				
				if(memo.getText().equals(set_guide)) {
					memo.setText("");
					memo.setFont(center);
				}	
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(memo.getText().length() == 0) {
					memo.setText(set_guide);
				}
			}
			
		});
		
		
		JButton memo_completed = new JButton("작성 완료");
		
		memo_completed.setSize(set_width, set_height);
		
		memo_completed.addActionListener(e ->{
			String texts = memo.getText();
			state.setMemo(texts);
			if((!texts.equals(set_guide)) && texts.length() > 1) {
				state.getPop_up().dispose();
			} else {
				JFrame no = get_now_not_input_panel("민원 본문");
				memo.grabFocus();
			}
						
		});
		
		JPanel jp_down = new JPanel();
		jp_down.setBackground(col.getInside_color());
		jp_down.add(memo_completed);
		
		memo_jp.add(new JScrollPane(memo) , BorderLayout.CENTER);
		memo_jp.add(jp_down, BorderLayout.SOUTH);
		return memo_jp;
	}
	
	private static JButton get_input_check_button(
			Botton_input_state state, String text
	) {
		JButton check = new JButton(text);
		
		check.setFont(bold);		
		check.setBackground(Color_list.getOutside_color());
		check.setSize(set_width, set_height);
		
		check.setSize(set_width, (int) (set_height * 1.5));
		
		check.addActionListener(e ->{	
			
			HashMap<String, String> texts = state.getInput_texts();
			boolean ok = true;
			String this_neme = "";
			
			for(JComponent jc : state.getNexts()) {
				
				if(jc instanceof JTextField) {					
					String str = ((JTextField) jc).getText();
					
					if(str == null || str == "" || texts.containsKey(str)) {
						//jc.getName().equals(str)
						ok = false;
						this_neme = jc.getName();
						System.out.println(this_neme + "툴이름");
						break;
					}
					// 얻어낸 텍스트(현재 입력된값)가
					// 해쉬 맴중 하나의 키(디폴트값)로 들어맞으면...
					// 이게 아예 시선을 주지를 않으니 안되네...
					// 그런 내가 넣와야지
				} 
			}
			// 내가 포커스로 임시 자료 저장이 되게 만들어놨음
			// 이제 버튼 누르면 그 임시 자료들을 보내면 되는데...
			// 아직 데이터 베이스 연결도 잘 모르겠어서 
			// 자료들 어디로 보내야 할지도 잘 모르겠다..
			// 일단 지금으로서는 자료 입력 되야 버튼 눌러지게 해놨음
			
			// 찾아보니 데이터 넣기는 있는데 데이터 받아오기는 없는데?
			// 아직 덜 만들어진듯...
			if(ok) {
				state.getPop_up().dispose();
			} else {
				JFrame no = get_now_not_input_panel(this_neme);
			}
			
		});
		return check;
		
	}
	
	
	private static JButton get_input_check_button(Botton_input_state state) {		
		return get_input_check_button(state, "입력 완료");		
	}
	
	
	
	protected static JPanel get_ok_button_panel(JFrame jf) {		
		JPanel tmtm = new JPanel();
		
		JButton ok = new JButton("확인");
		ok.setFont(center);		
		ok.setBackground(Color_list.getOutside_color());
		ok.setSize(set_width, set_height);
		
		ok.addActionListener(e ->{
			jf.dispose();
		});
		
		tmtm.add(ok);
		return tmtm;
	}

	protected static JPanel get_panel(Color_list col) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(col.getInside_color());
		
		
		return panel;
	}
	
	protected static JPanel get_button_panel(
			Botton_input_state state, Color_list col
	) {
		JPanel bjp = new JPanel();
		bjp.setBackground(col.getInside_color());		
		bjp.add(get_input_check_button(state));
				
		return bjp;
	}
	
	
	private static JLabel get_word(String title) {
		JLabel jl = new JLabel(title);
		jl.setHorizontalAlignment(JLabel.LEFT);
		jl.setFont(center);
		return jl;
	}
	
	private static JLabel get_word_center(String title) {
		JLabel jl = get_word(title);
		jl.setHorizontalAlignment(JLabel.CENTER);
		
		return jl;
	}

	private static JTextField get_jt(String text, Botton_input_state state) {
		JTextField jt = new JTextField(text);
		
		jt.setName(text);
		
		jt.addFocusListener(new FocusAdapter() {			
			@Override
			public void focusGained(FocusEvent e) {
				if(jt.getText().equals(text)) {					
					jt.setText("");
					jt.setFont(center);					
				}				
			}		
			
			@Override
			public void focusLost(FocusEvent e) {
				if(jt.getText().length() == 0) {
					jt.setText(text);
				} else {
					state.input_textsAdd(text, jt.getText());
				}
			}
		});
		
		
		return jt;
	}
	
	
	private static void find_next(
			Botton_input_state state, JComponent tf
	) {
		int size = state.getNexts().size();
		for(int i = 0; i < size; i++) {
			
			if(state.getNexts().get(i) == tf && i + 1 != size) {
				state.getNexts().get(i + 1).grabFocus();
			}
		}
	}
	
	
	
	
	private static JFrame get_now_not_input_panel(String lack) {
		JFrame no = new JFrame("경고");
		
		no.setLayout(new BorderLayout());
		
		JLabel ww = get_word(lack + "을(를) 입력해주세요");
		JPanel pp = get_ok_button_panel(no);
		
		ww.setHorizontalAlignment(JLabel.CENTER);
		ww.setFont(bold);
		
		pp.setFont(center);
		
		no.add(ww, BorderLayout.CENTER);
		no.add(pp, BorderLayout.SOUTH);
		
		no.setSize(400, 200);
		no.setLocationRelativeTo(null);
		no.setVisible(true);
		no.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		return no;
	}

	
	
	
	protected static JFrame get_pop_up_base(
			String title, Color_list col ,Botton_input_state state
	) {
		//팝업 저장
		
		JFrame pop_up = get_pop_up_base(title, col);
		
		state.setPop_up(pop_up);
		return pop_up;
	}
	
	public static JFrame get_pop_up_base(String title, Color_list col) {
		//팝업 노 저장
		JFrame pop_up = new JFrame(title);
		
		pop_up.setResizable(false);
		pop_up.setLocationRelativeTo(null);
		pop_up.setBounds(650, 300, 600, 350);
		pop_up.setSize(600, 350);
		pop_up.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pop_up.getRootPane().setBorder(col.getLine());
		pop_up.getContentPane().setBackground(col.getInside_color());
		
		pop_up.setVisible(true);
		
		return pop_up;
	}
	
	public static JFrame get_pop_up_base_small(String title, Color_list col) {
		JFrame pop_up = get_pop_up_base(title, col);
		pop_up.setBounds(750, 400, 400, 200);
		
		return pop_up;
	}
	
	
	public static JFrame get_base(String title) {// 시험용 버튼거치대
		
		JFrame base = new JFrame(title);
		
		base.setBounds(450, 200, 1000, 600);
		base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		base.setLocationRelativeTo(null);
		base.setVisible(true);
		
		base.setLayout(new FlowLayout());
		
		return base;
	}
	
	
	protected static JLabel get_public_pop_up_you_did_double(
			int index1, int index2,
			String str1 , String str2
	) {
		
		String[] contents1 = {
				"을(를) ", "이(가) ", "에 ", "의 "};
		String[] contents2 = {
				"하셨습니다.", "되었습니다.", " 완료되었습니다.", " 하시겠습니까?"};
		if(
				index1 < 0 || index2 < 0 
				|| index1 > contents1.length - 1 
				|| index2 > contents2.length
		) {
			return null;
		}
		
		String content = str1 + contents1[index1] + str2 + contents2[index2];
		
		return get_word_center(content);
		
		// 사용예: 민원등록을 취소하셨습니다	
		// 사용예 : 로그인에 실패하셨습니다
		
		// 이거말고 경우의 수가 또 뭐가 있지?
	}
	
	public static JLabel get_public_pop_up_you_did(String str) {
		
		return get_word_center(str);
	}
	
	protected static JLabel get_pop_up_login_out(String str1) {
		
		String content = str1 == null ? str1 + "님 환영합니다." : "로그아웃 되셨습니다.";
		
		return get_word_center(content);
	}
	
	
	
	protected static JLabel get_pop_up_wrong(String str1) {
		
		String content = new String("잘못된 " + str1 + " 입니다");
		
		return get_word_center(content);
	}
	
	
	protected static JLabel get_pop_up_you_can_not() {
		
		String content = new String("권한이 부족합니다");
		
		return get_word_center(content);
		
	}
	
	
}





