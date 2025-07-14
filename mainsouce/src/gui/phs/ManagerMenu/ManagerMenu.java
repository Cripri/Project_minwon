package gui.phs.ManagerMenu;

import gui.mainframe.FrameTop;
import gui.phs.common.BasicFrame;
import gui.phs.common.ImageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.function.Consumer;

public class ManagerMenu extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private Object[][] originalData;

    public ManagerMenu() {
        BasicFrame.setupBasicFrame(this, "계정 관리");

        FrameTop topPanel = new FrameTop();
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(centerPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setOpaque(false);

        JButton filterBtn = new JButton("검색필터");
        filterBtn.setPreferredSize(new Dimension(90, 30));
        styleButton(filterBtn);
        actionPanel.add(filterBtn);

        JButton createBtn = new JButton("계정 생성");
        createBtn.setBackground(new Color(70, 130, 180));
        createBtn.setForeground(Color.WHITE);
        createBtn.setFocusPainted(false);
        createBtn.setPreferredSize(new Dimension(100, 30));
        actionPanel.add(createBtn);

        centerPanel.add(actionPanel, BorderLayout.NORTH);

        String[] columns = {"등록번호", "아이디", "이름", "직급", "담당부서", "민원 부서 변경", "편집", "삭제"};
        originalData = new Object[][]{
                {"1", "aaaa111", "어쩌구", "주무관", "행정부서", "가능", "", ""},
                {"2", "abcd123", "저쩌구", "주임", "마케팅부서", "불가", "", ""},
                {"3", "qwer123", "이러쿵", "사무장", "지원부서", "가능", "", ""},
                {"4", "asdf1234", "저러쿵", "주무관", "행정부서", "불가", "", ""}
        };

        model = new DefaultTableModel(originalData, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 편집, 삭제 버튼 컬럼만 편집 가능하게 설정 (마지막 두 컬럼)
                return column >= columns.length - 2;
            }
        };

        table = new JTable(model);
        table.setRowHeight(40);
        table.setFillsViewportHeight(true);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(200, 220, 255));
        table.setSelectionForeground(Color.BLACK);

        // 홀짝 배경색 처리
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

        // 테이블 헤더 스타일
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // 아이콘 렌더러와 에디터 설정
        setupIconRenderers();

        // 컬럼 너비 설정
        table.getColumnModel().getColumn(0).setPreferredWidth(70);  // 등록번호
        table.getColumnModel().getColumn(1).setPreferredWidth(120); // 아이디
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // 이름
        table.getColumnModel().getColumn(3).setPreferredWidth(80);  // 직급
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // 담당부서
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // 민원 부서 변경
        table.getColumnModel().getColumn(6).setPreferredWidth(50);  // 편집
        table.getColumnModel().getColumn(7).setPreferredWidth(50);  // 삭제

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // 버튼 이벤트 처리
        filterBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "검색 필터 기능은 추후 구현 예정입니다.");
        });

        createBtn.addActionListener(e -> {
            AccountCreateDialog createDialog = new AccountCreateDialog(this, userData -> {
                model.addRow(userData);
            });
            createDialog.setVisible(true);
        });

        setVisible(true);
    }

    private void setupIconRenderers() {
        ImageIcon editIcon = ImageUtil.getScaledIcon("편집이미지.png", 25, 25);
        ImageIcon deleteIcon = ImageUtil.getScaledIcon("휴지통.png", 25, 25);

        table.getColumnModel().getColumn(6).setCellRenderer(new ImageButtonRenderer(editIcon));
        table.getColumnModel().getColumn(7).setCellRenderer(new ImageButtonRenderer(deleteIcon));

        table.getColumnModel().getColumn(6).setCellEditor(new ImageButtonEditor(new JCheckBox(), editIcon, "edit", table));
        table.getColumnModel().getColumn(7).setCellEditor(new ImageButtonEditor(new JCheckBox(), deleteIcon, "delete", table));
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(220, 220, 220));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        btn.setPreferredSize(new Dimension(90, 30));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(200, 200, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(220, 220, 220));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManagerMenu::new);
    }
}
