package gui.mainframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import function.connector.Employees;
import function.connector.Members;
import function.connector.QueryRequest;
import function.encryption.Encryptor;
import gui.mainframe.components.RoundedButton;

class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JRadioButton radio1;
	private JRadioButton radio2;

	public LoginPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(217, 217, 217));
        GridBagConstraints gbc = new GridBagConstraints();

        // 라디오 버튼
        JPanel radioPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        radioPanel.setBackground(new Color(217, 217, 217));
        radio1 = new JRadioButton("개인용");
        radio1.setBackground(new Color(217, 217, 217));
        radio2 = new JRadioButton("직원용");
        radio2.setBackground(new Color(217, 217, 217));
        radio1.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        radioPanel.add(radio1);
        radioPanel.add(radio2);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(radioPanel, gbc);
        
        // 아이디 라벨 + 텍스트필드
        JLabel idLabel = new JLabel("아이디");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(idLabel, gbc);

        JTextField idField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = 10;
        add(idField, gbc);

        // 비밀번호 라벨 + 텍스트필드
        JLabel pwLabel = new JLabel("비밀번호");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        add(pwLabel, gbc);

        JPasswordField pwField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 200;
        gbc.ipady = 10;
        add(pwField, gbc);

        // 직사각형 버튼
        RoundedButton signUpBtn = new RoundedButton("회원가입");
        signUpBtn.addActionListener((e) -> {
        	MainFrameState.card.show("signUp");
        });
        
		RoundedButton loginBtn = new RoundedButton("로그인");
		
		idField.addActionListener(e -> loginBtn.doClick());
        pwField.addActionListener(e -> loginBtn.doClick());
        
		loginBtn.addActionListener((e) -> {

			if (radio2.isSelected()) {
				QueryRequest<Employees> request = new QueryRequest<Employees>(
						"SELECT * FROM employees WHERE employee_id like ?", 
						idField.getText(), 
						Employees.class,
						MainFrameState.civil
						);
				Employees employee = request.getSingleResult();
	
				if (employee == null) {
					// 팝업 -> 아이디 틀림
					System.out.println("아이디가 틀림");
				} else {
					String pw = new String(pwField.getPassword());
					String enPw = employee.getEmployee_password();

					if (pw.matches(enPw)) {
						MainFrameState.employee = employee;
						MainFrameState.frameTop.refreshButtons();
						idField.setText("");
						pwField.setText("");
						
						if (MainFrameState.employeeMainPanel != null) {
		                    MainFrameState.employeeMainPanel.refreshPanel();
		                }

						MainFrameState.card.show("employeeMain");
					} else {
						// 팝업 -> 비밀번호 틀림
						System.out.println("비밀번호 틀림");
					}
				}
			} else {
				QueryRequest<Members> request = new QueryRequest<>(
						"SELECT * FROM members WHERE MEMBER_ID like ?",
						idField.getText(),
						Members.class,
						MainFrameState.civil
						);
				Members mem = request.getSingleResult();
				if (mem == null) {
					// 팝업 -> 아이디 틀림
					System.out.println("아이디가 틀림");
				} else {
					String pw = new String(pwField.getPassword());
					String enPw = mem.getMember_password_encrypted();

					if (Encryptor.matches(pw, enPw)) {
						MainFrameState.member = mem;
						MainFrameState.frameTop.refreshButtons();
						idField.setText("");
						pwField.setText("");
						
						String next = MainFrameState.postLoginTarget;
						if (next != null) {
					        MainFrameState.card.show(next);
					        MainFrameState.postLoginTarget = null;  // 한 번 쓰고 비워줌
						} else {
							// 카드 넘겨주기 mypage로
							MyPage myPage = new MyPage();
							MainFrameState.card.add("myPage", myPage);
							MainFrameState.card.show("firstPage");
						}
					} else {
						// 팝업 -> 비밀번호 틀림
						System.out.println("비밀번호 틀림");
					}
				}
			}
		});

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBackground(new Color(217, 217, 217));
        buttonPanel.add(signUpBtn);
        buttonPanel.add(loginBtn);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        add(buttonPanel, gbc);

        // 원형 버튼
        RoundedButton guestLogin = new RoundedButton("비회원 로그인");
        guestLogin.setPreferredSize(new Dimension(190, 35));
        guestLogin.addActionListener((e) -> {
        	MainFrameState.card.show("guestLogin");
        });

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(guestLogin, gbc);
    }
}