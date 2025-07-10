package gui.phs.simpleDocPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FrameTest extends JFrame {
    private static final long serialVersionUID = 1L;

    public FrameTest() {
        setTitle("Login UI");
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙
        setLayout(new BorderLayout());

        // 상단 패널 추가
        FrameTop ft = new FrameTop();
        add(ft, BorderLayout.NORTH);

        // 카드 레이아웃 패널 생성 및 추가
        CardLayoutPanel cardPage = new CardLayoutPanel();
        add(cardPage, BorderLayout.CENTER);

        // 화면 패널들 생성
        SimpleDocPanel simpleDocPanel = new SimpleDocPanel();
        // 다른 화면도 나중에 필요하면 추가
        // LoginPanel loginPanel = new LoginPanel();
        // MyPage myPage = new MyPage();

        // 카드 레이아웃 이름 설정
        cardPage.add(simpleDocPanel, "simpleDoc");

        // 카드 레이아웃 보여주기
        CardLayout cl = (CardLayout) cardPage.getLayout();
        cl.show(cardPage, "simpleDoc");

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrameTest::new);
    }
}