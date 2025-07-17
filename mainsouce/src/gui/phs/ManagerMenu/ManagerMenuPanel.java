package gui.phs.ManagerMenu;

import gui.mainframe.FrameTop;
import gui.phs.common.ImageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ManagerMenuPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private Object[][] originalData;

    public ManagerMenuPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

//        FrameTop topPanel = new FrameTop();
//        add(topPanel, BorderLayout.NORTH);

        JLabel pageTitle = new JLabel("직원 계정 관리", SwingConstants.CENTER);
        pageTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        pageTitle.setOpaque(true); 
        pageTitle.setBackground(new Color(245, 245, 245)); 
        pageTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(pageTitle, BorderLayout.NORTH);
        
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        centerPanel.setBackground(new Color(245, 245, 245));
        add(centerPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setOpaque(false);


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
                return column >= columns.length - 2; // 편집, 삭제 컬럼만 편집 가능
            }
        };

        table = new JTable(model);
        table.setRowHeight(40);
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

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        setupIconRenderers();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        createBtn.addActionListener(e -> {
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            AccountCreateDialog dialog = new AccountCreateDialog(parentWindow, userData -> model.addRow(userData));
            dialog.setVisible(true);
        });
        
        //완료 버튼
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        bottomPanel.setBackground(new Color(245, 245, 245));

        JButton completeBtn = new JButton("완료");
        completeBtn.setPreferredSize(new Dimension(100, 35));
        completeBtn.setBackground(new Color(45, 140, 240)); // 파란색
        completeBtn.setForeground(Color.WHITE);
        completeBtn.setFocusPainted(false);
        completeBtn.setFont(new Font("맑은 고딕", Font.BOLD, 13));

        // 버튼 클릭 이벤트 (예: 콘솔 출력)
        completeBtn.addActionListener(e -> {
            JLabel messageLabel = new JLabel("완료", SwingConstants.CENTER);
            messageLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

            JButton okButton = new JButton("확인");
            okButton.setPreferredSize(new Dimension(80, 30));

            JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(completeBtn), "알림", Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLayout(new BorderLayout(10, 10));
            dialog.add(messageLabel, BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(okButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.pack();
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(null);  // 화면 가운데에 띄우기

            okButton.addActionListener(ev -> dialog.dispose());

            dialog.setVisible(true);
        });
        
        bottomPanel.add(completeBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupIconRenderers() {
        ImageIcon editIcon = ImageUtil.getScaledIcon("resources/IconImage/편집이미지.png", 25, 25);
        ImageIcon deleteIcon = ImageUtil.getScaledIcon("resources/IconImage/휴지통.png", 25, 25);

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
}
