package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.model.Petition;

import javax.swing.border.EmptyBorder;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.mainframe.components.PaginationPanel;
import gui.mainframe.components.RoundedButton;
import gui.mainframe.components.SearchBarPanel;
import gui.mainframe.components.TableCardPanel;
import static gui.mainframe.MainFrameState.civil;

public class SinmungoListPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private SearchBarPanel searchBar;
    private TableCardPanel tableCardPanel;
    private PaginationPanel paginationPanel;

    private final List<Petition> petitions = List.of(
        new Petition("51", "노원구 어쩌구 아스팔트 파임", "도로 교통공사", "2025-07-03"),
        new Petition("50", "노원구 어쩌구 아스팔트 파임", "도로 교통공사", "2025-07-03"),
        new Petition("49", "노원구 어쩌구 아스팔트 파임", "도로 교통공사", "2025-07-03"),
        new Petition("48", "노원구 어쩌구 아스팔트 파임 🔒", "도로 교통공사", "2025-07-03"),
        new Petition("47", "등본 발급 관련 문의", "행정복지센터", "2025-07-02"),
        new Petition("46", "횡단보도 신호 개선 요청", "교통안전센터", "2025-07-01"),
        new Petition("45", "가로등 고장 신고", "시설관리공단", "2025-07-01")
    );

    QueryRequest<Sinmungo> sinmungo = new QueryRequest<>(
            "select * from Sinmungo",
            null,
            Sinmungo.class,
            civil
    );
    List<Sinmungo> sin = sinmungo.getResultList();

    private int currentPage = 0;
    private final int itemsPerPage = 5;

    public SinmungoListPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(217, 217, 217));

        // 제목
        JLabel title = new JLabel("민원 게시판");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(title);
        add(topPanel, BorderLayout.NORTH);

        // 메인 콘텐츠
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        searchBar = new SearchBarPanel();
        centerPanel.add(searchBar);
        centerPanel.add(Box.createVerticalStrut(20));

        tableCardPanel = new TableCardPanel(petitions, itemsPerPage);
        centerPanel.add(tableCardPanel);

        add(centerPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // 페이지네이션
        int totalPages = (int) Math.ceil((double) petitions.size() / itemsPerPage);
        paginationPanel = new PaginationPanel(totalPages, newPage -> {
            currentPage = newPage;
            tableCardPanel.showPage(newPage);
        });

        RoundedButton writeBtn = new RoundedButton("새 민원 작성");
        writeBtn.setPreferredSize(new Dimension(160, 40));
        writeBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        writeBtn.setBorderPainted(false);
        writeBtn.setFocusPainted(false);
//        writeBtn.addActionListener((e) -> {
//        	MainFrameState.card.show("write");
//        });
        
        
		bottomPanel.add(writeBtn, BorderLayout.EAST);
		bottomPanel.add(paginationPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}