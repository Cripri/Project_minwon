package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.EmployeeMainPanel;
import gui.mainframe.MainFrameState;

import static gui.mainframe.MainFrameState.civil;
import static gui.mainframe.MainFrameState.employee;

public class CivilComplaintDetailPanel extends JPanel {

    public CivilComplaintDetailPanel(Integer pk) {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(220, 220, 220));
        

        Sinmungo sinmungo_info = civil.find(Sinmungo.class,pk);

//        FrameTop topPanel = new FrameTop();
//        add(topPanel, BorderLayout.NORTH);

        // 🔹 중앙 전체 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // 🔸 1. 신청인 정보 패널
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        // 라벨 (위)
        JPanel labelPanel = new JPanel(new GridLayout(1, 3, 10, 5));
        labelPanel.setBackground(new Color(240, 240, 240));
        JLabel lbl1 = new JLabel("접수번호", SwingConstants.LEFT);
        JLabel lbl2 = new JLabel("성명", SwingConstants.LEFT);
        JLabel lbl3 = new JLabel();

        for (JLabel lbl : new JLabel[]{lbl1, lbl2, lbl3}) {
            lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
            lbl.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        }

        labelPanel.add(lbl1);
        labelPanel.add(lbl2);
        labelPanel.add(lbl3);

        // 값 (아래)
        JPanel valuePanel = new JPanel(new GridLayout(1, 3, 10, 5));
        valuePanel.setBackground(new Color(240, 240, 240));
        JLabel val1 = new JLabel(
                sinmungo_info.getSinmungo_code().toString(), SwingConstants.LEFT);
        JLabel val2 = new JLabel(
                sinmungo_info.getMember_name(), SwingConstants.LEFT);
        JLabel val3 = new JLabel();
        for (JLabel val : new JLabel[]{val1, val2, val3}) {
            val.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        }

        valuePanel.add(val1);
        valuePanel.add(val2);
        valuePanel.add(val3);

        infoPanel.add(labelPanel, BorderLayout.NORTH);
        infoPanel.add(valuePanel, BorderLayout.SOUTH);
        centerPanel.add(infoPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // 간격

        // 🔸 2. 제목 영역
        JTextArea titleArea = new JTextArea(sinmungo_info.getSinmungo_title());
        titleArea.setFont(new Font("맑은고딕", Font.BOLD, 16));
        titleArea.setEditable(false);
        titleArea.setBackground(Color.WHITE);
        titleArea.setLineWrap(true);
        titleArea.setWrapStyleWord(true);
        titleArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        centerPanel.add(titleArea);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // 🔸 3. 민원내용 / 답변내용
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(new Color(240, 240, 240));

        JTextArea requestArea = new JTextArea(sinmungo_info.getSinmungo_content());
        requestArea.setFont(new Font("맑은고딕", Font.PLAIN, 14));
        requestArea.setEditable(false);
        requestArea.setBackground(Color.WHITE);
        requestArea.setLineWrap(true);
        requestArea.setWrapStyleWord(true);
        requestArea.setBorder(BorderFactory.createTitledBorder("민원내용"));

        JTextArea answerArea = new JTextArea(sinmungo_info.getEmployees_answer());
        answerArea.setFont(new Font("맑은고딕", Font.PLAIN, 14));
        answerArea.setEditable(true); // 수정 가능
        answerArea.setBackground(Color.WHITE);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setBorder(BorderFactory.createTitledBorder("수정 가능"));

        contentPanel.add(requestArea);
        contentPanel.add(answerArea);

        centerPanel.add(contentPanel);
        add(centerPanel, BorderLayout.CENTER);

        // 🔹 하단 버튼 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(220, 220, 220));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonRow.setBackground(new Color(220, 220, 220));

        JButton confirmButton = new JButton("답변 확정");
        JButton listButton = new JButton("목록으로");
        JButton requestButton = new JButton("부서 변경 요청");
        
        listButton.addActionListener(e -> {
            MainFrameState.card.prev();
        });

        for (JButton btn : new JButton[]{confirmButton, listButton,requestButton}) {
            btn.setBackground(new Color(30, 144, 255));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(90, 30));
            buttonRow.add(btn);
        }

        confirmButton.addActionListener(e ->{
            Sinmungo sin = civil.find(Sinmungo.class,pk);
            sin.setEmployees_answer(answerArea.getText());
            sin.setStatus("C");
            Date bDate = new Date();
            LocalDate bLocalDate = LocalDate.now();
            bDate = Date.from(bLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            sin.setAnswer_date(bDate);
            sin.setEmployee_code(employee.getEmployee_code());

            civil.update(sin);
            EmployeeMainPanel em = new EmployeeMainPanel();
            MainFrameState.card.add("employeeMain", em);
            MainFrameState.employeeMainPanel =em;
            MainFrameState.card.show("employeeMain");
        });

        requestButton.addActionListener(e -> {
            Sinmungo sin = civil.find(Sinmungo.class,pk);
            sin.setStatus("Q");

            civil.update(sin);
            EmployeeMainPanel em = new EmployeeMainPanel();
            MainFrameState.card.add("employeeMain", em);
            MainFrameState.employeeMainPanel =em;
            MainFrameState.card.show("employeeMain");
        });

        bottomPanel.add(buttonRow);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
