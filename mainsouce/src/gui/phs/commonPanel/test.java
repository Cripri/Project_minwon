package gui.phs.commonPanel;

import gui.phs.commonPanel.TopPanel;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    public test() {
        setTitle("TopPanel test");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        setLayout(new BorderLayout());

        TopPanel topPanel = new TopPanel();
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(new JLabel("가운데욤"));
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(test::new);
    }
}