package gui.mainframe.components;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;

import gui.mainframe.model.Petition;

public class TableCardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final CardLayout layout;
	private List<Petition> petitions;
	private int itemsPerPage;

    public TableCardPanel(List<Petition> data, int itemsPerPage) {
        this.layout = new CardLayout();
        this.petitions = data;
		this.itemsPerPage = itemsPerPage;
        setLayout(layout);
        createPages(petitions);
    }

    private void createPages(List<Petition> data) {
		removeAll();
		int totalPages = (int) Math.ceil((double) data.size() / itemsPerPage);
		for (int i = 0; i < totalPages; i++) {
			int start = i * itemsPerPage;
			int end = Math.min(start + itemsPerPage, data.size());
			List<Petition> pageData = data.subList(start, end);
			add(new TableSectionPanel(pageData), "page" + i);
		}
		revalidate();
		repaint();
	}

    public void showPage(int pageIndex) {
        layout.show(this, "page" + pageIndex);
    }

    public void updatePetitions(List<Petition> newPetitions) {
		this.petitions = newPetitions;
		createPages(petitions);
		showPage(0);
	}
}