package gui.popup.wldb.pop_up_material;

import static gui.mainframe.MainFrameState.civil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import function.connector.Sinmungo;
import static gui.mainframe.MainFrameState.civil;



public class Get_pop_up_frames{


	static Color_list col = new Color_list();
	static Get_base geb = new Get_base();



	static Botton_input_state creation_state = new Botton_input_state();

	static public JButton get_account_makes_button() {
		JButton employees_creation = new JButton("직원 계정 생성");

		employees_creation.addActionListener(e ->{
			get_account_makes_frame();
		});

		return employees_creation;
	}

	static public JFrame get_account_makes_frame() {
		JFrame pop_up = geb.get_pop_up_base("직원 계정 생성", col, creation_state);
		pop_up.setLayout(new BorderLayout());

		JPanel pop_c = geb.get_panel(col);
		pop_c.setLayout(new GridLayout(4, 2));


		creation_state.setPop_up(pop_up);

		pop_c.add(
				geb.get_input_text_panel(
						creation_state, col, creation_state.getId()));
		pop_c.add(
				geb.get_input_text_panel(
						creation_state, col, creation_state.getName()));
		pop_c.add(
				geb.get_input_text_panel(
						creation_state, col, creation_state.getPassword()));
		pop_c.add(geb.get_panel(col));
		pop_c.add(
				geb.get_department_panel(creation_state, col));
		pop_c.add(
				geb.get_position_panel(creation_state, col));


		JPanel pop_s = geb.get_button_panel(creation_state, col);

		pop_up.add(pop_c, BorderLayout.CENTER);
		pop_up.add(pop_s, BorderLayout.SOUTH);

		return pop_up;
	}

	public static Botton_input_state getCreation_state() {
		return creation_state;
	}


	static Botton_input_state fix_state = new Botton_input_state();

	static public JButton get_account_fix_button() {
		JButton employees_fix = new JButton("고치다");

		employees_fix.addActionListener(e ->{
			get_account_fix_frame();
		});
		return employees_fix;
	}

	static public JFrame get_account_fix_frame() {
		JFrame pop_up = geb.get_pop_up_base("직원 계정 수정", col, fix_state);
		pop_up.setLayout(new BorderLayout());

		JPanel pop_c = geb.get_panel(col);
		pop_c.setLayout(new GridLayout(4, 2));


		fix_state.setPop_up(pop_up);

		pop_c.add(
				geb.get_input_text_panel(
						fix_state, col, fix_state.getId()));
		pop_c.add(geb.get_panel(col));
		pop_c.add(
				geb.get_input_text_panel(
						fix_state, col, fix_state.getPassword()));
		pop_c.add(geb.get_panel(col));
		pop_c.add(
				geb.get_department_panel(fix_state, col));
		pop_c.add(
				geb.get_position_panel(fix_state, col));
		pop_c.add(
				geb.get_change_dep_can_panel(fix_state, col));


		JPanel pop_s = geb.get_button_panel(fix_state, col);

		pop_up.add(pop_c, BorderLayout.CENTER);
		pop_up.add(pop_s, BorderLayout.SOUTH);
		return pop_up;
	}

	public static Botton_input_state getFix_state() {
		return fix_state;
	}


	static Botton_input_state delet_state = new Botton_input_state();

	static public JButton get_account_delet_button() {
		JButton employees_delet = new JButton("삭제");

		employees_delet.addActionListener(e ->{
			get_account_delet_frame();
		});
		return employees_delet;

	}

	static public JFrame get_account_delet_frame() {
		JFrame pop_up = geb.get_pop_up_base("", col, delet_state);
		pop_up.setLayout(new GridLayout(4,1));
		delet_state.setPop_up(pop_up);

		pop_up.add(geb.get_panel(col));
		pop_up.add(
				geb.get_deleted_name_panel("시험용", col));
		pop_up.add(
				geb.delet_yn_panel(delet_state, col));
		pop_up.add(
				geb.get_input_text_panel(
						delet_state, col, delet_state.getPassword()));

		return pop_up;
	}

	public static Botton_input_state getDelet_state() {
		return delet_state;
	}



	static Botton_input_state sinmungo_state = new Botton_input_state();

	static public JButton get_sinmungo_reply_button() {
		JButton sinmungo_reply = new JButton("민원 답변");

		sinmungo_reply.addActionListener(e ->{
			get_sinmungo_reply_frame();
		});
		return sinmungo_reply;
	}

	static public JFrame get_sinmungo_reply_frame() {
		JFrame pop_up = geb.get_pop_up_base("민원 답변", col, sinmungo_state);
		pop_up.setLayout(new BorderLayout());

		pop_up.add(
				geb.sinmungo_memo_panel(sinmungo_state, col),
				BorderLayout.CENTER);

		return pop_up;

	}


