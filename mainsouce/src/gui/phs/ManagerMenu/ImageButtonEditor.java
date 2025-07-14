package gui.phs.ManagerMenu;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ImageButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String type;  // "edit" or "delete"
    private JTable table;
    private ImageIcon icon;

    public ImageButtonEditor(JCheckBox checkBox, ImageIcon icon, String type, JTable table) {
        super(checkBox);
        this.icon = icon;
        this.type = type;
        this.table = table;
        button = new JButton(icon);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        button.setIcon(icon);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        int row = table.getSelectedRow();

        if ("delete".equals(type)) {
            String userId = (String) table.getValueAt(row, 1);

            JPanel panel = new JPanel(new GridLayout(3, 1));
            panel.add(new JLabel("계정 " + userId + " 을(를) 삭제하시겠습니까?"));
            panel.add(new JLabel("비밀번호"));

            JPasswordField passwordField = new JPasswordField(10);
            panel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(
                    table,
                    panel,
                    "계정 삭제 확인",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                String inputPassword = new String(passwordField.getPassword());
                if (inputPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(table, "비밀번호를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    return "";
                }
                // TODO: 실제 비밀번호 검증 로직 구현

                ((DefaultTableModel) table.getModel()).removeRow(row);
                JOptionPane.showMessageDialog(table, "계정이 삭제되었습니다.");
            }
        } else if ("edit".equals(type)) {
            int rowIndex = table.getSelectedRow();
            String userId = (String) table.getValueAt(rowIndex, 1);

            AccountModifyDialog modifyDialog = new AccountModifyDialog(
                    SwingUtilities.getWindowAncestor(table), userId, updatedData -> {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                for (int col = 0; col < updatedData.length; col++) {
                    model.setValueAt(updatedData[col], rowIndex, col);
                }
            });
            modifyDialog.setVisible(true);
        }
        return "";
    }
}
