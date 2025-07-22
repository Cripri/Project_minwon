package gui.phs;

import static gui.mainframe.MainFrameState.civil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import function.connector.Department;
import function.connector.Employees;
import function.connector.Sinmungo;
import gui.mainframe.MainFrameState;

public class DepartmentChangeRequestPanel extends JPanel {

    private static DepartmentChangeRequestPanel instance;

    private DefaultTableModel model;
    private JTable table;
    private JPanel paginationPanel;

    private int currentPage = 1;
    private final int rowsPerPage = 10;
    private int totalDataCount = 0;

    public DepartmentChangeRequestPanel() {
        instance = this;

        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(centerPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("민원 부서변경 요청 목록", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columns = {"접수 번호", "내용", "처리 상태", "현재 배정부서", "접수 날짜"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(35);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false); // 열 이동 방지

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object val, boolean isSelected,
                                                           boolean hasFocus, int row, int col) {
                Component comp = super.getTableCellRendererComponent(tbl, val, isSelected, hasFocus, row, col);
                comp.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
                if (!isSelected) {
                    comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240));
                }
                setHorizontalAlignment(SwingConstants.CENTER);
                return comp;
            }
        });

        JTableHeader header = table.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD, 13f));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(paginationPanel, BorderLayout.SOUTH);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();

                if (column == 1) {
                    try {
                        int code = (int) table.getValueAt(row, 0);
                        String cardName = "DepartmentChangeDetail_" + code;

                        if (!MainFrameState.card.contains(cardName)) {
                            DepartmentChangeRequestDetailPanel detailPanel = new DepartmentChangeRequestDetailPanel(code);
                            MainFrameState.card.add(cardName, detailPanel);
                        }
                        MainFrameState.card.show(cardName);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        loadDataAndUpdateTable(currentPage);
    }

    public static void refresh() {
        if (instance != null) {
            instance.loadDataAndUpdateTable(instance.currentPage);
        }
    }

    private List<Sinmungo> fetchPendingRequestsFromDB() {
        List<Sinmungo> all = civil.selectAll(Sinmungo.class);
        List<Sinmungo> pending = new ArrayList<>();
        for (Sinmungo s : all) {
            String status = s.getStatus();
            if ("Q".equals(status)) {
                pending.add(s);
            }
        }
        return pending;
    }

    private void loadDataAndUpdateTable(int page) {
        List<Sinmungo> dataList = fetchPendingRequestsFromDB();

        int start = (page - 1) * rowsPerPage;
        int end = Math.min(start + rowsPerPage, dataList.size());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        model.setRowCount(0);
        for (int i = start; i < end; i++) {
            Sinmungo s = dataList.get(i);
            model.addRow(new Object[]{
                s.getSinmungo_code(),
                s.getSinmungo_title(),
                s.getStatus().equals("Q") ? "처리중" : "완료",
                getDepartmentName(s.getEmployee_code()),
                s.getCreate_date() != null ? formatter.format(s.getCreate_date()) : ""
            });
        }

        totalDataCount = dataList.size();
        currentPage = page;
        updatePaginationButtons();
    }

    private void updatePaginationButtons() {
        paginationPanel.removeAll();
        int totalPage = (int) Math.ceil((double) totalDataCount / rowsPerPage);

        for (int i = 1; i <= totalPage; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setPreferredSize(new Dimension(50, 40));

            if (i == currentPage) {
                btn.setEnabled(false);
                btn.setBackground(new Color(70, 130, 180));
                btn.setForeground(Color.WHITE);
            }

            final int pageNum = i;
            btn.addActionListener(e -> loadDataAndUpdateTable(pageNum));

            paginationPanel.add(btn);
        }

        paginationPanel.revalidate();
        paginationPanel.repaint();
    }

    private String getDepartmentName(Integer empCode) {
        if (empCode == null) return "-";
        Employees emp = civil.find(Employees.class, empCode);
        if (emp == null) return "-";
        Department dept = civil.find(Department.class, emp.getDepartment_code());
        return dept != null ? dept.getDepartment_name() : "-";
    }
}
