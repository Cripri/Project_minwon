package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import function.complaintkeyword.ComplaintClassifier;
import function.connector.Department;
import function.connector.Employees;
import function.connector.Members;
import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.MainFrameState;

public class WriteContent extends JPanel {

    public WriteContent() {
        setLayout(new BorderLayout());

        // 중앙 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // 제목 및 비밀번호 패널 (FlowLayout)
        JPanel titlePasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePasswordPanel.setOpaque(false);

        // 제목
        JLabel titleLabel = new JLabel("제목");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextField titleField = new JTextField(20);
        titleField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        titleField.setPreferredSize(new Dimension(250, 30)); // 가로 250, 세로 30 고정

        // 비밀번호
        JLabel pwdLabel = new JLabel("비밀번호");
        pwdLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextField pwdField = new JTextField(15);
        pwdField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        pwdField.setPreferredSize(new Dimension(150, 30)); // 가로 150, 세로 30 고정

        // 제목과 비밀번호 컴포넌트 추가
        titlePasswordPanel.add(titleLabel);
        titlePasswordPanel.add(titleField);
        titlePasswordPanel.add(pwdLabel);
        titlePasswordPanel.add(pwdField);

        // 내용 패널
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        JLabel contentLabel = new JLabel("내용");
        contentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextArea contentArea = new JTextArea(10, 40);
        contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        contentPanel.add(contentLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // 작성완료 버튼 패널 (오른쪽 하단)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton completeButton = new JButton("작성완료");
        completeButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        completeButton.setBackground(new Color(0, 120, 215));
        completeButton.setForeground(Color.WHITE);
        completeButton.setFocusPainted(false);
        completeButton.setBorderPainted(false);
        completeButton.setOpaque(true);
        completeButton.setPreferredSize(new Dimension(100, 30));
        
        completeButton.addActionListener((e) -> {
        	Members m =  MainFrameState.member;
        	Sinmungo s = new Sinmungo();
        	s.setMember_code(m.getMember_code());
        	
        	s.setSinmungo_title(titleField.getText());
        	s.setSinmungo_content(contentArea.getText());
        	
        	// 부서이름 or "판별 불가"
        	String dep = ComplaintClassifier.classify(contentArea.getText());
        	QueryRequest<Department> request = new QueryRequest<Department>(
        			"select * from department where department_name like ?", 
        			dep,
        			Department.class,
        			MainFrameState.civil
        			);
        			
        	Department d = request.getSingleResult();
        	
        	QueryRequest<Employees> request1 = new QueryRequest<Employees>(
        			"select * from employees where position_code = 1 and department_code like ?", 
        			d.getDepartment_code(),
        			Employees.class,
        			MainFrameState.civil
        			);
        	List<Employees> eList = request1.getResultList();
        	// 주무관만 들어감
        	s.setEmployee_code((int)(Math.random() * eList.size()));
        	
        	if (pwdField.getText() != null) {
        		s.setSecurity_set("t");
        		s.setSecurity_password(pwdField.getText());
        	} else {
        		s.setSecurity_set("f");
        		s.setSecurity_password(null);
        	}
        	s.setCreate_date(new Date());
        	s.setStatus("P");
        	// TODO 팝업 
        	
        	// 팝업 완료되면 주석 풀기
        	 MainFrameState.civil.insert(s);
        	 MainFrameState.card.show("myPage");
        });
        
        buttonPanel.add(completeButton);

        // 컴포넌트 구성
        centerPanel.add(titlePasswordPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(contentPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(buttonPanel);

        add(centerPanel, BorderLayout.CENTER);

        // ▶ 전송 버튼 패널 (오른쪽 아래)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(217, 217, 217));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 60));

        JButton submitButton = new JButton("전송");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.setBackground(new Color(45, 140, 240));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        bottomPanel.add(submitButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
