package gui.mainframe.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

// 모서리 둥근 버튼
public class RoundedButton extends JButton {
    private static final long serialVersionUID = 1L;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // 손가락 커서 추가
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color bgColor = new Color(17, 120, 255);
        Color fgColor = Color.WHITE;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(bgColor.darker());
        } else if (getModel().isRollover()) {
            g2.setColor(bgColor.brighter());
        } else {
            g2.setColor(bgColor);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // 텍스트 위치 조정
        FontMetrics fm = g.getFontMetrics();
        Rectangle textBounds = fm.getStringBounds(getText(), g2).getBounds();
        int textX = (getWidth() - textBounds.width) / 2;
        int textY = (getHeight() - textBounds.height) / 2 + fm.getAscent();

        g2.setColor(fgColor);
        g2.drawString(getText(), textX, textY);

        g2.dispose();
        super.paintComponent(g);
    }
}