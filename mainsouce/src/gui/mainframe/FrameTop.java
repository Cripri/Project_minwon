package gui.mainframe;

import function.drawingsign.DrawingSign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

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
        JButton previousBtn = new JButton("뒤로가기");
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

        boolean login = MainFrameState.login_id != null;

        JButton mainPageBtn = new JButton("메인페이지");
        mainPageBtn.setOpaque(false);
        mainPageBtn.setContentAreaFilled(false);
        mainPageBtn.setBorderPainted(false);
        mainPageBtn.addActionListener((e) -> {
            MainFrameState.card.show("firstPage");
        });

        JButton myPageBtn = new JButton("마이페이지");
        myPageBtn.setOpaque(false);
        myPageBtn.setContentAreaFilled(false);
        myPageBtn.setBorderPainted(false);
        myPageBtn.addActionListener((e) -> {
            if (login) {
                MainFrameState.card.show("myPage");
            } else {
                MainFrameState.card.show("login");
            }
        });

        JButton loginBtn;
        if (login) {
            loginBtn = new JButton("로그아웃");
            loginBtn.addActionListener((e) -> {
                MainFrameState.login_id = null;  // 로그아웃 처리
                refreshButtons(); // 버튼 갱신
                MainFrameState.card.show("login");
            });
        } else {
            loginBtn = new JButton("로그인");
            loginBtn.addActionListener((e) -> {
                // 로그인 창으로 이동 또는 로그인 처리
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