package gui.mainframe;

import gui.mainframe.components.*;
import gui.mainframe.model.Petition;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

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

        // 페이지네이션
        int totalPages = (int) Math.ceil((double) petitions.size() / itemsPerPage);
        paginationPanel = new PaginationPanel(totalPages, newPage -> {
            currentPage = newPage;
            tableCardPanel.showPage(newPage);
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        bottomPanel.add(paginationPanel, BorderLayout.CENTER);
        
        // 새 글 작성 버튼
		RoundedButton writeBtn = new RoundedButton("새 민원 작성");
		writeBtn.setPreferredSize(new Dimension(160, 40));
		writeBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		writeBtn.setBorderPainted(false);
		writeBtn.setFocusPainted(false);
		
		// 민원 작성 페이지로 이동 설정해야 함 
//		writeBtn.addActionListener((e) ->{
//			MainFrameState.card.show("민원작성페이지");
//		});

        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(writeBtn, BorderLayout.EAST);
    }
}