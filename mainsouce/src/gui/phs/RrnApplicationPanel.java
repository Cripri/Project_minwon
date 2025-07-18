package gui.phs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gui.mainframe.components.addressComboBoxPanel;

public class RrnApplicationPanel extends JPanel {

    public RrnApplicationPanel() {
        setLayout(new BorderLayout());
        Color backgroundColor = new Color(217, 217, 217);
        Font boldFont = new Font("맑은 고딕", Font.BOLD, 14);
        Font smallBoldFont = new Font("맑은 고딕", Font.BOLD, 12);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(backgroundColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        add(centerPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("주민등록표 등본(초본) 발급");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel guide1 = new JLabel("* 정보를 정확히 확인하세요. 주민등록상 주소와 다를 경우 신청이 불가합니다.");
        guide1.setFont(smallBoldFont);
        guide1.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide1);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        ButtonGroup docTypeGroup = new ButtonGroup();
        JRadioButton option1 = new JRadioButton("주민등록표 등본 발급");
        JRadioButton option2 = new JRadioButton("주민등록표 초본 발급");
        option1.setSelected(true);
        option1.setFont(boldFont);
        option2.setFont(boldFont);
        option1.setOpaque(false);
        option2.setOpaque(false);
        docTypeGroup.add(option1);
        docTypeGroup.add(option2);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioPanel.setOpaque(false);
        radioPanel.add(option1);
        radioPanel.add(option2);
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(radioPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel subTitle = new JLabel("신청내용");
        subTitle.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        subTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(subTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        JLabel addressLabel = new JLabel("주민등록상 주소 확인 (필수)");
        addressLabel.setFont(boldFont);
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(addressLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addressComboBoxPanel addressCombo = new addressComboBoxPanel();
        JPanel addressPanel = addressCombo.addressComboBoxPanel();
        addressPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(addressPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel issueTypeLabel = new JLabel("발급형태 선택 (필수)");
        issueTypeLabel.setFont(boldFont);
        issueTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(issueTypeLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JRadioButton allIssue = new JRadioButton("전체 발급");
        JRadioButton partialIssue = new JRadioButton("선택 발급");
        allIssue.setSelected(true);
        allIssue.setFont(boldFont);
        partialIssue.setFont(boldFont);
        allIssue.setOpaque(false);
        partialIssue.setOpaque(false);

        ButtonGroup issueGroup = new ButtonGroup();
        issueGroup.add(allIssue);
        issueGroup.add(partialIssue);

        JPanel issueRadioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        issueRadioPanel.setOpaque(false);
        issueRadioPanel.add(allIssue);
        issueRadioPanel.add(partialIssue);
        issueRadioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(issueRadioPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel detailPanel = new JPanel(new CardLayout());
        detailPanel.setOpaque(false);
        detailPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.setVisible(false);

        JPanel copyPanel = new JPanel();
        copyPanel.setLayout(new BoxLayout(copyPanel, BoxLayout.Y_AXIS));
        copyPanel.setOpaque(false);
        copyPanel.add(createCheckboxWithSubOptions("1. 과거의 주소 변동 사항", boldFont, "addrHistory"));
        copyPanel.add(createCheckbox("2. 세대 구성 사유", boldFont));
        copyPanel.add(createCheckbox("3. 세대 구성 일자", boldFont));
        copyPanel.add(createCheckbox("4. 발생일 / 신고일", boldFont));
        copyPanel.add(createCheckboxWithSubOptions("5. 변동 사유", boldFont, "changeReason"));
        copyPanel.add(createCheckbox("6. 교부 대상자 외 세대주·세대원·외국인등록 이름", boldFont));
        copyPanel.add(createCheckboxWithSubOptions("7. 주민등록번호 뒷자리", boldFont, "rrnBack"));
        copyPanel.add(createCheckbox("8. 세대원의 세대주와의 관계", boldFont));
        copyPanel.add(createCheckbox("9. 동거인", boldFont));
        copyPanel.add(createCheckbox("10. 개인 인적 사항 변경 내용", boldFont));

        JPanel extractPanel = new JPanel();
        extractPanel.setLayout(new BoxLayout(extractPanel, BoxLayout.Y_AXIS));
        extractPanel.setOpaque(false);
        extractPanel.add(createCheckbox("1. 개인 인적 사항 변경 내용", boldFont));
        extractPanel.add(createCheckboxWithSubOptions("2. 과거의 주소 변동 사항", boldFont, "addrHistory"));
        extractPanel.add(createCheckbox("3. 과거의 주소 변동 중 세대주/세대주와의 관계", boldFont));
        extractPanel.add(createCheckboxWithSubOptions("4. 주민등록번호 뒷자리", boldFont, "rrnBack"));
        extractPanel.add(createCheckbox("5. 세대주의 성명과 세대주와의 관계", boldFont));
        extractPanel.add(createCheckbox("6. 발생일 / 신고일", boldFont));
        extractPanel.add(createCheckbox("7. 변동 사유", boldFont));
        extractPanel.add(createCheckboxWithSubOptions("8. 병역 사항", boldFont, "military"));
        extractPanel.add(createCheckbox("9. 국내거소신고번호 / 외국인등록번호", boldFont));

        detailPanel.add(copyPanel, "COPY");
        detailPanel.add(extractPanel, "EXTRACT");
        centerPanel.add(detailPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel guide2 = new JLabel("* 재외국민 등록사항을 제외한 모든 정보가 표시되지 않습니다.");
        guide2.setFont(smallBoldFont);
        guide2.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide2);
        centerPanel.add(Box.createVerticalGlue());

        // ✅ 전송 버튼
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        JButton submitBtn = new JButton("신청하기");
        submitBtn.setFont(boldFont);
        submitBtn.setBackground(new Color(0, 120, 255));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setPreferredSize(new Dimension(100, 40));
        btnPanel.add(submitBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(btnPanel);

        allIssue.addActionListener(e -> detailPanel.setVisible(false));
        partialIssue.addActionListener(e -> {
            detailPanel.setVisible(true);
            ((CardLayout) detailPanel.getLayout()).show(detailPanel, option1.isSelected() ? "COPY" : "EXTRACT");
        });

        option1.addActionListener(e -> {
            if (partialIssue.isSelected()) detailPanel.setVisible(true);
            ((CardLayout) detailPanel.getLayout()).show(detailPanel, "COPY");
        });

        option2.addActionListener(e -> {
            if (partialIssue.isSelected()) detailPanel.setVisible(true);
            ((CardLayout) detailPanel.getLayout()).show(detailPanel, "EXTRACT");
        });
    }

    private JPanel createCheckbox(String text, Font font) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(font);
        cb.setOpaque(false);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        p.setOpaque(false);
        p.add(cb);
        return p;
    }

    private JPanel createCheckboxWithSubOptions(String labelText, Font font, String key) {
        JCheckBox mainCheckBox = new JCheckBox(labelText);
        mainCheckBox.setFont(font);
        mainCheckBox.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        JPanel topLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topLine.setOpaque(false);
        topLine.add(mainCheckBox);
        mainPanel.add(topLine);

        JPanel subOptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        subOptionPanel.setOpaque(false);
        subOptionPanel.setVisible(false);

        switch (key) {
            case "addrHistory":
                JCheckBox totalInclude = new JCheckBox("전체 포함");
                JCheckBox directInput = new JCheckBox("직접 입력: 최근");
                JTextField yearField = new JTextField(3);
                JLabel yearLabel = new JLabel("년 포함");
                totalInclude.setOpaque(false);
                directInput.setOpaque(false);
                yearField.setMaximumSize(new Dimension(40, 20));
                subOptionPanel.add(totalInclude);
                subOptionPanel.add(directInput);
                subOptionPanel.add(yearField);
                subOptionPanel.add(yearLabel);
                break;
            case "changeReason":
            case "rrnBack":
                JCheckBox cb1 = new JCheckBox("본인");
                JCheckBox cb2 = new JCheckBox("세대원");
                cb1.setOpaque(false);
                cb2.setOpaque(false);
                subOptionPanel.add(cb1);
                subOptionPanel.add(cb2);
                break;
            case "military":
                JCheckBox basic = new JCheckBox("기본(입영/전역일자)");
                JCheckBox all = new JCheckBox("전체");
                basic.setOpaque(false);
                all.setOpaque(false);
                subOptionPanel.add(basic);
                subOptionPanel.add(all);
                break;
        }

        mainCheckBox.addActionListener(e -> subOptionPanel.setVisible(mainCheckBox.isSelected()));
        mainPanel.add(subOptionPanel);
        return mainPanel;
    }
} 