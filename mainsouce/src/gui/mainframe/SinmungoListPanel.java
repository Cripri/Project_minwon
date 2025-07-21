package gui.mainframe;

import static gui.mainframe.MainFrameState.civil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import function.connector.Department;
import function.connector.Sinmungo;
import gui.mainframe.components.PaginationPanel;
import gui.mainframe.components.RoundedButton;
import gui.mainframe.components.SearchBarPanel;
import gui.mainframe.components.TableCardPanel;
import gui.mainframe.model.Petition;
import gui.phs.CivilComplaintDetailPanel;
import gui.phs.SinmungoinfoPanel;

public class SinmungoListPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private SearchBarPanel searchBar;
    private TableCardPanel tableCardPanel;
    private PaginationPanel paginationPanel;

    List<Sinmungo> sin = civil.selectAll(Sinmungo.class);
    List<Petition> petitions = new ArrayList<>();
    List<Department> dp = civil.selectAll(Department.class);


    private int currentPage = 0;
    private final int itemsPerPage = 5;

    public SinmungoListPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(217, 217, 217));
        petitions = buildPetitionList(sin);

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
        writeBtn.addActionListener((e) -> {
        	if (MainFrameState.member == null) {
        		MainFrameState.postLoginTarget = "sinmungoinfoPanel";
        		MainFrameState.card.show("login");        		
        	} else {
        		Component[] components = MainFrameState.card.getComponents();
            	for (Component comp : components) {
            	    if (comp instanceof SinmungoinfoPanel) {
            	    	// 신문고인포패널 있었다면 제거
            	        MainFrameState.card.remove(comp);
            	        break;
            	    }
            	}
            	SinmungoinfoPanel infoPanel = new SinmungoinfoPanel();
            	MainFrameState.card.add("sinmungoinfoPanel", infoPanel);
        		MainFrameState.card.show("sinmungoinfoPanel");
        	}
        });
        
		bottomPanel.add(writeBtn, BorderLayout.EAST);
		bottomPanel.add(paginationPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        searchBar.addSearchListener(e -> {
			String keyword = searchBar.getSearchText().trim();
			List<Petition> filtered = buildPetitionList(filterSinmungos(keyword));
			tableCardPanel.updatePetitions(filtered);
			paginationPanel.updatePages((int) Math.ceil((double) filtered.size() / itemsPerPage));
			paginationPanel.setCurrentPage(0);
			currentPage = 0;
			tableCardPanel.showPage(0);
		});
    }
    
    // 전체 Sinmungo를 Petition 리스트로 변환
 	private List<Petition> buildPetitionList(List<Sinmungo> sinmungos) {
 		List<Petition> result = new ArrayList<>();
 		for (int i = sinmungos.size() - 1; i >= 0; i--) {
 			Sinmungo s = sinmungos.get(i);
 			if (s.getStatus().equals("C")) {
 				String dename = dp.stream()
 						.filter(d -> Objects.equals(d.getDepartment_code(), s.getEmployee_code()))
 						.map(Department::getDepartment_name)
 						.findFirst().orElse("");
 				result.add(new Petition(
 						String.valueOf(s.getSinmungo_code()),
 						s.getSinmungo_title(),
 						dename,
 						s.getAnswer_date()));
 			}
 		}
 		return result;
 	}

 	// 검색어에 맞는 Sinmungo 필터링
 	private List<Sinmungo> filterSinmungos(String keyword) {
 		if (keyword.isEmpty()) return sin;
 		return sin.stream()
 				.filter(s -> s.getSinmungo_title().contains(keyword) || s.getSinmungo_content().contains(keyword))
 				.toList();
 	}
 	
//    private void inputlist(List<Sinmungo> sin) {
//        for (int i = sin.size()-1; i >= 0; i--) {
//            if(sin.get(i).getStatus().equals("C")) {
//                String dename = "";
//                for (Department d : dp) {
//                    if (Objects.equals(d.getDepartment_code(), sin.get(i).getEmployee_code())) {
//                        dename = d.getDepartment_name();
//                    }
//                }
//                petitions.add(new Petition(String.valueOf(sin.get(i).getSinmungo_code()), sin.get(i).getSinmungo_title(), dename, sin.get(i).getAnswer_date()));
//            }
//        }
//    }
}