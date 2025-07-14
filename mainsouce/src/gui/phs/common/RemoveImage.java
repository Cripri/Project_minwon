package gui.phs.common;

import javax.swing.*;
import java.net.URL;

/**
 * 이미지 리소스를 쉽게 불러오기 위한 유틸 클래스
 */
public class RemoveImage {

    /**
     * 리소스 경로를 기반으로 ImageIcon 객체를 반환합니다.
     *
     * @param path 클래스패스 기준 리소스 경로 (예: "/IconImage/휴지통.png")
     * @return ImageIcon 객체 또는 null (리소스를 못 찾을 경우)
     */
    public static ImageIcon getIcon(String path) {
        URL imageUrl = RemoveImage.class.getResource(path);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        } else {
            System.err.println("이미지를 찾을 수 없습니다: " + path);
            return null;
        }
    }
}