package gui.mainframe.components;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class IconButton extends JButton {

	private static final long serialVersionUID = 1L;

	// 텍스트 + 아이콘 생성자
    public IconButton(String text, String iconPath, int iconWidth, int iconHeight, int textPosition) {
        super(text);
        initCommon(iconPath, iconWidth, iconHeight);
        setHorizontalTextPosition(textPosition);
        setFont(new Font("맑은 고딕", Font.PLAIN, 14));
    }

    // 텍스트 + 아이콘 (기본 크기 20x20, 텍스트 오른쪽)
    public IconButton(String text, String iconPath) {
        this(text, iconPath, 20, 20, SwingConstants.RIGHT);
    }

    // 아이콘만 있는 버튼
    public IconButton(String iconPath, int iconWidth, int iconHeight) {
        super();
        initCommon(iconPath, iconWidth, iconHeight);
    }

    // 아이콘만 (기본 크기 20x20)
    public IconButton(String iconPath) {
        this(iconPath, 20, 20);
    }

    // 공통 초기화 메서드
    private void initCommon(String iconPath, int iconWidth, int iconHeight) {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon icon = new ImageIcon(iconPath);
        Image scaled = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaled));
    }
}