package gui.phs;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ComplaintAnswerListPanel extends JPanel {

    public ComplaintAnswerListPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 🔹 제목 라벨: "민원신청내역" (가운데 정렬)
        JLabel titleLabel = new JLabel("민원신청내역", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        this.add(titleLabel, BorderLayout.NORTH);  // 👉 여기서 바로 가운데 정렬됨

        // 아래 contentPanel은 그대로 유지
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));  // 제목 아래 간격

        // ▶ 안내 텍스트 + 뒤로가기 버튼
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(Color.WHITE);

        JLabel infoLabel = new JLabel("클릭한 민원에 대한 내용 (ex 합당된 민원, 결제확인증, 처리완료된민원, 혹은 전체에서 검색된내용)");
        infoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));

        infoPanel.add(infoLabel);
        contentPanel.add(infoPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ▶ 테이블 영역
        JTable table = createStyledTable();
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        contentPanel.add(tableScroll);

        this.add(contentPanel, BorderLayout.CENTER);
    }

    // ✔ 스타일 적용된 테이블 생성
    private JTable createStyledTable() {
        String[] columnNames = {"접수 번호", "내용", "처리상태", "만료일자", "추가신청"};
        Object[][] data = {
            {"AA0702-0001", "주민등록 등초본 발급 신청", "처리완료", "2025-07-09", ""},
            {"DA0702-0001", "여권 재발급 신청", "처리중", "2025-07-22", ""},
            {"DA0702-0002", "여권 재발급 신청", "미확인", "2025-07-22", ""},
            {"DA0702-0002", "여권 재발급 신청", "반려", "2025-07-22", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setFillsViewportHeight(true);

        // 헤더 스타일
        table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.getTableHeader().setReorderingAllowed(false);

        // 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        return table;
    }
}
