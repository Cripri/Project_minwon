package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import function.connector.Department;
import function.connector.Employees;
import function.connector.Position;
import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.components.SearchBarPanel;
import gui.phs.ComplaintAnswerListPanel;

public class EmployeeMainPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public static class Item {
        private final String title;
        private final String count;
        private final String type;

        public Item(String title, String count, String type) {
            this.title = title;
            this.count = count;
            this.type = type;
        }

        public String getTitle() { return title; }
        public String getCount() { return count; }
        public String getType() { return type; }
    }

    public EmployeeMainPanel() {}

    public void refreshPanel() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(217, 217, 217));
        this.setBorder(new EmptyBorder(40, 40, 40, 40));

        int employeeCode = MainFrameState.employee.getEmployee_code();

        List<Sinmungo> 접수중리스트 = fetchComplaintsByStatus("P");
        List<Sinmungo> 할당된민원 = fetchComplaintsByStatusAndEmployee("P", employeeCode);
        List<Sinmungo> 처리중민원 = fetchComplaintsByStatus("I");
        List<Sinmungo> 부서변경요청 = fetchComplaintsByStatus("X");
        List<Sinmungo> 처리완료 = fetchComplaintsByStatus("C");
        // TODO 처리불가는 어떻게 처리?
        List<Sinmungo> 처리불가 = new ArrayList<>();

        Employees e = MainFrameState.employee;
        if (e != null) {
            Position p = MainFrameState.civil.find(Position.class, e.getPosition_code());
            Department d = MainFrameState.civil.find(Department.class, e.getDepartment_code());

            JPanel employeeTopPanel = new JPanel(new BorderLayout());
            employeeTopPanel.setBackground(new Color(217, 217, 217));
            JPanel searchBarPanel = new JPanel(new GridLayout(0, 1));
            searchBarPanel.setBackground(new Color(217, 217, 217));
            JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
            userInfoPanel.setBackground(new Color(217, 217, 217));
            userInfoPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
            
            SearchBarPanel searchBar = new SearchBarPanel();
            searchBarPanel.add(searchBar);
            searchBar.addSearchListener(event -> {
                String query = searchBar.getSearchText();
                ComplaintAnswerListPanel searchedPanel = new ComplaintAnswerListPanel(query);
                MainFrameState.card.add(query + "_searchedPanel", searchedPanel);
                MainFrameState.card.show(query + "_searchedPanel");
            });

            employeeTopPanel.add(searchBarPanel, BorderLayout.CENTER);

            JLabel rankTitleLabel = new JLabel("직급");
            JLabel rankValueLabel = new JLabel(p.getPosition_name());
            JLabel nameTitleLabel = new JLabel("이름");
            JLabel nameValueLabel = new JLabel(e.getEmployee_name());
            JLabel deptTitleLabel = new JLabel("부서");
            JLabel deptValueLabel = new JLabel(d.getDepartment_name());

            Font infoFont = new Font("맑은 고딕", Font.BOLD, 14);
            for (JLabel label : List.of(rankTitleLabel, rankValueLabel, nameTitleLabel, nameValueLabel, deptTitleLabel, deptValueLabel)) {
                label.setFont(infoFont);
            }

            userInfoPanel.add(rankTitleLabel);
            userInfoPanel.add(rankValueLabel);
            userInfoPanel.add(nameTitleLabel);
            userInfoPanel.add(nameValueLabel);
            userInfoPanel.add(deptTitleLabel);
            userInfoPanel.add(deptValueLabel);

            employeeTopPanel.add(userInfoPanel, BorderLayout.WEST);
            add(employeeTopPanel, BorderLayout.NORTH);
        }

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));

        JPanel labelPanel = new JPanel(new GridLayout(0, 3, 20, 0));
        labelPanel.setBackground(new Color(217, 217, 217));

        JLabel label1 = new JLabel("일반주무관 사용", SwingConstants.CENTER);
        JLabel label2 = new JLabel("팀장급 사용", SwingConstants.CENTER);
        JLabel label3 = new JLabel("처리완료된 민원", SwingConstants.CENTER);

        Font labelFont = new Font("맑은 고딕", Font.BOLD, 15);
        for (JLabel label : List.of(label1, label2, label3)) {
            label.setFont(labelFont);
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }

        labelPanel.add(label1);
        labelPanel.add(label2);
        labelPanel.add(label3);
        centerPanel.add(labelPanel);

        JPanel whiteWrapper = new JPanel(new GridLayout(0, 3, 20, 0));
        whiteWrapper.setBackground(Color.WHITE);
        whiteWrapper.setBorder(new EmptyBorder(10, 30, 30, 30));

        whiteWrapper.add(createSection(List.of(
            new Item("대기중인 민원", String.valueOf(접수중리스트.size()), "waiting"),
            new Item("할당된 민원", String.valueOf(할당된민원.size()), "assigned"),
            new Item("처리중 민원", String.valueOf(처리중민원.size()), "processing"),
            new Item("부서 변경 요청", String.valueOf(부서변경요청.size()), "reassign")
        )));

        whiteWrapper.add(createSection(List.of(
            new Item("확인 대기중(결재) 민원", null, "approval_wait"),
            new Item("결재대기중", "3건", "approval_pending"),
            new Item("결재중", "3건", "approving"),
            new Item("요청 승인 대기", "3건", "approval_requested")
        )));

        whiteWrapper.add(createSection(List.of(
            new Item("처리완료", String.valueOf(처리완료.size()), "done"),
            new Item("처리불가", "1건", "rejected")
        )));

        centerPanel.add(whiteWrapper);
        add(centerPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    private JPanel createSection(List<Item> items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(217, 217, 217));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            new EmptyBorder(10, 15, 10, 15)));

        for (Item item : items) {
            JPanel row = new JPanel(new BorderLayout());
            row.setOpaque(false);

            JLabel label = new JLabel(item.getTitle());
            if (item.getCount() == null) {
                label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        openComplaintPanel(item);
                    }
                });
            } else {
                label.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
            }
            row.add(label, BorderLayout.WEST);

            if (item.getCount() != null) {
                JButton countBtn = new JButton(item.getCount());
                countBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
                countBtn.setContentAreaFilled(false);
                countBtn.setBorderPainted(false);
                countBtn.setFocusPainted(false);
                countBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                countBtn.setOpaque(false);

                countBtn.addActionListener(e -> openComplaintPanel(item));
                row.add(countBtn, BorderLayout.EAST);
            }

            panel.add(row);
            panel.add(Box.createVerticalStrut(5));
        }

        return panel;
    }

    private void openComplaintPanel(Item item) {
        ComplaintAnswerListPanel panel;
        String panelName;

        switch (item.getType()) {
            case "waiting" -> {
                panel = new ComplaintAnswerListPanel();
                panelName = "waitingPanel";
            }
            case "assigned" -> {
                int empCode = MainFrameState.employee.getEmployee_code();
                panel = new ComplaintAnswerListPanel(empCode);
                panelName = "assignedPanel_" + empCode;
            }
            case "processing" -> {
                int empCode = MainFrameState.employee.getEmployee_code();
                panel = new ComplaintAnswerListPanel("처리중", empCode);
                panelName = "processingPanel_" + empCode;
            }
            default -> {
                panel = new ComplaintAnswerListPanel(); // fallback
                panelName = "defaultPanel";
            }
        }

        MainFrameState.card.add(panelName, panel);
        MainFrameState.card.show(panelName);
    }
    
    private List<Sinmungo> fetchComplaintsByStatus(String status) {
      QueryRequest<Sinmungo> request = new QueryRequest<>(
          "SELECT * FROM sinmungo WHERE status LIKE ?",
          status,
          Sinmungo.class,
          MainFrameState.civil
      );
      return request.getResultList();
  }

  private List<Sinmungo> fetchComplaintsByStatusAndEmployee(String status, int employeeCode) {
      QueryRequest<Sinmungo> request = new QueryRequest<>(
          "SELECT * FROM sinmungo WHERE status LIKE ? AND employee_code = ?",
          Arrays.asList(status, employeeCode),
          Sinmungo.class,
          MainFrameState.civil
      );
      return request.getResultList();
  }
}
