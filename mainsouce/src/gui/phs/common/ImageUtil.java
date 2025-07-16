package gui.phs.common;

import javax.swing.*;
import java.awt.*;

public class ImageUtil {

    /**
     * 이미지 아이콘을 리사이즈하여 반환하는 유틸리티 함수
     *
     * @param path 이미지 경로 (예: "편집이미지.png")
     * @param width 원하는 너비
     * @param height 원하는 높이
     * @return 리사이즈된 ImageIcon
     */
    public static ImageIcon getScaledIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}