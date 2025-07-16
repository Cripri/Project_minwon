package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gui.mainframe.FrameTop;

public class AfterLoginPanel extends JPanel {

    public AfterLoginPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

        FrameTop topPanel = new FrameTop();
        this.add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(10, 1, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 250, 30, 250));
        formPanel.setBackground(new Color(217, 217, 217));

        JPanel[] rows = new JPanel[10];
        for (int i = 0; i < 10; i++) {
            rows[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            rows[i].setBackground(new Color(217, 217, 217));
            formPanel.add(rows[i]);
        }

        rows[0].add(new JLabel("신청인"));
        JTextField nameField = new JTextField(20);
        rows[0].add(nameField);

        rows[1].add(new JLabel("신청인 구분"));
        JRadioButton 개인 = new JRadioButton("개인", true);
        개인.setBackground(new Color(217, 217, 217));
        rows[1].add(개인);

        rows[2].add(new JLabel("주소"));
        JComboBox<String> sidoBox = new JComboBox<>(new String[]{"시도선택"});
        JComboBox<String> sigunguBox = new JComboBox<>(new String[]{"시군구선택"});
        sidoBox.setPreferredSize(new java.awt.Dimension(120, 25));
        sigunguBox.setPreferredSize(new java.awt.Dimension(120, 25));
        rows[2].add(sidoBox);
        rows[2].add(sigunguBox);

        rows[3].add(new JLabel("연락처"));
        JTextField phoneField = new JTextField(20);
        rows[3].add(phoneField);

        // 생년월일 및 성별
        rows[4].add(new JLabel("생년월일"));

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

        yearsBox.setPreferredSize(new java.awt.Dimension(80, 25));
        monthsBox.setPreferredSize(new java.awt.Dimension(60, 25));
        daysBox.setPreferredSize(new java.awt.Dimension(60, 25));

        rows[4].add(yearsBox); rows[4].add(new JLabel("년"));
        rows[4].add(monthsBox); rows[4].add(new JLabel("월"));
        rows[4].add(daysBox); rows[4].add(new JLabel("일"));

        rows[4].add(new JLabel("성별:"));
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"남성", "여성"});
        genderBox.setPreferredSize(new java.awt.Dimension(80, 25));
        rows[4].add(genderBox);

        // 진행사항 통지방식
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

        JComboBox<String> minwonSido = new JComboBox<>(new String[]{"시도"});
        JComboBox<String> minwonSigungu = new JComboBox<>(new String[]{"시군구"});
        minwonSido.setPreferredSize(new java.awt.Dimension(120, 25));
        minwonSigungu.setPreferredSize(new java.awt.Dimension(120, 25));

        rows[6].add(sameBtn);
        rows[6].add(differentBtn);
        rows[6].add(minwonSido);
        rows[6].add(minwonSigungu);

        // 보안설정
        rows[7].add(new JLabel("보안설정"));
        JRadioButton securityYes = new JRadioButton();
        securityYes.setBackground(new Color(217, 217, 217));
        ButtonGroup securityGroup = new ButtonGroup();
        securityGroup.add(securityYes);
        rows[7].add(securityYes);

        JLabel warningLabel = new JLabel("* 선택할 경우 외부로 비밀번호 제외, 비회원은 신청정보와 일치할 때만 확인할 수 있습니다.");
        rows[8].add(warningLabel);

        this.add(formPanel, BorderLayout.CENTER);
    }
}
