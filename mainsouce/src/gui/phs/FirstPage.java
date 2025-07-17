package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.mainframe.MainFrameState;
import gui.mainframe.SimpleDocPanel;
import gui.mainframe.components.RoundedButton; 

public class FirstPage extends SimpleDocPanel {

    private static final long serialVersionUID = 1L;

    public FirstPage() {
        removeAll();
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        RoundedButton leftButton = createCenterButton("단순 서류\n출력 민원");
        RoundedButton rightButton = createCenterButton("ㅇㅇ구\n신문고");

        Dimension buttonSize = new Dimension(300, 300);
        leftButton.setPreferredSize(buttonSize);
        rightButton.setPreferredSize(buttonSize);
        leftButton.setMaximumSize(buttonSize);
        rightButton.setMaximumSize(buttonSize);
        
        
        
        leftButton.addActionListener((e) -> {
        	MainFrameState.card.show("simpleDoc");
        });
        
        rightButton.addActionListener((e) -> {
            MainFrameState.card.show("sinmungoList");
       
        });
        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(leftButton); 
        centerPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        centerPanel.add(rightButton);
        centerPanel.add(Box.createHorizontalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }

    private RoundedButton createCenterButton(String text) {
        RoundedButton button = new RoundedButton(text);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
        return button;
    }
}