	public static Botton_input_state getSinmungo_state() {
		return sinmungo_state;
	}


	static Botton_input_state alarm_state = new Botton_input_state();

	static public JButton get_alarm_button() {
		JButton alarm_button = new JButton("공용+고정 알림");

		alarm_button.addActionListener(e ->{
			JFrame pop_up = geb.get_base("알림");
			Color_list col = new Color_list();

			JButton btn1 = new JButton("공용 알림");
			btn1.addActionListener(f ->{
				JFrame pop = get_public_alarm_double_frame(2, 0, "로그인", "실패");
			});
			JButton btn2 = new JButton("공용 알림");
			btn2.addActionListener(f ->{
				JFrame pop = get_public_alarm_frame
						("유저의 계정이 정지되었습니다");
			});
			JButton btn3 = new JButton("로그인/아웃 알림");
			btn3.addActionListener(f ->{
				JFrame pop = get_log_in_out_frame("보라돌이");
			});
			JButton btn4 = new JButton("틀림 알림");
			btn4.addActionListener(f ->{
				JFrame pop = get_wrong_frame("아이디");
			});
			JButton btn5 = new JButton("권한부족 알림");
			btn5.addActionListener(f ->{
				JFrame pop = get_you_can_not_frame();
			});

			pop_up.add(btn1);
			pop_up.add(btn2);
			pop_up.add(btn3);
			pop_up.add(btn4);
			pop_up.add(btn5);

		});
		return alarm_button;
	}


	public static JFrame get_public_alarm_double_frame(
			int index1, int index2,
			String str1 , String str2
	) {
		JFrame pop = geb.get_pop_up_base_small("알림", col);
		pop.setLayout(new GridLayout(3, 1));

		pop.add(new JPanel());
		pop.add(geb.get_public_pop_up_you_did_double(index1, index2, str1, str2));
		pop.add(geb.get_ok_button_panel(pop));

		return pop;
	}

	public static JFrame get_public_alarm_frame(
			String str1
	) {
		JFrame pop = geb.get_pop_up_base_small("알림", col);
		pop.setLayout(new GridLayout(3, 1));

		pop.add(new JPanel());
		pop.add(geb.get_public_pop_up_you_did(str1));
		pop.add(geb.get_ok_button_panel(pop));

		return pop;
	}


	public static JFrame get_log_in_out_frame(String str) {
		JFrame pop = geb.get_pop_up_base_small("로그인 알림", col);

		pop.setLayout(new GridLayout(3, 1));

		pop.add(new JPanel());
		pop.add(geb.get_pop_up_login_out(str));
		pop.add(geb.get_ok_button_panel(pop));

		return pop;
	}


	public static JFrame get_wrong_frame(String wrong_part) {
		JFrame pop = geb.get_pop_up_base_small("틀림 알림", col);

		pop.setLayout(new GridLayout(3, 1));

		pop.add(new JPanel());
		pop.add(geb.get_pop_up_wrong(wrong_part));
		pop.add(geb.get_ok_button_panel(pop));

		return pop;
	}


	public static JFrame get_you_can_not_frame() {
		JFrame pop = geb.get_pop_up_base_small("권한부족 알림", col);

		pop.setLayout(new GridLayout(3, 1));

		pop.add(new JPanel());
		pop.add(geb.get_pop_up_you_can_not());
		pop.add(geb.get_ok_button_panel(pop));

		return pop;
	}



	public static <T> JFrame get_yn_frame (String str1, T obj){

		JFrame delets = geb.get_pop_up_base_small("확인", col);
		delets.setLayout(new GridLayout(2, 1));

		delets.add(geb.get_public_pop_up_you_did(str1));

		JPanel jp = new JPanel(null);
		jp.setOpaque(false);

		JButton yes = new JButton("예");
		JButton no = new JButton("아니요");

		yes.setFont(new Font("기본", Font.CENTER_BASELINE, 20));
		no.setFont(new Font("기본", Font.CENTER_BASELINE, 20));
		yes.setBackground(new Color(241, 95, 95));
		no.setBackground(new Color(103, 153, 255));
		yes.setBounds(55, 10,
				100, 40);
		no.setBounds(215, 10,
				100, 40);

		yes.addActionListener(e ->{
			try {
				Class<?> clazz = obj.getClass();
				Object instance = clazz.getDeclaredConstructor().newInstance();

				Method[] fields = clazz.getDeclaredMethods();
				Method update;

				for(Method m : fields) {
					String name = m.getName().toLowerCase();

					if(name.endsWith("update")) {
						m.setAccessible(true);
						m.invoke(instance);

						break;
					}

				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			delets.dispose();
		});

		no.addActionListener(e ->{
			delets.dispose();
		});

		jp.add(yes);
		jp.add(no);

		delets.add(jp);

		return delets;

	}
}