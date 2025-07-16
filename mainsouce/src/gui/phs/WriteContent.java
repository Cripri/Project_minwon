package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WriteContent extends JPanel {

    public WriteContent() {
        setLayout(new BorderLayout());

        // 중앙 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // 제목 및 비밀번호 패널 (FlowLayout)
        JPanel titlePasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePasswordPanel.setOpaque(false);

        // 제목
        JLabel titleLabel = new JLabel("제목");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextField titleField = new JTextField(20);
        titleField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        titleField.setPreferredSize(new Dimension(250, 30)); // 가로 250, 세로 30 고정

        // 비밀번호
        JLabel pwdLabel = new JLabel("비밀번호");
        pwdLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextField pwdField = new JTextField(15);
        pwdField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        pwdField.setPreferredSize(new Dimension(150, 30)); // 가로 150, 세로 30 고정

        // 제목과 비밀번호 컴포넌트 추가
        titlePasswordPanel.add(titleLabel);
        titlePasswordPanel.add(titleField);
        titlePasswordPanel.add(pwdLabel);
        titlePasswordPanel.add(pwdField);

        // 내용 패널
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        JLabel contentLabel = new JLabel("내용");
        contentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextArea contentArea = new JTextArea(10, 40);
        contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        contentPanel.add(contentLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // 작성완료 버튼 패널 (오른쪽 하단)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton completeButton = new JButton("작성완료");
        completeButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        completeButton.setBackground(new Color(0, 120, 215));
        completeButton.setForeground(Color.WHITE);
        completeButton.setFocusPainted(false);
        completeButton.setBorderPainted(false);
        completeButton.setOpaque(true);
        completeButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(completeButton);

        // 컴포넌트 구성
        centerPanel.add(titlePasswordPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(contentPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(buttonPanel);

        add(centerPanel, BorderLayout.CENTER);
    }
}
