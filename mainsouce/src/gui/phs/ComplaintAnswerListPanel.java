package gui.phs;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ComplaintAnswerListPanel extends JPanel {

    public ComplaintAnswerListPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 제목 라벨: "민원신청내역" 가운데 정렬
        JLabel titleLabel = new JLabel("민원신청내역", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        this.add(titleLabel, BorderLayout.NORTH);

        // 메인 콘텐츠 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        // 안내 텍스트
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel infoLabel = new JLabel("클릭한 민원에 대한 내용 (ex 합당된 민원, 결제확인증, 처리완료된민원, 혹은 전체에서 검색된내용)");
        infoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoPanel.add(infoLabel, BorderLayout.WEST);

        centerPanel.add(infoPanel);

        // 테이블
        JTable table = createStyledTable();
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        centerPanel.add(tableScroll);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 상세 정보 패널 (접수번호, 성명 등)
        JPanel detailInfoPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        detailInfoPanel.setBackground(new Color(240, 240, 240));
        detailInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lblReceipt = new JLabel("접수번호: AA1234-215466");
        lblReceipt.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        JLabel lblName = new JLabel("성명: 어쩌구씨");
        lblName.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        detailInfoPanel.add(lblReceipt);
        detailInfoPanel.add(lblName);

        centerPanel.add(detailInfoPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // 제목 영역
        JTextArea titleArea = new JTextArea("제목");
        titleArea.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        titleArea.setEditable(false);
        titleArea.setBackground(Color.WHITE);
        titleArea.setLineWrap(true);
        titleArea.setWrapStyleWord(true);
        titleArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        centerPanel.add(titleArea);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // 민원내용 / 답변내용 패널
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(new Color(240, 240, 240));

        JTextArea requestArea = new JTextArea("민원내용");
        requestArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        requestArea.setEditable(false);
        requestArea.setBackground(Color.WHITE);
        requestArea.setLineWrap(true);
        requestArea.setWrapStyleWord(true);
        requestArea.setBorder(BorderFactory.createTitledBorder("민원내용"));

        JTextArea answerArea = new JTextArea("답변내용");
        answerArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        answerArea.setEditable(true); // 수정 가능
        answerArea.setBackground(Color.WHITE);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setBorder(BorderFactory.createTitledBorder("답변내용 (수정 가능)"));

        contentPanel.add(requestArea);
        contentPanel.add(answerArea);

        centerPanel.add(contentPanel);

        this.add(centerPanel, BorderLayout.CENTER);

        // 하단 버튼 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(220, 220, 220));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonRow.setBackground(new Color(220, 220, 220));

        JButton confirmButton = new JButton("답변 확정");
        JButton listButton = new JButton("목록으로");

        for (JButton btn : new JButton[]{confirmButton, listButton}) {
            btn.setBackground(new Color(30, 144, 255));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(90, 30));
            buttonRow.add(btn);
        }

        bottomPanel.add(buttonRow);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

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
