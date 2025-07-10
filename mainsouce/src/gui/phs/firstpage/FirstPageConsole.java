package gui.phs.firstpage;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.phs.simpleDocPanel.CardLayoutPanel;
import gui.phs.simpleDocPanel.FrameTop;

public class FirstPageConsole extends JFrame {

    private static final long serialVersionUID = 1L;

    public FirstPageConsole() {
        setTitle("Custom UI");
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        FrameTop top = new FrameTop();
        add(top, BorderLayout.NORTH);

        CardLayoutPanel cardPanel = new CardLayoutPanel();
        add(cardPanel, BorderLayout.CENTER);

        FirstPage customPanel = new FirstPage();
        cardPanel.add(customPanel, "custom");


        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "custom");

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FirstPageConsole::new);
    }
}