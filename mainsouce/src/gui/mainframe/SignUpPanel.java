package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import function.connector.Members;
import function.connector.QueryRequest;
import function.encryption.Encryptor;
import gui.mainframe.components.BirthDateSelector;
import gui.mainframe.components.PlaceholderTextField;
import gui.mainframe.components.RoundedButton;
import gui.mainframe.components.addressComboBoxPanel;

public class SignUpPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    boolean isDuplication = true;
    String gender = "m";
    boolean isCertification = false;
    
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
        
        JTextField idField = new JTextField(15);

        RoundedButton duplicationButton = new RoundedButton("중복확인");
        duplicationButton.addActionListener((e) -> {
           QueryRequest<Members> request = new QueryRequest<>(
                  "SELECT member_id FROM members WHERE member_id like ?", 
                   idField.getText(),
                   Members.class,
                   MainFrameState.civil
               );
               
               Members mem = request.getSingleResult();
               if (mem == null) {
                  // 팝업 사용가능한 아이디입니다.
                  System.out.println("사용가능한 아이디");
                  isDuplication = false;
               } else {
                  // 팝업 이미 사용중인 아이디입니다.
                  System.out.println("사용불가능한 아이디");
                  isDuplication = true;
               }
        });
        
        JPasswordField pwField = new JPasswordField(15);
        JPasswordField pwCheckField = new JPasswordField(15);
        
        PlaceholderTextField nameField = new PlaceholderTextField("홍길동", 15);

        addRow(formPanel, gbc, row++, "ID", idField, duplicationButton, labelFont, inputFont);
        addRow(formPanel, gbc, row++, "비밀번호", pwField, null, labelFont, inputFont);
        addRow(formPanel, gbc, row++, "비밀번호확인", pwCheckField, null, labelFont, inputFont);
        addRow(formPanel, gbc, row++, "이름", nameField, null, labelFont, inputFont);

        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel birthDateLabel = new JLabel("생년월일");
        formPanel.add(birthDateLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        BirthDateSelector bds = new BirthDateSelector();
        formPanel.add(bds.getBirthDatePanel(), gbc);
        
        // 성별
        gbc.gridx = 0;
        gbc.gridy = row;
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
        
        PlaceholderTextField phoneNumberField = new PlaceholderTextField("010-1234-5678", 15);
        RoundedButton certificationButton = new RoundedButton("본인인증");
        
        certificationButton.addActionListener((e) -> {
           // 본인인증 만들어지면 수정
           isCertification = true;
        });
        
        PlaceholderTextField emailField = new PlaceholderTextField("example@email.com", 15);

        addRow(formPanel, gbc, row++, "핸드폰번호", phoneNumberField, certificationButton, labelFont, inputFont);
        addRow(formPanel, gbc, row++, "이메일", emailField, null, labelFont, inputFont);

        // 주소
        gbc.gridx = 0;
        gbc.gridy = row;

        JLabel addressLabel = new JLabel("주소");
        formPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        addressComboBoxPanel address = new addressComboBoxPanel();
        formPanel.add(address.addressComboBoxPanel(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel addressDetailLabel = new JLabel("상세주소");
        formPanel.add(addressDetailLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        JTextField addressDetailField = new JTextField();
        formPanel.add(addressDetailField, gbc);

        // --- Submit Button ---
        JButton submitBtn = new RoundedButton("회원가입");
		submitBtn.addActionListener((e) -> {
			if (male.isSelected()) {
				gender = "m";
			} else if (female.isSelected()) {
				gender = "f";
			}

			Date bDate = new Date();
			if (bds.getYear() != null && bds.getMonth() != null && bds.getDay() != null) {
				int year = bds.getYear();
				int month = bds.getMonth();
				int day = bds.getDay();

				LocalDate bLocalDate = LocalDate.of(year, month, day);
				bDate = Date.from(bLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			} else {
				
				// TODO 팝업
				// 생년월일이 모두 선택되지 않았다는 알림 등을 보여줄 수 있음
				System.out.println("생년, 월, 일을 모두 선택해주세요.");
				return;
			}

			// 값이 선택된 경우만 처리
			if (address.getSido() != null && address.getSigungu() != null) {
				int districtCode = address.findDistrictCode(address.getSido(), address.getSigungu());
//			    System.out.println("선택된 지역 코드: " + districtCode);

				if (districtCode == -1) {
					System.err.println("해당 지역에 대한 코드가 없습니다.");
				}
			} else {
				System.out.println("시도와 시군구를 모두 선택해주세요.");
				return;
			}

			if (addressDetailField.getText().trim().length() == 0) {
				System.out.println("상세주소를 입력해주세요.");
				return;
			}

			if (isDuplication) {
				// 팝업
				System.out.println("아이디 중복확인을 해주세요.");
				return;
			}
			if (nameField.getText().trim().length() == 0) {
				System.out.println("이름을 입력해주세요.");
				return;
			}
			if (phoneNumberField.getText().trim().length() == 0) {
				System.out.println("핸드폰 번호를 입력해주세요.");
				return;
			}
			if (!isCertification) {
				// 팝업
				System.out.println("본인인증을 진행해주세요.");
				return;
			}
			if (pwField.getPassword().length == 0) {
				System.out.println("비밀번호를 확인해주세요");
				return;
			}
			String pw = new String(pwField.getPassword());
			String pwCheck;
			if (pwCheckField.getPassword() != null) {
				pwCheck = new String(pwCheckField.getPassword());
				if (!pw.equals(pwCheck)) {
					System.out.println("비밀번호가 일치하지않습니다.");
					return;
				}
			} else {
				System.out.println("비밀번호 확인란을 확인해주세요.");
				return;
			}
			

			Members m = new Members();
			m.setMember_id(idField.getText());
			m.setMember_password(new String(pwField.getPassword()));
			m.setMember_name(nameField.getText());
			m.setMember_birthday(bDate);
			m.setMember_gender(gender);
			m.setDistrict_code(address.findDistrictCode(address.getSido(), address.getSigungu()));
			m.setMember_ad_detail(addressDetailField.getText());
			m.setMember_phonenum(phoneNumberField.getText());
			m.setMember_email(emailField.getText());
			m.setMember_password_encrypted(Encryptor.encode(new String(pwField.getPassword())));

			// 완성되면 주석 풀기
			// MainFrameState.civil.insert(m);
        });
        
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
