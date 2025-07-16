package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.mainframe.FrameTop;

public class WriteContent extends JPanel {

    public WriteContent() {
        setLayout(new BorderLayout());

        // 중앙 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // 제목 패널
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("제목");
        JTextField titleField = new JTextField(30);
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        // 내용 패널
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        JLabel contentLabel = new JLabel("내용");
        JTextArea contentArea = new JTextArea(10, 40);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        contentPanel.add(contentLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // 구성 추가
        centerPanel.add(titlePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(contentPanel);

        add(centerPanel, BorderLayout.CENTER);
    }
}