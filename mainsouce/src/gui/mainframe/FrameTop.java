package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.mainframe.components.IconButton;
import gui.popup.wldb.pop_up_material.Get_pop_up_frames;
import gui.phs.CivilComplaintDetailPanel;

// 버튼들은 나중에 픽토그램 이미지로 변경 예정
public class FrameTop extends JPanel {
	private static final long serialVersionUID = 1L;

	private JPanel topButtonPanel;

	public FrameTop() {
		MainFrameState.frameTop = this;
		setLayout(new BorderLayout());
		setBackground(new Color(217, 217, 217));

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(217, 217, 217));
		add(topPanel, BorderLayout.NORTH);

		topButtonPanel = new JPanel(new GridLayout(0, 3));
		topButtonPanel.setBackground(new Color(217, 217, 217));
		topPanel.add(topButtonPanel, BorderLayout.EAST);

		// 이전 버튼 패널 (기존과 동일)
		JPanel previousButtonPanel = new JPanel(new GridLayout(0, 1));
		previousButtonPanel.setBackground(new Color(217, 217, 217));
		JButton previousBtn = new IconButton("뒤로가기", "resources/IconImage/뒤로가기.png");
		previousBtn.setOpaque(false);
		previousBtn.setContentAreaFilled(false);
		previousBtn.setBorderPainted(false);
		previousBtn.addActionListener((e) -> {
			MainFrameState.card.prev();
		});
		previousButtonPanel.add(previousBtn);
		topPanel.add(previousButtonPanel, BorderLayout.WEST);

		refreshButtons(); // 처음 버튼 세팅
	}

	public void refreshButtons() {
		topButtonPanel.removeAll();

		boolean login = MainFrameState.member != null;
		boolean emplogin = MainFrameState.employee != null;

		JButton mainPageBtn = new IconButton("메인페이지", "resources/IconImage/메인페이지.png");
		mainPageBtn.setOpaque(false);
		mainPageBtn.setContentAreaFilled(false);
		mainPageBtn.setBorderPainted(false);
		mainPageBtn.addActionListener((e) -> {
			if (emplogin) {
				MainFrameState.card.add("employeeMain", new EmployeeMainPanel());
				MainFrameState.card.show("employeeMain");
			} else {
				MainFrameState.card.show("firstPage");
			}
		});

		JButton myPageBtn = new IconButton("마이페이지", "resources/IconImage/마이페이지.png");
		myPageBtn.setOpaque(false);
		myPageBtn.setContentAreaFilled(false);
		myPageBtn.setBorderPainted(false);
		myPageBtn.addActionListener((e) -> {
			if (login) {
				MainFrameState.card.add("myPage",new MyPage());
				MainFrameState.card.show("myPage");
			} else if (emplogin) {
				MainFrameState.card.add("employeeMain", new EmployeeMainPanel());
				MainFrameState.card.show("employeeMain");
			} else {
				MainFrameState.card.add("login", new LoginPanel());
				MainFrameState.card.show("login");
			}
		});

		JButton loginBtn;
		if (login || emplogin) {
			loginBtn = new IconButton("로그아웃", "resources/IconImage/로그아웃.png");
			if (emplogin) {
				loginBtn.addActionListener((e) -> {
					MainFrameState.card.remove(MainFrameState.employeeMainPanel);
					MainFrameState.employeeMainPanel = null;
					MainFrameState.employee = null; // 로그아웃 처리
					Get_pop_up_frames.get_log_in_out_frame(null);
					refreshButtons(); // 버튼 갱신
					MainFrameState.card.add("login", new LoginPanel());
					MainFrameState.card.show("login");
				});
			} 
			if (login) {
				loginBtn.addActionListener((e) -> {
					Component[] components = MainFrameState.card.getComponents();
                	for (Component comp : components) {
                	    if (comp instanceof MyPage) {
                	    	// 마이페이지가 존재한다면
                	        MainFrameState.card.remove(comp);
                	        break;
                	    }
                	}
					MainFrameState.member = null; // 로그아웃 처리
					Get_pop_up_frames.get_log_in_out_frame(null);
					refreshButtons(); // 버튼 갱신
					MainFrameState.card.add("login", new LoginPanel());
					MainFrameState.card.show("login");
				});
			}
		} else {
			loginBtn = new IconButton("로그인", "resources/IconImage/로그인.png");
			loginBtn.addActionListener((e) -> {
				// 로그인 창으로 이동 또는 로그인 처리
				MainFrameState.card.add("login", new LoginPanel());
				MainFrameState.card.show("login");
			});
		}
		loginBtn.setOpaque(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setBorderPainted(false);

		topButtonPanel.add(mainPageBtn);
		topButtonPanel.add(myPageBtn);
		topButtonPanel.add(loginBtn);

		topButtonPanel.revalidate();
		topButtonPanel.repaint();
	}
}