package gui.phs;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import function.connector.Department;
import function.connector.Employees;
import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.MainFrameState;

public class ComplaintAnswerListPanel extends JPanel {

    private String searchKeyword = null;
    private Integer employeeCode = null;
    private String titleText = "민원신청내역";

    public ComplaintAnswerListPanel() {
        initUI();
    }

    public ComplaintAnswerListPanel(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        initUI();
    }

    public ComplaintAnswerListPanel(int employeeCode) {
        this.employeeCode = employeeCode;
        initUI();
    }

    public ComplaintAnswerListPanel(String searchKeyword, int employeeCode) {
        this.searchKeyword = searchKeyword;
        this.employeeCode = employeeCode;
        initUI();
    }

    public ComplaintAnswerListPanel(String searchKeyword, String titleText) {
        this.searchKeyword = searchKeyword;
        this.titleText = titleText;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(217, 217, 217));
        titlePanel.setBorder(new EmptyBorder(30, 30, 10, 30));

        JLabel title = new JLabel(titleText);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(10, 10, 10, 10)
        ));
        tableWrapper.setBackground(Color.WHITE);

        JTable table = createFilteredTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        tableWrapper.setPreferredSize(new Dimension(1500, 300));
        add(tableWrapper, BorderLayout.CENTER);
    }

    private JTable createFilteredTable() {
        String[] columnNames = {"접수 번호", "제목", "처리기관", "등록일", "답변일"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder sql = new StringBuilder("SELECT * FROM sinmungo WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 처리 상태 필터
        if ("처리중".equals(searchKeyword)) {
            sql.append(" AND status = 'I'");
        } else if ("처리완료".equals(searchKeyword)) {
            sql.append(" AND status = 'C'");
        } else if (searchKeyword == null) {
            sql.append(" AND status = 'P'");
        }

        // 담당자 코드 필터
        if (employeeCode != null) {
            sql.append(" AND employee_code = ?");
            params.add(employeeCode);
        }

        // 제목 검색 필터 (단, 처리 상태 필터는 제외)
        if (searchKeyword != null && !searchKeyword.equals("처리중") && !searchKeyword.equals("처리완료")) {
            sql.append(" AND sinmungo_title LIKE ?");
            params.add("%" + searchKeyword + "%");
        }

        QueryRequest<Sinmungo> request = new QueryRequest<>(
                sql.toString(),
                params,
                Sinmungo.class,
                MainFrameState.civil
        );
        List<Sinmungo> list = request.getResultList();

        Object[][] data = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            Sinmungo s = list.get(i);
            Employees emp = MainFrameState.civil.find(Employees.class, s.getEmployee_code());
            Department dept = (emp != null) ? MainFrameState.civil.find(Department.class, emp.getDepartment_code()) : null;

            data[i][0] = s.getSinmungo_code();
            data[i][1] = s.getSinmungo_title();
            data[i][2] = (dept != null) ? dept.getDepartment_name() : "판별 불가";
            data[i][3] = sdf.format(s.getCreate_date());
            data[i][4] = (s.getAnswer_date() != null) ? sdf.format(s.getAnswer_date()) : "답변 없음";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(240, 240, 240));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    Integer sinmungoCode = Integer.parseInt(table.getValueAt(row, 0).toString());
                    CivilComplaintDetailPanel detailPanel = new CivilComplaintDetailPanel(sinmungoCode);
                    MainFrameState.card.add(detailPanel, "detail_" + sinmungoCode);
                    MainFrameState.card.show("detail_" + sinmungoCode);
                }
            }
        });

        return table;
    }
}
