package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gui.mainframe.FrameTop;
import gui.phs.common.BasicFrame;

public class WriteContent extends JFrame {

    public WriteContent() {
        BasicFrame.setupBasicFrame(this, "민원 작성");

        // 상단 바
        FrameTop topPanel = new FrameTop();
        add(topPanel, BorderLayout.NORTH);

        // 중앙 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // 제목 패널 (레이블 + 텍스트필드)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false); // 배경 투명하게
        JLabel titleLabel = new JLabel("제목");
        JTextField titleField = new JTextField(30);
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        // 내용 패널 (레이블 + 텍스트영역)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        JLabel contentLabel = new JLabel("내용");
        JTextArea contentArea = new JTextArea(10, 40);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(contentArea);

        contentPanel.add(contentLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // 제목과 내용 사이에 공간 주기
        centerPanel.add(titlePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(contentPanel);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WriteContent::new);
    }
}
