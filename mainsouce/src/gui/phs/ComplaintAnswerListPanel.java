package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.MainFrameState;

public class ComplaintAnswerListPanel extends JPanel {

    private String searchKeyword = null;
    private Integer employeeCode = null;

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

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 제목
        JLabel titleLabel = new JLabel("민원신청내역", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // 중앙 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        // 안내 문구
        JLabel infoLabel = new JLabel("클릭한 민원에 대한 내용 또는 검색결과 / 할당된 민원");
        infoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        centerPanel.add(infoLabel);

        // 테이블
        JTable table = createFilteredTable();
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        centerPanel.add(tableScroll);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JTable createFilteredTable() {
        String[] columnNames = {"접수 번호", "제목", "처리상태", "만료일자", "직원코드"};
        QueryRequest<Sinmungo> request;
        if (employeeCode == null && searchKeyword == null) {
        	request = new QueryRequest<>(
            		"SELECT * FROM sinmungo WHERE status like ?", 
            	    "P",
            	    Sinmungo.class,
            	    MainFrameState.civil
            	);
        	List<Sinmungo> sList = request.getResultList();
        }
        Object[][] allData = {
            {"AA0702-0001", "주민등록 등초본 발급 신청", "처리완료", "2025-07-09", 1001},
            {"DA0702-0001", "여권 재발급 신청", "처리중", "2025-07-22", 1002},
            {"DA0702-0002", "가족관계증명서 발급", "미확인", "2025-07-22", 1001},
            {"DA0702-0003", "등초본 재발급", "처리완료", "2025-07-22", 1003}
        };

        List<Object[]> filtered = new ArrayList<>();
        for (Object[] row : allData) {
            String title = row[1].toString();
            Integer empCode = (Integer) row[4];

            if (searchKeyword != null && !title.contains(searchKeyword)) continue;
            if (employeeCode != null && !empCode.equals(employeeCode)) continue;

            filtered.add(row);
        }

        DefaultTableModel model = new DefaultTableModel(
                filtered.toArray(new Object[0][]), columnNames
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(240, 240, 240));

        // 가운데 정렬
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        return table;
    }
}
