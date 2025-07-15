package gui.mainframe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.mainframe.components.BirthDateSelector;
import gui.mainframe.components.RoundedButton;
import gui.mainframe.components.addressComboBoxPanel;

import java.awt.*;

public class SignUpPanel extends JPanel {
    private static final long serialVersionUID = 1L;

	public SignUpPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));
        setBorder(new EmptyBorder(40, 60, 40, 60));

        // --- Title ---
        JLabel titleLabel = new JLabel("회원가입");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Form Panel ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("맑은 고딕", Font.BOLD, 14);
        Font inputFont = new Font("맑은 고딕", Font.PLAIN, 14);

        int row = 0;

        // Helper to add labeled field
        addRow(formPanel, gbc, row++, "ID", new JTextField(15), new RoundedButton("중복확인"), labelFont, inputFont);
        addRow(formPanel, gbc, row++, "비밀번호", new JPasswordField(15), null, labelFont, inputFont);
        addRow(formPanel, gbc, row++, "비밀번호확인", new JPasswordField(15), null, labelFont, inputFont);
        addRow(formPanel, gbc, row++, "이름", new JTextField(15), null, labelFont, inputFont);

        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel birthDateLabel = new JLabel("생년월일");
        formPanel.add(birthDateLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row;
        BirthDateSelector bds = new BirthDateSelector();
        formPanel.add(bds.getBirthDatePanel(), gbc);
        
        // 성별
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(new JLabel("성별"), gbc);

        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setOpaque(false);
        ButtonGroup genderGroup = new ButtonGroup();
        JRadioButton male = new JRadioButton("남");
        JRadioButton female = new JRadioButton("여");
        male.setBackground(new Color(217, 217, 217));
        female.setBackground(new Color(217, 217, 217));
        male.setSelected(true);
        
        genderGroup.add(male);
        genderGroup.add(female);
        genderPanel.add(male);
        genderPanel.add(female);
        formPanel.add(genderPanel, gbc);
        row++;

        addRow(formPanel, gbc, row++, "핸드폰번호", new JTextField("010-1234-5678", 15), new RoundedButton("본인인증"), labelFont, inputFont);
        addRow(formPanel, gbc, row++, "이메일", new JTextField("example@email.com", 15), null, labelFont, inputFont);

        gbc.gridx = 0;
        gbc.gridy = row;

        JLabel addressLabel = new JLabel("주소");
        formPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        addressComboBoxPanel address = new addressComboBoxPanel();
        formPanel.add(address.addressComboBoxPanel(), gbc);
//        JComboBox<String> sidoComboBox = new JComboBox<String>();
//        sidoComboBox.setBackground(Color.WHITE);
//    	sidoComboBox.addItem("서울특별시");
//    	sidoComboBox.addItem("경기도");
//    	formPanel.add(sidoComboBox, gbc);
    	
//    	gbc.gridx = 2;
//    	gbc.gridy = row++;
    	
//    	JComboBox<String> sigunguComboBox = new JComboBox<String>();
//    	sigunguComboBox.setBackground(Color.WHITE);
//    	sigunguComboBox.addItem("노원구");
//    	sigunguComboBox.addItem("안양시");
//    	formPanel.add(sigunguComboBox, gbc);
        
    	gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        formPanel.add(new JTextField(), gbc);

        // --- Submit Button ---
        JButton submitBtn = new RoundedButton("회원가입");
        submitBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        submitBtn.setBackground(new Color(0, 122, 255));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setPreferredSize(new Dimension(150, 40));

        gbc.gridy = row;
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
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        panel.add(label, gbc);

        gbc.gridx = 1;
        field.setFont(inputFont);
        panel.add(field, gbc);

        if (btn != null) {
            gbc.gridx = 2;
            btn.setBackground(new Color(0, 122, 255));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            panel.add(btn, gbc);
        }
    }
}
