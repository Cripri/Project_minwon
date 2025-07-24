package gui.mainframe.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaginationPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private int currentPage = 0;
	private int totalPages;
	private final PageChangeListener listener;
	private final List<JButton> pageButtons = new ArrayList<>();
	private final IconButton prevBtn = new IconButton("이전", "resources/IconImage/이전페이지.png");
	private final IconButton nextBtn = new IconButton("다음", "resources/IconImage/다음페이지.png");
	private final JLabel pageLabel = new JLabel();

	public interface PageChangeListener {
		void onPageChanged(int newPage);
	}

	public PaginationPanel(int totalPages, PageChangeListener listener) {
		this.totalPages = totalPages;
		this.listener = listener;
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setOpaque(false);
		initNavigationButtons();
		renderButtons();
		updateLabel();
	}

	private void initNavigationButtons() {
		prevBtn.addActionListener(e -> {
			if (currentPage > 0) {
				currentPage--;
				updateLabel();
				listener.onPageChanged(currentPage);
			}
		});
		nextBtn.addActionListener(e -> {
			if (currentPage < totalPages - 1) {
				currentPage++;
				updateLabel();
				listener.onPageChanged(currentPage);
			}
		});
	}

	private void updateLabel() {
		pageLabel.setText((currentPage + 1) + " / " + totalPages);
	}

	public void setCurrentPage(int page) {
		this.currentPage = page;
		updateLabel();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void updatePages(int newTotalPages) {
		this.totalPages = newTotalPages;
		this.currentPage = 0;
		renderButtons();
		updateLabel();
	}

	private void renderButtons() {
		removeAll();
		pageButtons.clear();

		add(prevBtn);
		add(pageLabel);

		nextBtn.setHorizontalTextPosition(SwingConstants.LEFT);
		add(nextBtn);
		revalidate();
		repaint();
	}
}