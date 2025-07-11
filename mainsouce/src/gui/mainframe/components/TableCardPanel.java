package gui.mainframe.components;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;

import gui.mainframe.model.Petition;

public class TableCardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final CardLayout layout;

    public TableCardPanel(List<Petition> data, int itemsPerPage) {
        this.layout = new CardLayout();
        setLayout(layout);
        createPages(data, itemsPerPage);
    }

    private void createPages(List<Petition> data, int itemsPerPage) {
        int totalPages = (int) Math.ceil((double) data.size() / itemsPerPage);
        for (int i = 0; i < totalPages; i++) {
            int start = i * itemsPerPage;
            int end = Math.min(start + itemsPerPage, data.size());
            List<Petition> pageData = data.subList(start, end);
            add(new TableSectionPanel(pageData), "page" + i);
        }
    }

    public void showPage(int pageIndex) {
        layout.show(this, "page" + pageIndex);
    }

    public int getTotalPages(int dataSize, int itemsPerPage) {
        return (int) Math.ceil((double) dataSize / itemsPerPage);
    }
}