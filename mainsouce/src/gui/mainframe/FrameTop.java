package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

// 버튼들은 나중에 픽토그램 이미지로 변경 예정
public class FrameTop extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public FrameTop() {
		setLayout(new BorderLayout());
		setBackground(new Color(217, 217, 217));
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(217, 217, 217));
		add(topPanel, BorderLayout.NORTH);
		JPanel topButtonPanel = new JPanel(new GridLayout(0, 3));
		topButtonPanel.setBackground(new Color(217, 217, 217));
		JPanel previousButtonPanel = new JPanel(new GridLayout(0,1));
		previousButtonPanel.setBackground(new Color(217, 217, 217));
		
		// 버튼들 (메인페이지 아직 연결 안함)
		JButton mainPageBtn = new JButton("메인페이지");
//		mainPageBtn.addActionListener((e) -> {
//			MainFrameState.card.show("my page");
//		});
		
		JButton myPageBtn = new JButton("마이페이지");
		myPageBtn.addActionListener((e) -> {
			MainFrameState.card.show("my page");
		});
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener((e) -> {
			MainFrameState.card.show("loginPanel");
		});
		
		JButton previousBtn = new JButton("뒤로가기");
		previousBtn.addActionListener((e) -> {
			MainFrameState.card.prev();
		});

		// 버튼 투명하게 설정
		for (JButton btn : new JButton[]{mainPageBtn, myPageBtn, loginBtn}) {
		    btn.setOpaque(false);
		    btn.setContentAreaFilled(false);
		    btn.setBorderPainted(false);
		    topButtonPanel.add(btn);
		}
		topPanel.add(topButtonPanel, BorderLayout.EAST);
		
		// 뒤로가기 추가
		previousBtn.setOpaque(false);
		previousBtn.setContentAreaFilled(false);
		previousBtn.setBorderPainted(false);
		previousButtonPanel.add(previousBtn);
		topPanel.add(previousButtonPanel, BorderLayout.WEST);
	}
}