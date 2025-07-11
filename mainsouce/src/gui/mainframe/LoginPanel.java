package gui.mainframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gui.mainframe.components.RoundButton;
import gui.mainframe.components.RoundedButton;

class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public LoginPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(217, 217, 217));
        GridBagConstraints gbc = new GridBagConstraints();

        // 라디오 버튼
        JPanel radioPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        radioPanel.setBackground(new Color(217, 217, 217));
        JRadioButton radio1 = new JRadioButton("개인용");
        radio1.setBackground(new Color(217, 217, 217));
        JRadioButton radio2 = new JRadioButton("직원용");
        radio2.setBackground(new Color(217, 217, 217));
        radio1.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        radioPanel.add(radio1);
        radioPanel.add(radio2);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(radioPanel, gbc);

        // 아이디 라벨 + 텍스트필드
        JLabel idLabel = new JLabel("아이디");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(idLabel, gbc);

        JTextField idField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = 10;
        add(idField, gbc);

        // 비밀번호 라벨 + 텍스트필드
        JLabel pwLabel = new JLabel("비밀번호");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        add(pwLabel, gbc);

        JTextField pwField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 200;
        gbc.ipady = 10;
        add(pwField, gbc);

        // 직사각형 버튼
        RoundedButton signUpBtn = new RoundedButton("회원가입");
        RoundedButton loginBtn = new RoundedButton("로그인");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBackground(new Color(217, 217, 217));
        buttonPanel.add(signUpBtn);
        buttonPanel.add(loginBtn);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        add(buttonPanel, gbc);

        // 원형 버튼
        RoundButton kakaoBtn = new RoundButton("K");
        kakaoBtn.setBackground(new Color(254, 229, 0));
        kakaoBtn.setForeground(Color.BLACK);
        kakaoBtn.setPreferredSize(new Dimension(50, 50));

        RoundButton naverBtn = new RoundButton("N");
        naverBtn.setBackground(new Color(3, 199, 90));
        naverBtn.setForeground(Color.WHITE);
        naverBtn.setPreferredSize(new Dimension(50, 50));

        RoundButton passBtn = new RoundButton("P");
        passBtn.setBackground(new Color(255, 59, 75));
        passBtn.setForeground(Color.WHITE);
        passBtn.setPreferredSize(new Dimension(50, 50));

        JPanel roundPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        roundPanel.setBackground(new Color(217, 217, 217));
        roundPanel.add(kakaoBtn);
        roundPanel.add(naverBtn);
        roundPanel.add(passBtn);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(roundPanel, gbc);
    }
}