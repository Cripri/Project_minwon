package gui.phs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.mainframe.components.addressComboBoxPanel;

public class RrnApplicationPanel extends JPanel {

    private JPanel selectiveIssuePanelCopy;     // 초본용 체크박스 패널
    private JPanel selectiveIssuePanelOriginal; // 등본용 체크박스 패널

    public RrnApplicationPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

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
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // 안내문
        JLabel guide1 = new JLabel("* 정보를 정확히 확인하세요. 주민등록상 주소와 다를 경우 신청이 불가합니다.");
        guide1.setFont(smallBoldFont);
        guide1.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide1);
        centerPanel.add(Box.createRigidArea(new Dimension(20, 20)));

        // 소제목 - 신청내용
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
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel newGuide = new JLabel("<html>* 개인 정보 보호를 위해 아래의 등 · 초본 사항 중 필요한 사항만 선택하여 신청할 수 있습니다.<br>"
                + "포함 여부를 선택하지 않을 경우 신청인 또는 교부 대상자의 성명, 생년월일, 주소 등 기본적인 사항만 제공됩니다.</html>");
        newGuide.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        newGuide.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(newGuide);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

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

        // 등본용 체크박스 텍스트
        String[] checkBoxTextsOriginal = {
                "1. 과거의 주소 변동 사항 포함",
                "2. 세대 구성 사유 포함",
                "3. 세대 구성 일자 포함",
                "4. 발생일 / 신고일 포함",
                "5. 변동 사유 포함",
                "6. 교부 대상자 외 세대주·세대원·외국인 등의 이름 포함",
                "7. 주민등록번호 뒷자리 포함",
                "8. 세대원의 세대주와의 관계 포함",
                "9. 동거인 포함"
        };

        // 초본용 체크박스 텍스트
        String[] checkBoxTextsCopy = {
                "1. 개인 인적 사항 변경 내용 포함",
                "2. 과거의 주소 변동 사항 포함",
                "3. 과거의 주소 변동 사항 중 세대주의 성명과 세대주와의 관계 포함",
                "4. 주민등록번호 뒷자리 포함",
                "5. 세대주의 성명과 세대주와의 관계 포함",
                "6. 발생일 / 신고일 포함",
                "7. 변동 사유 포함",
                "8. 병역 사항 포함",
                "9. 국내거소신고번호 / 외국인등록번호 포함"
        };

        // 등본용 체크박스 패널
        selectiveIssuePanelOriginal = new JPanel(new GridLayout(0, 2, 0, 5));
        selectiveIssuePanelOriginal.setOpaque(false);
        selectiveIssuePanelOriginal.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectiveIssuePanelOriginal.setVisible(false);

        
        for (String text : checkBoxTextsOriginal) {
            JCheckBox cb = new JCheckBox(text);
            cb.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            cb.setOpaque(false);
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);
            selectiveIssuePanelOriginal.add(cb);
        }

        // 초본용 체크박스 패널
        selectiveIssuePanelCopy = new JPanel(new GridLayout(0, 2, 0, 5));
        selectiveIssuePanelCopy.setOpaque(false);
        selectiveIssuePanelCopy.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectiveIssuePanelCopy.setVisible(false);

        for (String text : checkBoxTextsCopy) {
            JCheckBox cb = new JCheckBox(text);
            cb.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            cb.setOpaque(false);
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);
            selectiveIssuePanelCopy.add(cb);
        }

        centerPanel.add(selectiveIssuePanelOriginal);
        centerPanel.add(selectiveIssuePanelCopy);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // 가이드 라벨
        JLabel guide2 = new JLabel("* 재외국민 등록사항을 제외한 모든 정보가 표시되지 않습니다. (예: 주민등록번호 뒷자리, 세대 정보 등)");
        guide2.setFont(smallBoldFont);
        guide2.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide2);

        centerPanel.add(Box.createVerticalGlue());

        // 버튼 패널
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

        // 이벤트 리스너 - 선택 발급 선택 시 체크박스 패널 보이도록, 등본/초본 선택 시 갱신
        ActionListener updateCheckBoxPanels = e -> {
            boolean isPartial = partialIssue.isSelected();
            if (!isPartial) {
                selectiveIssuePanelOriginal.setVisible(false);
                selectiveIssuePanelCopy.setVisible(false);
            } else {
                if (option1.isSelected()) { // 등본 선택 시
                    selectiveIssuePanelOriginal.setVisible(true);
                    selectiveIssuePanelCopy.setVisible(false);
                } else { // 초본 선택 시
                    selectiveIssuePanelOriginal.setVisible(false);
                    selectiveIssuePanelCopy.setVisible(true);
                }
            }
            revalidate();
            repaint();
        };

        partialIssue.addActionListener(updateCheckBoxPanels);
        allIssue.addActionListener(updateCheckBoxPanels);
        option1.addActionListener(updateCheckBoxPanels);
        option2.addActionListener(updateCheckBoxPanels);
    }
}
