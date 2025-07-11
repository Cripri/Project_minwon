package gui.mainframe.components;

import javax.swing.*;
import java.awt.*;

public class PaginationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private final JLabel pageLabel;
    private int currentPage = 0;
    private final int totalPages;
    private final PageChangeListener listener;

    public interface PageChangeListener {
        void onPageChanged(int newPage);
    }

    public PaginationPanel(int totalPages, PageChangeListener listener) {
        this.totalPages = totalPages;
        this.listener = listener;
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        IconButton prevBtn = new IconButton("이전 페이지", "./IconImage/이전페이지.png");
        IconButton nextBtn = new IconButton("다음 페이지", "./IconImage/다음페이지.png");
        pageLabel = new JLabel();

        prevBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        nextBtn.setHorizontalTextPosition(SwingConstants.LEFT);

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

        add(pageLabel);
        add(prevBtn);
        add(nextBtn);
        updateLabel();
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
} 