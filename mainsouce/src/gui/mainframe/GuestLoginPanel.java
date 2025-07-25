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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import function.connector.Members;
import function.connector.QueryRequest;
import gui.mainframe.components.BirthDateSelector;
import gui.mainframe.components.PlaceholderTextField;
import gui.mainframe.components.RoundedButton;
import gui.popup.wldb.pop_up_material.Get_pop_up_frames;

public class GuestLoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	String gender = "m";
    boolean isCertification = false;
	
	public GuestLoginPanel() {
		setLayout(new BorderLayout());
		setBackground(new Color(217, 217, 217));
		setBorder(new EmptyBorder(40, 60, 40, 60));

		JLabel titleLabel = new JLabel("비회원 로그인");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
		add(titleLabel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;

		Font labelFont = new Font("맑은 고딕", Font.BOLD, 14);
		Font inputFont = new Font("맑은 고딕", Font.PLAIN, 14);
		int row = 0;

		PlaceholderTextField nameField = new PlaceholderTextField("홍길동", 15);

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
			Get_pop_up_frames.get_public_alarm_frame("본인인증이 완료되셨습니다");
		});

		addRow(formPanel, gbc, row++, "핸드폰번호", phoneNumberField, certificationButton, labelFont, inputFont);

		// --- Submit Button ---
		JButton submitBtn = new RoundedButton("로그인");
		submitBtn.addActionListener((e) -> {
			if (male.isSelected()) {
				gender = "m";
			} else if (female.isSelected()) {
				gender = "f";
			}

			if (nameField.getText().trim().length() == 0) {
				Get_pop_up_frames.get_public_alarm_frame("이름을 입력해주세요.");
				return;
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
				Get_pop_up_frames.get_public_alarm_frame("생년, 월, 일을 모두 선택해주세요.");
				return;
			}

			if (phoneNumberField.getText().trim().length() == 0) {
				Get_pop_up_frames.get_public_alarm_frame("핸드폰 번호를 입력해주세요.");
				return;
			}
			if (!isCertification) {
				// 팝업
				Get_pop_up_frames.get_public_alarm_frame("본인인증을 진행해주세요.");
				return;
			}

			Members m = new Members();
			m.setMember_name(nameField.getText());
			m.setMember_birthday(bDate);
			m.setMember_gender(gender);
			m.setMember_phonenum(phoneNumberField.getText());
			//m.setDistrict_code(null);
			
			
			Date utilDate = m.getMember_birthday();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			// 중복 여부 확인
		    String query = "SELECT * FROM members WHERE member_name = ? AND member_birthday = ? AND member_phonenum = ? AND member_gender = ?";
		    QueryRequest<Members> checkRequest = new QueryRequest<>(
		        query,
		        List.of(m.getMember_name(), sqlDate, m.getMember_phonenum(), m.getMember_gender()),
		        Members.class,
		        MainFrameState.civil
		    );

		    Members existing = checkRequest.getSingleResult();

		    if (existing != null) {
		        // 기존 회원 → 정보 저장 후 마이페이지로 이동
		        MainFrameState.member = existing;
		        Get_pop_up_frames.get_log_in_out_frame(existing.getMember_name());
		    } else {
		        // 신규 회원 → DB에 추가
		        MainFrameState.civil.insert(m);
		        
		        // insert 후 select로 member_code가 채워진 객체 재조회
		        QueryRequest<Members> refresh = new QueryRequest<>(
		            "SELECT * FROM members WHERE member_name = ? AND member_birthday = ? AND member_phonenum = ? AND member_gender = ?",
		            List.of(m.getMember_name(), sqlDate, m.getMember_phonenum(), m.getMember_gender()),
		            Members.class,
		            MainFrameState.civil
		        );
		        MainFrameState.member = refresh.getSingleResult(); // member_code 있는 객체로 갱신
		        
		        Get_pop_up_frames.get_public_alarm_frame("회원 등록이 완료되었습니다.");
		    }
		    MainFrameState.frameTop.refreshButtons();
		    MyPage myPage = new MyPage();
		    MainFrameState.card.add("myPage", myPage);
		    MainFrameState.card.show("myPage");
		    
		    // 입력 초기화
		    nameField.setText("");
		    bds.reset();  // 아래 reset 메서드 필요
		    male.setSelected(true);
		    phoneNumberField.setText("");
		    isCertification = false;
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