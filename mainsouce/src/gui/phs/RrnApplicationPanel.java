package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import gui.mainframe.FrameTop;
import gui.mainframe.components.addressComboBoxPanel;

public class RrnApplicationPanel extends JPanel {

    public RrnApplicationPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

        // 상단 패널
        FrameTop topPanel = new FrameTop();
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        add(centerPanel, BorderLayout.CENTER);

        // 제목
        JLabel title = new JLabel("주민등록표 등본(초본) 발급");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 등본/초본 선택 라디오 버튼
        ButtonGroup docTypeGroup = new ButtonGroup();
        JRadioButton option1 = new JRadioButton("주민등록표 등본 발급");
        JRadioButton option2 = new JRadioButton("주민등록표 초본 발급");
        option1.setSelected(true);
        option1.setOpaque(false);
        option2.setOpaque(false);

        Font boldFont = new Font("맑은 고딕", Font.BOLD, 14);
        Font smallBoldFont = new Font("맑은 고딕", Font.BOLD, 12);

        option1.setFont(boldFont);
        option2.setFont(boldFont);
        docTypeGroup.add(option1);
        docTypeGroup.add(option2);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setOpaque(false);
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        radioPanel.add(option1);
        radioPanel.add(option2);
        centerPanel.add(radioPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // 소제목
        JLabel subTitle = new JLabel("신청내용");
        subTitle.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        subTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(subTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // 주소 확인
        JLabel addressLabel = new JLabel("주민등록상 주소 확인 (필수)");
        addressLabel.setFont(boldFont);
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(addressLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        comboPanel.setOpaque(false);
        comboPanel.add(new addressComboBoxPanel().addressComboBoxPanel());
        
        comboPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(comboPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 2)));

        JLabel guide1 = new JLabel("* 정보를 정확히 확인하세요. 주민등록상 주소와 다를 경우 신청이 불가합니다.");
        guide1.setFont(smallBoldFont);
        guide1.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide1);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // 발급형태 선택
        JLabel issueTypeLabel = new JLabel("발급형태 선택 (필수)");
        issueTypeLabel.setFont(boldFont);
        issueTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(issueTypeLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JRadioButton allIssue = new JRadioButton("전체 발급");
        JRadioButton partialIssue = new JRadioButton("선택 발급");
        allIssue.setOpaque(false);
        partialIssue.setOpaque(false);
        allIssue.setSelected(true);
        allIssue.setFont(boldFont);
        partialIssue.setFont(boldFont);

        ButtonGroup issueGroup = new ButtonGroup();
        issueGroup.add(allIssue);
        issueGroup.add(partialIssue);

        JPanel radioPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioPanel2.setOpaque(false);
        radioPanel2.add(allIssue);
        radioPanel2.add(partialIssue);
        radioPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(radioPanel2);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel guide2 = new JLabel("* 재외국민 등록사항을 제외한 모든 정보가 표시되지 않습니다. (예: 주민등록번호 뒷자리, 세대 정보 등)");
        guide2.setFont(smallBoldFont);
        guide2.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide2);

        centerPanel.add(Box.createVerticalGlue());

        // 버튼
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setOpaque(false);
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPanel.add(Box.createHorizontalGlue());

        JButton submitBtn = new JButton("신청하기");
        submitBtn.setBackground(new Color(0, 120, 255));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        submitBtn.setFocusPainted(false);
        submitBtn.setPreferredSize(new Dimension(100, 40));
        btnPanel.add(submitBtn);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(btnPanel);
    }

    // 테스트용 main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("민원 작성");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new RrnApplicationPanel());
            frame.setVisible(true);
        });
    }
}
