package gui.phs;

import static gui.mainframe.MainFrameState.civil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import function.connector.Department;
import function.connector.Employees;
import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.MainFrameState;
import gui.popup.wldb.pop_up_material.Get_pop_up_frames;

public class DepartmentChangeRequestDetailPanel extends JPanel {

    private final Font defaultFont = new Font("\uB9D1\uC740\uACE0\uB515", Font.PLAIN, 14);
    private final Font boldFont = new Font("\uB9D1\uC740\uACE0\uB515", Font.BOLD, 14);
    private final Font titleFont = new Font("\uB9D1\uC740\uACE0\uB515", Font.BOLD, 20);

    private static Sinmungo sinmungo_info;
    private static ArrayList<Employees> emp_list;

    public DepartmentChangeRequestDetailPanel() {
		// static 이랑 이거 지우면 오류남, final 함부러 넣어도 오류남
	}
    
    public DepartmentChangeRequestDetailPanel(Integer pk) {
        this.sinmungo_info = civil.find(Sinmungo.class, pk);
        setName("DepartmentChangeDetail_" + pk);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(200, 200, 200));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(220, 220, 220));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel headerLabel = new JLabel("부서 변경 요청 내역", SwingConstants.CENTER);
        headerLabel.setFont(titleFont);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        centerPanel.add(headerLabel);

        JPanel infoPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        infoPanel.setBackground(new Color(220, 220, 220));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        infoPanel.add(wrapLeftAlignedLabel("접수번호"));
        infoPanel.add(wrapLeftAlignedLabel("성명"));
        infoPanel.add(new JLabel());

        infoPanel.add(wrapLeftAlignedLabel(sinmungo_info.getSinmungo_code().toString()));
        infoPanel.add(wrapLeftAlignedLabel(sinmungo_info.getMember_name()));
        infoPanel.add(new JLabel());

        centerPanel.add(infoPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JTextArea titleArea = new JTextArea(sinmungo_info.getSinmungo_title());
        titleArea.setFont(boldFont);
        titleArea.setEditable(false);
        titleArea.setBackground(Color.WHITE);
        titleArea.setLineWrap(true);
        titleArea.setWrapStyleWord(true);
        titleArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        centerPanel.add(titleArea);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JTextArea contentArea = new JTextArea(sinmungo_info.getSinmungo_content());
        contentArea.setFont(defaultFont);
        contentArea.setEditable(false);
        contentArea.setBackground(Color.WHITE);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        centerPanel.add(contentArea);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(220, 220, 220));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonRow.setBackground(new Color(220, 220, 220));

        JButton rejectButton = new JButton("반려");
        rejectButton.setBackground(new Color(178, 34, 34));
        rejectButton.setForeground(Color.WHITE);
        rejectButton.setFocusPainted(false);
        rejectButton.setPreferredSize(new Dimension(70, 30));
        rejectButton.setFont(defaultFont);

        JButton changeDeptButton = new JButton("부서변경");
        changeDeptButton.setBackground(new Color(30, 144, 255));
        changeDeptButton.setForeground(Color.WHITE);
        changeDeptButton.setFocusPainted(false);
        changeDeptButton.setPreferredSize(new Dimension(90, 30));
        changeDeptButton.setFont(defaultFont);

        ArrayList<Department> departments = new ArrayList<>(civil.selectAll(Department.class));
        String[] d_names = new String[departments.size()];
        Integer[] d_cord = new Integer[departments.size()];

        for (int i = 0; i < departments.size(); i++) {
            d_names[i] = departments.get(i).getDepartment_name();
            d_cord[i] = departments.get(i).getDepartment_code();
        }

        JComboBox<String> deptComboBox = new JComboBox<>(d_names);
        deptComboBox.setPreferredSize(new Dimension(120, 30));
        deptComboBox.setFont(defaultFont);

        changeDeptButton.addActionListener(e -> {
            int select = deptComboBox.getSelectedIndex();

            if (select != -1) {
                QueryRequest<Employees> request = new QueryRequest<>(
                        "SELECT * FROM Employees WHERE department_code = ?",
                        d_cord[select],
                        Employees.class,
                        MainFrameState.civil
                );

                emp_list = new ArrayList<>(request.getResultList());
                Collections.shuffle(emp_list);

                Get_pop_up_frames.get_yn_frame(
                        "정말로 " + deptComboBox.getSelectedItem() + "(으)로 변경하시겠습니까?",
                        this
                );
            }
        });

        JButton listButton = new JButton("목록으로");
        listButton.setBackground(new Color(30, 144, 255));
        listButton.setForeground(Color.WHITE);
        listButton.setFocusPainted(false);
        listButton.setPreferredSize(new Dimension(90, 30));
        listButton.setFont(defaultFont);

        listButton.addActionListener(e -> MainFrameState.card.show("DepartmentChangeRequestPanel"));

        buttonRow.add(rejectButton);
        buttonRow.add(changeDeptButton);
        buttonRow.add(deptComboBox);
        buttonRow.add(listButton);
        bottomPanel.add(buttonRow);

        JPanel explanationPanel = new JPanel(new GridLayout(2, 1));
        explanationPanel.setBackground(new Color(220, 220, 220));
        explanationPanel.add(centeredLabel("부서 선택 후 '부서 변경' 클릭 시 적용됩니다."));
        explanationPanel.add(centeredLabel("팀장급부터만 변경 가능합니다."));
        bottomPanel.add(explanationPanel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel wrapLeftAlignedLabel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(new Color(220, 220, 220));
        JLabel label = new JLabel(text);
        label.setFont(defaultFont);
        panel.add(label);
        return panel;
    }

    private JLabel centeredLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(defaultFont);
        return label;
    }

    public void startUpdate() {
        sinmungo_info.setStatus("P");
        sinmungo_info.setEmployee_code(emp_list.get(0).getEmployee_code());
        civil.update(sinmungo_info);
    }
}