package gui.phs;

import gui.mainframe.FrameTop;
import gui.phs.common.BasicFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentChangeRequestPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private JPanel paginationPanel;

    private int currentPage = 1;
    private final int rowsPerPage = 10;
    private int totalDataCount = 0;

    public DepartmentChangeRequestPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // 상단 패널 (예: FrameTop)
        FrameTop topPanel = new FrameTop();
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(centerPanel, BorderLayout.CENTER);

        // 테이블 컬럼
        String[] columns = {"접수 번호", "내용", "처리 상태", "현재 배정부서", "접수 날짜"};

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 2 || column == 3;
            }
        };

        table = new JTable(model);
        table.setRowHeight(40);
        table.setFillsViewportHeight(true);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(200, 220, 255));
        table.setSelectionForeground(Color.BLACK);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object val, boolean isSelected,
                                                           boolean hasFocus, int row, int col) {
                Component comp = super.getTableCellRendererComponent(tbl, val, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                }
                setHorizontalAlignment(SwingConstants.CENTER);
                return comp;
            }
        });

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        setComboBoxEditors();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(paginationPanel, BorderLayout.SOUTH);

        loadDataAndUpdateTable(currentPage);
    }

    private void setComboBoxEditors() {
        String[] contents = {"민원내용1", "민원내용2", "민원내용3"};
        String[] statuses = {"처리중", "완료", "대기"};
        String[] departments = {"행정부서", "마케팅부서", "지원부서"};

        JComboBox<String> contentCombo = new JComboBox<>(contents);
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(contentCombo));

        JComboBox<String> statusCombo = new JComboBox<>(statuses);
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(statusCombo));

        JComboBox<String> departmentCombo = new JComboBox<>(departments);
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(departmentCombo));
    }

    private Object[][] fetchDataFromDB(int page, int pageSize) {
        int totalExampleData = 50;
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalExampleData);

        Object[][] data = new Object[end - start][5];
        for (int i = start; i < end; i++) {
            data[i - start][0] = (i + 1) + "";  // 접수 번호
            data[i - start][1] = "내용 " + (i + 1);
            data[i - start][2] = i % 3 == 0 ? "완료" : "처리중";
            data[i - start][3] = i % 2 == 0 ? "행정부서" : "마케팅부서";
            data[i - start][4] = "2025-07-14";
        }

        totalDataCount = totalExampleData;

        return data;
    }

    private void loadDataAndUpdateTable(int page) {
        model.setRowCount(0);

        Object[][] pageData = fetchDataFromDB(page, rowsPerPage);

        for (Object[] row : pageData) {
            model.addRow(row);
        }

        currentPage = page;

        updatePaginationButtons();
    }

    private void updatePaginationButtons() {
        paginationPanel.removeAll();

        int totalPage = (int) Math.ceil((double) totalDataCount / rowsPerPage);

        for (int i = 1; i <= totalPage; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setPreferredSize(new Dimension(40, 30));
            if (i == currentPage) {
                btn.setEnabled(false);
                btn.setBackground(new Color(70, 130, 180));
                btn.setForeground(Color.WHITE);
            }

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadDataAndUpdateTable(Integer.parseInt(btn.getText()));
                }
            });

            paginationPanel.add(btn);
        }

        paginationPanel.revalidate();
        paginationPanel.repaint();
    }
}
