package gui.phs;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

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

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(centerPanel, BorderLayout.CENTER);

        // 제목 라벨 추가
        JLabel titleLabel = new JLabel("민원 부서 변경 요청", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        titleLabel.setPreferredSize(new Dimension(400, 40));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        // 테이블 컬럼
        String[] columns = {"접수 번호", "제목", "처리 상태", "현재 배정부서", "접수 날짜"};

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀 수정 불가능
            }
        };

        table = new JTable(model);
        table.setRowHeight(40);
        table.setFillsViewportHeight(true);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(200, 220, 255));
        table.setSelectionForeground(Color.BLACK);

        // 셀 렌더링 커스터마이즈
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object val, boolean isSelected,
                                                           boolean hasFocus, int row, int col) {
                Component comp = super.getTableCellRendererComponent(tbl, val, isSelected, hasFocus, row, col);

                if (!isSelected) {
                    comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                }
                setHorizontalAlignment(SwingConstants.CENTER);

                // 처리 상태 컬럼에 색상 추가
                if (col == 2) {
                    String status = val.toString();
                    if ("완료".equals(status)) {
                        comp.setForeground(new Color(0, 128, 0)); // 녹색
                    } else if ("처리중".equals(status)) {
                        comp.setForeground(new Color(200, 100, 0)); // 주황색
                    } else {
                        comp.setForeground(Color.BLACK);
                    }
                } else {
                    comp.setForeground(Color.BLACK);
                }

                return comp;
            }
        });

        // 테이블 헤더 스타일
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // 페이지네이션 패널
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(paginationPanel, BorderLayout.SOUTH);

        loadDataAndUpdateTable(currentPage);
    }

    private Object[][] fetchDataFromDB(int page, int pageSize) {
        int totalExampleData = 50;
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalExampleData);

        Object[][] data = new Object[end - start][5];
        for (int i = start; i < end; i++) {
            data[i - start][0] = (i + 1) + "";                    // 접수 번호
            data[i - start][1] = "제목 예시";                     // 제목
            data[i - start][2] = i % 3 == 0 ? "완료" : "처리중";   // 처리 상태
            data[i - start][3] = i % 2 == 0 ? "행정부서" : "마케팅부서"; // 배정부서
            data[i - start][4] = "2025-07-14";                   // 날짜
        }

        totalDataCount = totalExampleData;

        return data;
    }

    private void loadDataAndUpdateTable(int page) {
        model.setRowCount(0); // 기존 데이터 삭제

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

            btn.addActionListener(e -> loadDataAndUpdateTable(Integer.parseInt(btn.getText())));

            paginationPanel.add(btn);
        }

        paginationPanel.revalidate();
        paginationPanel.repaint();
    }
}
