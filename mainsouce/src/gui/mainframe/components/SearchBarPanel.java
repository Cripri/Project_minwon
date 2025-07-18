package gui.mainframe.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBarPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final JTextField searchField;
	private final RoundedButton searchButton = new RoundedButton("검색");
	

    public SearchBarPanel() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        ImageIcon search = new ImageIcon("resources/IconImage/돋보기.png");
        Image searchImg = search.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel searchIcon = new JLabel(new ImageIcon(searchImg));
        searchIcon.setPreferredSize(new Dimension(40, 40));

        searchField = new JTextField();
        searchField.setBorder(null);
        searchField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

        add(searchIcon, BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }
    
    public void addSearchListener(ActionListener listener) {
        searchField.addActionListener(listener);
        searchButton.addActionListener(listener);
    }

    public String getSearchText() {
        return searchField.getText();
    }
}