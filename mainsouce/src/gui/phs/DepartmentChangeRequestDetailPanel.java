package gui.phs;

import java.awt.*;
import javax.swing.*;
import gui.mainframe.MainFrameState;

public class DepartmentChangeRequestDetailPanel extends JPanel {

    private final Font defaultFont = new Font("맑은고딕", Font.PLAIN, 14);
    private final Font boldFont = new Font("맑은고딕", Font.BOLD, 14);
    private final Font titleFont = new Font("맑은고딕", Font.BOLD, 20);

    public DepartmentChangeRequestDetailPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(200, 200, 200));

        // 🔹 중앙 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(220, 220, 220));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // 제목
        JLabel headerLabel = new JLabel("부서 변경 요청 내역", SwingConstants.CENTER);
        headerLabel.setFont(titleFont);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        centerPanel.add(headerLabel);

        // 신청인 정보
        JPanel infoPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        infoPanel.setBackground(new Color(220, 220, 220));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        

        infoPanel.add(wrapLeftAlignedLabel("접수번호"));
        infoPanel.add(wrapLeftAlignedLabel("성명"));
        infoPanel.add(new JLabel());

        infoPanel.add(wrapLeftAlignedLabel("AA1234-567890"));
        infoPanel.add(wrapLeftAlignedLabel("어쩌구씨"));
        infoPanel.add(new JLabel());

        centerPanel.add(infoPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // 제목 영역
        JTextArea titleArea = new JTextArea("제목");
        titleArea.setFont(boldFont);
        titleArea.setEditable(false);
        titleArea.setBackground(Color.WHITE);
        titleArea.setLineWrap(true);
        titleArea.setWrapStyleWord(true);
        titleArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        centerPanel.add(titleArea);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // 민원내용
        JTextArea contentArea = new JTextArea("민원내용");
        contentArea.setFont(defaultFont);
        contentArea.setEditable(false);
        contentArea.setBackground(Color.WHITE);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        centerPanel.add(contentArea);

        add(centerPanel, BorderLayout.CENTER);

        // 🔹 하단 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(220, 220, 220));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonRow.setBackground(new Color(220, 220, 220));

        JButton rejectButton = new JButton("반려");
        rejectButton.setBackground(new Color(178, 34, 34));
        rejectButton.setForeground(Color.WHITE);
        rejectButton.setFocusPainted(false);
        rejectButton.setPreferredSize(new Dimension(70, 30));
        rejectButton.setFont(defaultFont);

        JButton changeDeptButton = new JButton("부서변경");
        changeDeptButton.setBackground(new Color(30, 144, 255));
        changeDeptButton.setForeground(Color.WHITE);
        changeDeptButton.setFocusPainted(false);
        changeDeptButton.setPreferredSize(new Dimension(90, 30));
        changeDeptButton.setFont(defaultFont);

        JComboBox<String> deptComboBox = new JComboBox<>(new String[]{"부서목록"});
        deptComboBox.setPreferredSize(new Dimension(120, 30));
        deptComboBox.setFont(defaultFont);

        JButton listButton = new JButton("목록으로");
        listButton.setBackground(new Color(30, 144, 255));
        listButton.setForeground(Color.WHITE);
        listButton.setFocusPainted(false);
        listButton.setPreferredSize(new Dimension(90, 30));
        listButton.setFont(defaultFont);

        listButton.addActionListener(e -> {
            MainFrameState.card.show("DepartmentChangeRequestPanel");
        });

        buttonRow.add(rejectButton);
        buttonRow.add(changeDeptButton);
        buttonRow.add(deptComboBox);
        buttonRow.add(listButton);
        bottomPanel.add(buttonRow);

        JPanel explanationPanel = new JPanel(new GridLayout(2, 1));
        explanationPanel.setBackground(new Color(220, 220, 220));
        explanationPanel.add(centeredLabel("부서 선택 후 '부서 변경' 클릭 시 적용됩니다."));
        explanationPanel.add(centeredLabel("팀장급부터만 변경 가능합니다."));
        bottomPanel.add(explanationPanel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel wrapLeftAlignedLabel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(new Color(220, 220, 220));
        JLabel label = new JLabel(text);
        label.setFont(defaultFont);
        panel.add(label);
        return panel;
    }

    private JLabel centeredLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(defaultFont);
        return label;
    }
}
