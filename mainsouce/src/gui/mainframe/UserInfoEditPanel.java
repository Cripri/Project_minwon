package gui.mainframe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import gui.mainframe.components.RoundedButton;
import java.awt.*;

public class UserInfoEditPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public UserInfoEditPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));
        setBorder(new EmptyBorder(40, 60, 40, 60));

        // --- Title ---
        JLabel titleLabel = new JLabel("회원 정보 변경");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Form Panel ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("맑은 고딕", Font.BOLD, 14);
        Font inputFont = new Font("맑은 고딕", Font.PLAIN, 14);
        Font warnFont = new Font("맑은 고딕", Font.PLAIN, 12);
        Color warnColor = new Color(220, 20, 60);

        int row = 0;

        // 고정 ID
        addFixedRow(formPanel, gbc, row++, "ID", "eozzyeogu1212", labelFont, inputFont);

        // 비밀번호
        addRow(formPanel, gbc, row++, "비밀번호", new JPasswordField(15), null, labelFont, inputFont);
        addRow(formPanel, gbc, row++, "비밀번호확인", new JPasswordField(15), null, labelFont, inputFont);

        // 이름 + 생년월일 (고정)
        addFixedRow(formPanel, gbc, row++, "이름", "어쩌구씨", labelFont, inputFont);
        addFixedRow(formPanel, gbc, row++, "생년월일", "1999년12월12일", labelFont, inputFont);

        // 경고 메시지 (이름/생년월일)
        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel warn1 = new JLabel("※ 이름과 생년월일은 관리자 및 직원만 변경할 수 있습니다.");
        warn1.setFont(warnFont);
        warn1.setForeground(warnColor);
        formPanel.add(warn1, gbc);

        // 핸드폰번호
        JTextField phoneField = new JTextField("010-1234-5678", 15);
        RoundedButton verifyBtn = new RoundedButton("본인인증");
        verifyBtn.setPreferredSize(new Dimension(100, 30));
        verifyBtn.setBackground(new Color(0, 122, 255));
        verifyBtn.setForeground(Color.WHITE);
        verifyBtn.setFocusPainted(false);
        addRow(formPanel, gbc, row++, "핸드폰번호", phoneField, verifyBtn, labelFont, inputFont);

        // 경고 메시지 (핸드폰번호)
        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel warn2 = new JLabel("※ 핸드폰번호는 본인인증이 가능한 번호로만 변경할 수 있습니다.");
        warn2.setFont(warnFont);
        warn2.setForeground(warnColor);
        formPanel.add(warn2, gbc);

        // 주소
        JTextField zipField = new JTextField("12345", 6);
        RoundedButton addrBtn = new RoundedButton("주소검색");
        addrBtn.setPreferredSize(new Dimension(100, 30));
        addrBtn.setBackground(new Color(0, 122, 255));
        addrBtn.setForeground(Color.WHITE);
        addrBtn.setFocusPainted(false);
        addRow(formPanel, gbc, row++, "주소", zipField, addrBtn, labelFont, inputFont);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        formPanel.add(new JTextField("서울특별시 어쩌구 저쩌구 이거저거", 20), gbc);

        gbc.gridy = row++;
        formPanel.add(new JTextField("어디빌라 몇동 몇호", 20), gbc);

        // --- Submit Button ---
        JButton submitBtn = new RoundedButton("정보 변경");
        submitBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        submitBtn.setBackground(new Color(0, 122, 255));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setPreferredSize(new Dimension(150, 40));

        gbc.gridy = row++;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(submitBtn, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField field, JButton btn,
                        Font labelFont, Font inputFont) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        panel.add(label, gbc);

        gbc.gridx = 1;
        field.setFont(inputFont);
        panel.add(field, gbc);

        if (btn != null) {
            gbc.gridx = 2;
            panel.add(btn, gbc);
        }
    }

    private void addFixedRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, String valueText,
                             Font labelFont, Font inputFont) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        panel.add(label, gbc);

        gbc.gridx = 1;
        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setFont(inputFont);
        panel.add(valueLabel, gbc);
    }
}