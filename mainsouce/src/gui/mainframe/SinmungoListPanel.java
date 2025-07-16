package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import function.connector.Department;
import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.model.Petition;

import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    List<Sinmungo> sin = civil.selectAll(Sinmungo.class);
    List<Petition> petitions = new ArrayList<>();
    List<Department> dp = civil.selectAll(Department.class);

    private void inputlist(List<Sinmungo> sin) {
        for (int i = sin.size()-1; i >= 0; i--) {
            if(sin.get(i).getStatus().equals("C")) {
                String dename = "";
                for (Department d : dp) {
                    if (Objects.equals(d.getDepartment_code(), sin.get(i).getEmployee_code())) {
                        dename = d.getDepartment_name();
                    }
                }
                petitions.add(new Petition(String.valueOf(sin.get(i).getSinmungo_code()), sin.get(i).getSinmungo_title(), dename, sin.get(i).getAnswer_date()));
            }
        }
    }

    private int currentPage = 0;
    private final int itemsPerPage = 5;

    public SinmungoListPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(217, 217, 217));
        inputlist(sin);

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
        	MainFrameState.card.show("write");
        });
        
		bottomPanel.add(writeBtn, BorderLayout.EAST);
		bottomPanel.add(paginationPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}