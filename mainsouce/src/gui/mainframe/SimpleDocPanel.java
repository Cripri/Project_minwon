package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.mainframe.components.RoundedButton;

// 효승님이 한거 버튼 크기 수정본
public class SimpleDocPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public SimpleDocPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

        // 가운데 버튼 영역 (BoxLayout 사용)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBackground(new Color(217, 217, 217));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // 전체 여백

        RoundedButton leftButton = new RoundedButton("주민등록증 신청\n및 재발급");
        RoundedButton rightButton = new RoundedButton("주민등록 등본/\n초본 발급");
        
        leftButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        rightButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        Dimension buttonSize = new Dimension(300, 300);
        leftButton.setPreferredSize(buttonSize);
        leftButton.setMaximumSize(buttonSize);
        leftButton.setMinimumSize(buttonSize);
        rightButton.setPreferredSize(buttonSize);
        rightButton.setMaximumSize(buttonSize);
        rightButton.setMinimumSize(buttonSize);

        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(leftButton);
        centerPanel.add(Box.createRigidArea(new Dimension(50, 0))); // 버튼 사이 간격
        centerPanel.add(rightButton);
        centerPanel.add(Box.createHorizontalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}
