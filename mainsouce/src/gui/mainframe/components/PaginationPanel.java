package gui.mainframe.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaginationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JButton prevBtn;
    private final JButton nextBtn;
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

        ImageIcon prevIcon = new ImageIcon("./IconImage/이전페이지.png");
        ImageIcon nextIcon = new ImageIcon("./IconImage/다음페이지.png");
        Image prevScaled = prevIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image nextScaled = nextIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        prevBtn = new JButton("이전", new ImageIcon(prevScaled));
        nextBtn = new JButton("다음", new ImageIcon(nextScaled));
        pageLabel = new JLabel();

        prevBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        nextBtn.setHorizontalTextPosition(SwingConstants.LEFT);

        for (JButton btn : new JButton[]{prevBtn, nextBtn}) {
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }

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