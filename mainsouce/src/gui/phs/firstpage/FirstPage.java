package gui.phs.firstpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.mainframe.MainFrameState;
import gui.mainframe.SimpleDocPanel;
import gui.mainframe.components.RoundedButton;

public class FirstPage extends SimpleDocPanel {

    private static final long serialVersionUID = 1L;

    public FirstPage() {
        removeAll(); 
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));
//s
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); 

        JButton leftButton = new RoundedButton("단순 서류\n출력 민원");
        JButton rightButton = new RoundedButton("ㅇㅇ구\n 신문고");
        
        leftButton.addActionListener((e) -> {
        	MainFrameState.card.show("simpleDoc");
        });
        
        rightButton.addActionListener((e) -> {
        	MainFrameState.card.show("sinmungoList");
        });
        
        leftButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        rightButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        Dimension buttonSize = new Dimension(300, 300);
        leftButton.setPreferredSize(buttonSize);
        rightButton.setPreferredSize(buttonSize);
        leftButton.setMaximumSize(buttonSize);
        rightButton.setMaximumSize(buttonSize);

        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(leftButton);
        centerPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        centerPanel.add(rightButton);
        centerPanel.add(Box.createHorizontalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}