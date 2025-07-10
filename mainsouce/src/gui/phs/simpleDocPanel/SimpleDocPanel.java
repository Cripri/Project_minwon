package gui.phs.simpleDocPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

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

        JButton leftButton = createCenterButton("주민등록증 신청\n및 재발급");
        JButton rightButton = createCenterButton("주민등록 등본/\n초본 발급");

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

    private JButton createCenterButton(String text) {
        JButton button = new JButton("<html><center>" + text.replace("\n", "<br>") + "</center></html>");
        button.setBackground(new Color(30, 160, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        button.setFocusPainted(false);
        return button;
    }
}
