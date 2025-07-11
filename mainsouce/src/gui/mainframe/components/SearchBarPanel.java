package gui.mainframe.components;

import javax.swing.*;
import java.awt.*;

public class SearchBarPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final JTextField searchField;

    public SearchBarPanel() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon search = new ImageIcon("./IconImage/돋보기.png");
        Image searchImg = search.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel searchIcon = new JLabel(new ImageIcon(searchImg));
        searchIcon.setPreferredSize(new Dimension(40, 40));

        searchField = new JTextField();
        searchField.setBorder(null);
        searchField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

        add(searchIcon, BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
    }

    public String getSearchText() {
        return searchField.getText();
    }
}