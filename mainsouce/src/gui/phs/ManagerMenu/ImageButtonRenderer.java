package gui.phs.ManagerMenu;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ImageButtonRenderer extends JButton implements TableCellRenderer {

    public ImageButtonRenderer(ImageIcon icon) {
        setIcon(icon);
        setOpaque(true);
        setBorderPainted(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        return this;
    }
}
