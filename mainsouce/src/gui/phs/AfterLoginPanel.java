package gui.phs;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Year;
import javax.swing.*;

import gui.mainframe.components.addressComboBoxPanel;

public class AfterLoginPanel extends JPanel {

    public AfterLoginPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

        JPanel formPanel = new JPanel(new GridLayout(10, 1, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 250, 30, 250));
        formPanel.setBackground(new Color(217, 217, 217));

        JPanel[] rows = new JPanel[10];
        for (int i = 0; i < 10; i++) {
            rows[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            rows[i].setBackground(new Color(217, 217, 217));
            formPanel.add(rows[i]);
        }

        // 이름
        rows[0].add(new JLabel("신청인"));
        JTextField nameField = new JTextField(20);
        rows[0].add(nameField);

        // 신청인 구분
        rows[1].add(new JLabel("신청인 구분"));
        JRadioButton 개인 = new JRadioButton("개인", true);
        개인.setBackground(new Color(217, 217, 217));
        rows[1].add(개인);

        // 주소
        rows[2].add(new JLabel("주소"));
        rows[2].add(new addressComboBoxPanel().addressComboBoxPanel());

        // 연락처
        rows[3].add(new JLabel("연락처"));
        JTextField phoneField = new JTextField(20);
        rows[3].add(phoneField);

        // 생년월일 + 성별
        rows[4].add(new JLabel("생년월일"));
        JPanel birthdatePanel = createBirthdatePanel();
        rows[4].add(birthdatePanel);

        rows[4].add(new JLabel("성별:"));
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"남성", "여성"});
        genderBox.setPreferredSize(new Dimension(80, 25));
        rows[4].add(genderBox);

        // 통지방식
        rows[5].add(new JLabel("진행사항 통지방식"));
        JRadioButton emailBtn = new JRadioButton("이메일", true);
        JRadioButton phoneBtn = new JRadioButton("휴대폰");
        emailBtn.setBackground(new Color(217, 217, 217));
        phoneBtn.setBackground(new Color(217, 217, 217));
        ButtonGroup notificationGroup = new ButtonGroup();
        notificationGroup.add(emailBtn);
        notificationGroup.add(phoneBtn);
        rows[5].add(emailBtn);
        rows[5].add(phoneBtn);

        // 민원발생지역
        rows[6].add(new JLabel("민원발생지역"));
        JRadioButton sameBtn = new JRadioButton("동일", true);
        JRadioButton differentBtn = new JRadioButton("다름");
        sameBtn.setBackground(new Color(217, 217, 217));
        differentBtn.setBackground(new Color(217, 217, 217));
        ButtonGroup regionGroup = new ButtonGroup();
        regionGroup.add(sameBtn);
        regionGroup.add(differentBtn);

        rows[6].add(new JLabel("주소"));
        rows[6].add(new addressComboBoxPanel().addressComboBoxPanel());

        // 보안설정
        rows[7].add(new JLabel("보안설정"));
        JRadioButton securityYes = new JRadioButton();
        securityYes.setBackground(new Color(217, 217, 217));
        ButtonGroup securityGroup = new ButtonGroup();
        securityGroup.add(securityYes);
        rows[7].add(securityYes);
        

        // 안내문
        JLabel warningLabel = new JLabel("* 선택할 경우 외부로 비밀번호 제외, 비회원은 신청정보와 일치할 때만 확인할 수 있습니다.");
        rows[8].add(warningLabel);

        this.add(formPanel, BorderLayout.CENTER);
    }

    public JPanel createBirthdatePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setOpaque(false);

        int currentYear = Year.now().getValue();
        int startYear = 1850;
        int count = currentYear - startYear + 1;
        String[] yearStrings = new String[count];
        for (int i = 0; i < count; i++) {
            yearStrings[i] = String.valueOf(currentYear - i);
        }

        JComboBox<String> yearsBox = new JComboBox<>(yearStrings);
        JComboBox<Integer> monthsBox = new JComboBox<>();
        JComboBox<Integer> daysBox = new JComboBox<>();

        for (int i = 1; i <= 12; i++) monthsBox.addItem(i);
        for (int i = 1; i <= 31; i++) daysBox.addItem(i);

        ActionListener updateDays = e -> {
            int year = Integer.parseInt((String) yearsBox.getSelectedItem());
            int month = (Integer) monthsBox.getSelectedItem();
            int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
            Integer selectedDay = (Integer) daysBox.getSelectedItem();

            daysBox.removeAllItems();
            for (int i = 1; i <= maxDay; i++) daysBox.addItem(i);
            if (selectedDay != null && selectedDay <= maxDay) daysBox.setSelectedItem(selectedDay);
        };

        yearsBox.addActionListener(updateDays);
        monthsBox.addActionListener(updateDays);

        yearsBox.setPreferredSize(new Dimension(60, 23));
        monthsBox.setPreferredSize(new Dimension(45, 23));
        daysBox.setPreferredSize(new Dimension(45, 23));

        panel.add(yearsBox); panel.add(new JLabel("년"));
        panel.add(monthsBox); panel.add(new JLabel("월"));
        panel.add(daysBox); panel.add(new JLabel("일"));

        return panel;
    }
}
