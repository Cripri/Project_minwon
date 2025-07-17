package gui.mainframe;

import function.connector.Sinmungo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import static gui.mainframe.MainFrameState.civil;

public class SinmungoDetailPanel extends JPanel {

//    public SinmungoDetailPanel(Object pk) {
//        init(pk);
//    }
    
    public SinmungoDetailPanel(Object pk, String s) {
    	init2(pk, s);
    }

//    private void init(Object pk) {
//        setName("마이페이지 신문고 상세");
//        setLayout(new BorderLayout());
//
//        JPanel container = new JPanel();
//        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
//        container.setBackground(new Color(217, 217, 217));
//        container.setBorder(new EmptyBorder(30, 30, 30, 30));
//
//        container.add(MyPage.titlePanel("마이페이지", "신문고 상세 내역"));
//        container.add(sinmungoTableSection(pk));
//
//        add(container);
//        setVisible(true);
//    }
    
    private void init2(Object pk, String s) {
    	boolean isMyPage = s.equals("마이페이지");
        setName(isMyPage ? "마이페이지 신문고 상세" : "신문고 상세");
        setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(new Color(217, 217, 217));
        container.setBorder(new EmptyBorder(30, 30, 30, 30));

        container.add(isMyPage ?
        			MyPage.titlePanel("마이페이지", "신문고 상세 내역") :
        			MyPage.titlePanel("신문고", "상세 내역"));
        container.add(sinmungoTableSection(pk));

        add(container);
        setVisible(true);
    }

    private JPanel sinmungoTableSection(Object pk) {
        // 감싸고 있는 패널 설정
        JPanel panel = new JPanel(new GridBagLayout());
        // 바깥 배경과 색 동일하게
        panel.setBackground(new Color(217, 217, 217));
        // 테두리
//        panel.setBorder(new CompoundBorder(
//            new LineBorder(Color.LIGHT_GRAY),
//            new EmptyBorder(10, 10, 10, 10)
//        ));

        Sinmungo sin = civil.find(Sinmungo.class,pk);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // 폰트 바꾸는 곳
        Font headerFont = new Font("맑은 고딕", Font.BOLD, 14);
        Font contentFont = new Font("맑은 고딕", Font.PLAIN, 13);

        gbc.gridy = 0;
        panel.add(createStyledLabel(sin.getSinmungo_title(), headerFont), gbc);

        gbc.gridy = 1;
        panel.add(makeContentBox(sin.getSinmungo_content(), contentFont, 10), gbc);

        gbc.gridy = 2;
        panel.add(createStyledLabel("답변", headerFont), gbc);

        gbc.gridy = 3;
        panel.add(makeContentBox(sin.getEmployees_answer(), contentFont, 10), gbc);

        return panel;
    }

    // 제목 쪽
    private JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createCompoundBorder(
                // 마지막이 true일 경우 둥근 테두리, 흰색테두리로 해서 안 보이게
                new LineBorder(Color.WHITE, 1, true),
                // 없을 경우 height가 좁아짐
                new EmptyBorder(8, 15, 8, 15)
        ));
        // 중앙정렬
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    // 답변 쪽
    private JPanel makeContentBox(String text, Font font, int rows) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(font);
        // 자동 줄바꿈
        textArea.setLineWrap(true);
        // 단어 단위 줄바꿈
        textArea.setWrapStyleWord(true);
        // 읽기 전용
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 1, true),
                new EmptyBorder(8, 15, 8, 15)
        ));
        textArea.setRows(rows); // 기본으로 보이는 줄 현재 10

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new LineBorder(Color.WHITE));
        scrollPane.setPreferredSize(new Dimension(400, rows * 24));

        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(Color.WHITE);
        box.add(scrollPane, BorderLayout.CENTER);
        //box.setPreferredSize(new Dimension(400, rows * 24)); // 크기 조절

        return box;
    }

}
