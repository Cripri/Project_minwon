package gui.phs.firstpage;

import gui.phs.simpleDocPanel.SimpleDocPanel;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

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

        JButton leftButton = createCenterButton("단순 서류\n출력 민원");
        JButton rightButton = createCenterButton("ㅇㅇ구\n신문고");

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

    private JButton createCenterButton(String text) {
        JButton button = new JButton("<html><center>" + text.replace("\n", "<br>") + "</center></html>");
        button.setBackground(new Color(30, 160, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        button.setFocusPainted(false);
        return button;
    }
}