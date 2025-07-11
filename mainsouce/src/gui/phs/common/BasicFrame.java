package gui.phs.common;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.mainframe.FrameTop;

public class BasicFrame {
    public static void setupBasicFrame(JFrame frame, String title) {
        frame.setTitle(title);
        frame.setSize(1600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
    }
}