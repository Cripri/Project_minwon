package gui.mainframe;

import static gui.mainframe.MainFrameState.card;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import function.connector.Complaint_category_info;
import function.connector.Department;
import function.connector.Employees;
import function.connector.QueryRequest;
import function.connector.Simple_doc;
import function.connector.Sinmungo;
import function.drawingsign.DrawingSign;
import function.pdfwriter.PDFWriter;
import gui.mainframe.components.RoundedButton;

public class MyPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int PAGE_SIZE = 5;
	
	// 신문고 페이지 관련
	private int requestCurrentPage = 0;
	private JPanel requestTableContainer;
	
	// 문서 페이지 관련
	private int issueCurrentPage = 0;
	private JPanel issueTableContainer;

	public MyPage() {
        setName("마이페이지");
        setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(new Color(217, 217, 217));
        container.setBorder(new EmptyBorder(30, 30, 30, 30));

        // 마이페이지 제목
        container.add(titlePanel("마이페이지", "나의 민원 신청내역"));
        container.add(Box.createVerticalStrut(15));
        container.add(requestTableSection());

        container.add(Box.createVerticalStrut(30));
        container.add(titlePanel(null, "나의 민원 발급내역"));
        container.add(Box.createVerticalStrut(15));
        container.add(issueTableSection());

        add(container);
        setVisible(true);
    }

    public static JPanel titlePanel(String title, String subtitle) {
        JPanel panel = new JPanel(new GridLayout(title != null ? 2 : 1, 1));
        panel.setBackground(new Color(217, 217, 217));

        if (title != null) {
            JLabel t = new JLabel(title);
            t.setFont(new Font("맑은 고딕", Font.BOLD, 30));
            panel.add(t);
        }

        JLabel st = new JLabel(subtitle);
        st.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        panel.add(st);

        return panel;
    }

    private JPanel requestTableSection() {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.setBorder(new CompoundBorder(
            new LineBorder(Color.LIGHT_GRAY),
            new EmptyBorder(10, 10, 10, 10)
        ));

        requestTableContainer = new JPanel();
        requestTableContainer.setLayout(new BoxLayout(requestTableContainer, BoxLayout.Y_AXIS));
        container.add(requestTableContainer, BorderLayout.CENTER);

        QueryRequest<Sinmungo> request = new QueryRequest<>(
        		"SELECT * FROM sinmungo WHERE member_code = ?",
        		MainFrameState.member.getMember_code(),
        		Sinmungo.class,
        		MainFrameState.civil
        		);
        List<Sinmungo> list = request.getResultList();
        
        // 페이지네이션
        JPanel navPanel = new JPanel();
        navPanel.setBackground(Color.WHITE);

        // 이전, 다음 버튼
        RoundedButton prev = new RoundedButton("이전");
        RoundedButton next = new RoundedButton("다음");

        prev.addActionListener(e -> {
            if (requestCurrentPage > 0) {
                requestCurrentPage--;
                updateRequestTable();
            }
        });

        next.addActionListener(e -> {
            int totalRows = list.size();
            if ((requestCurrentPage + 1) * PAGE_SIZE < totalRows) {
                requestCurrentPage++;
                updateRequestTable();
            }
        });
        navPanel.add(prev);

        // 페이지 번호 버튼 생성
        int totalRows = list == null ? 0 : list.size();
        int totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);

        for (int i = 0; i < totalPages; i++) {
            int pageIndex = i;
            RoundedButton pageButton = new RoundedButton(String.valueOf(i + 1));
            if (i == requestCurrentPage) {
                pageButton.setEnabled(false); // 현재 페이지는 비활성화
            }
            pageButton.addActionListener(e -> {
                requestCurrentPage = pageIndex;
                updateRequestTable();
            });
            navPanel.add(pageButton);
        }

        navPanel.add(next);
        container.add(navPanel, BorderLayout.SOUTH);

        updateRequestTable(); // 초기 렌더링

        container.setPreferredSize(new Dimension(1500, 300));
        return container;
    }
    
    private void updateRequestTable() {
        requestTableContainer.removeAll();

        String[] headers = {"번호", "제목", "처리기관", "등록일", "답변일"};
        int[] columnWidths = {80, 920, 200, 150, 150};
        
        QueryRequest<Sinmungo> request = new QueryRequest<>(
        		"SELECT * FROM sinmungo WHERE member_code like ?",
        		MainFrameState.member.getMember_code(),
        		Sinmungo.class,
        		MainFrameState.civil
        		);
        
        List<Sinmungo> list = request.getResultList();
           
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Font font = new Font("맑은 고딕", Font.PLAIN, 13);
        Font headerFont = new Font("맑은 고딕", Font.BOLD, 14);

        int headerHeight = 40;
        int rowHeight = 40;

        // 헤더
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setPreferredSize(new Dimension(0, headerHeight));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, headerHeight));

        for (int i = 0; i < headers.length; i++) {
            JLabel label = new JLabel(headers[i], SwingConstants.CENTER);
            label.setFont(headerFont);
            label.setPreferredSize(new Dimension(columnWidths[i], headerHeight));
            label.setMaximumSize(new Dimension(columnWidths[i], headerHeight));
            label.setMinimumSize(new Dimension(columnWidths[i], headerHeight));
            headerPanel.add(label);
        }
        requestTableContainer.add(headerPanel);

        // 현재 페이지 데이터
        int start = requestCurrentPage * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, list == null ? 0 : list.size());

        for (int i = start; i < end; i++) {
            Sinmungo sinmungo = list.get(i);
            Employees emp = MainFrameState.civil.find(Employees.class, sinmungo.getEmployee_code());
            Department dept;
            if (emp != null) {
            	dept = MainFrameState.civil.find(Department.class, emp.getDepartment_code());
            } else {
            	dept = null;
            }

            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            rowPanel.setBackground(Color.WHITE);
            rowPanel.setPreferredSize(new Dimension(0, rowHeight));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            rowPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            String[] data = {
                String.valueOf(sinmungo.getSinmungo_code()),
                sinmungo.getSinmungo_title(),
                dept != null ? dept.getDepartment_name() : "판별 불가",
                formatter.format(sinmungo.getCreate_date()),
                sinmungo.getAnswer_date() == null ? "답변 없음" : formatter.format(sinmungo.getAnswer_date())
            };

            for (int j = 0; j < data.length; j++) {
                JLabel label = new JLabel(data[j], SwingConstants.CENTER);
                label.setFont(font);
                label.setPreferredSize(new Dimension(columnWidths[j], rowHeight));
                label.setMaximumSize(new Dimension(columnWidths[j], rowHeight));
                label.setMinimumSize(new Dimension(columnWidths[j], rowHeight));
                rowPanel.add(label);
            }

            rowPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	String panelName = "detailPanel_" + sinmungo.getSinmungo_code();
                	boolean exists = Arrays.stream(card.getComponents())
                	    .anyMatch(c -> panelName.equals(c.getName()));
                	if (!exists) {
                	    SinmungoDetailPanel panel = new SinmungoDetailPanel(sinmungo.getSinmungo_code(), "마이페이지");
                	    panel.setName(panelName);
                	    card.add(panel, panelName);
                	}
                	card.show(panelName);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    rowPanel.setBackground(new Color(230, 230, 250));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    rowPanel.setBackground(Color.WHITE);
                }
            });
            requestTableContainer.add(rowPanel);
        }
        requestTableContainer.revalidate();
        requestTableContainer.repaint();
    }


    private JPanel issueTableSection() {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.setBorder(new CompoundBorder(
                new LineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(10, 10, 10, 10)
            ));

        issueTableContainer = new JPanel();
        issueTableContainer.setLayout(new BoxLayout(issueTableContainer, BoxLayout.Y_AXIS));
        container.add(issueTableContainer, BorderLayout.CENTER);

        QueryRequest<Simple_doc> request = new QueryRequest<>(
        		"SELECT * FROM simple_doc WHERE member_code like ?",
        		MainFrameState.member.getMember_code(),
        		Simple_doc.class,
        		MainFrameState.civil
        		);
        List<Simple_doc> list = request.getResultList();
        
        // 페이지네이션 버튼
        JPanel navPanel = new JPanel();
        navPanel.setBackground(Color.WHITE);

        RoundedButton prev = new RoundedButton("이전");
        RoundedButton next = new RoundedButton("다음");

        prev.addActionListener(e -> {
            if (issueCurrentPage > 0) {
                issueCurrentPage--;
                updateIssueTable();
            }
        });

        next.addActionListener(e -> {
            int totalRows = list == null ? 0 : list.size();
            if ((issueCurrentPage + 1) * PAGE_SIZE < totalRows) {
                issueCurrentPage++;
                updateIssueTable();
            }
        });

        navPanel.add(prev);

        // 페이지 번호 버튼
        int totalRows = list == null ? 0 : list.size();
        int totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);

        for (int i = 0; i < totalPages; i++) {
            final int pageIndex = i;
            RoundedButton pageButton = new RoundedButton(String.valueOf(i + 1));
            if (i == issueCurrentPage) {
                pageButton.setEnabled(false); // 현재 페이지는 비활성화
            }
            pageButton.addActionListener(e -> {
                issueCurrentPage = pageIndex;
                updateIssueTable();
            });
            navPanel.add(pageButton);
        }

        navPanel.add(next);
        container.add(navPanel, BorderLayout.SOUTH);

        updateIssueTable(); // 초기 렌더링

        container.setPreferredSize(new Dimension(1500, 300));
        return container;
    }

    private void updateIssueTable() {
        issueTableContainer.removeAll();

        String[] headers = {"번호", "신청문서", "저장", "등록일", "처리일"};
        int[] columnWidths = {80, 920, 200, 150, 150};

        QueryRequest<Simple_doc> request = new QueryRequest<>(
        		"SELECT * FROM simple_doc WHERE member_code like ?",
        		MainFrameState.member.getMember_code(),
        		Simple_doc.class,
        		MainFrameState.civil
        		);
        List<Simple_doc> list = request.getResultList();
        
//        List<Simple_doc> list = MainFrameState.civil.selectAll(Simple_doc.class);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Font font = new Font("맑은 고딕", Font.PLAIN, 13);
        Font headerFont = new Font("맑은 고딕", Font.BOLD, 14);

        int headerHeight = 40;
        int rowHeight = 40;

        // 헤더 패널
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setPreferredSize(new Dimension(0, headerHeight));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, headerHeight));

        for (int i = 0; i < headers.length; i++) {
            JLabel label = new JLabel(headers[i], SwingConstants.CENTER);
            label.setFont(headerFont);
            label.setPreferredSize(new Dimension(columnWidths[i], headerHeight));
            label.setMaximumSize(new Dimension(columnWidths[i], headerHeight));
            label.setMinimumSize(new Dimension(columnWidths[i], headerHeight));
            headerPanel.add(label);
        }
        issueTableContainer.add(headerPanel);

        // 현재 페이지 데이터
        int start = issueCurrentPage * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, list == null ? 0 : list.size());

        for (int i = start; i < end; i++) {
            Simple_doc doc = list.get(i);
            Complaint_category_info category = MainFrameState.civil.find(Complaint_category_info.class, doc.getComplaint_category_code());

            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            rowPanel.setBackground(Color.WHITE);
            rowPanel.setPreferredSize(new Dimension(0, rowHeight));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
//            rowPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            String[] data = {
                String.valueOf(doc.getSimple_doc_code()),
                category.getComplaint_category_name(),
                "저장", // 출력 버튼 대신 텍스트 자리
                formatter.format(doc.getCreate_date()),
                doc.getComplete_date() == null ? "처리중" : formatter.format(doc.getComplete_date())
            };

            for (int j = 0; j < data.length; j++) {
                if (j == 2) { // 출력 버튼 위치
                    JPanel btnWrapper = new JPanel();
                    btnWrapper.setBackground(Color.WHITE);
                    btnWrapper.setPreferredSize(new Dimension(columnWidths[j], rowHeight));
                    btnWrapper.setMaximumSize(new Dimension(columnWidths[j], rowHeight));
                    btnWrapper.setMinimumSize(new Dimension(columnWidths[j], rowHeight));
                    btnWrapper.setLayout(new GridBagLayout());

                    RoundedButton btn = new RoundedButton("저장");
                    btn.setPreferredSize(new Dimension(70, 30));
                    btn.setFont(font);
                    btn.setForeground(Color.WHITE);
                    btn.setFocusPainted(false);
                    btn.setBorderPainted(false);
                    btn.addActionListener(e -> {
                    	JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this); // this는 패널일 경우
                    	DrawingSign signDialog = new DrawingSign(parent);
                    	signDialog.setVisible(true);  // 여기서 사인 다 끝날 때까지 기다림
                        
                    	String rrn = JOptionPane.showInputDialog("주민등록번호를 입력하세요:");
                        if (rrn == null || rrn.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "주민등록번호가 입력되지 않았습니다.");
                            return;
                        }
                    	
                    	JFileChooser fileChooser = new JFileChooser();
                    	fileChooser.setDialogTitle("파일 저장 위치 선택");
                    	
                    	// 타임스탬프 생성
//                        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                        String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                        // 기본 파일 이름 설정
                        String defaultFileName = "발급_서류_" + timestamp + ".pdf";
                        fileChooser.setSelectedFile(new File(defaultFileName));

                        int result = fileChooser.showSaveDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();

                            // .pdf 확장자 자동 추가
                            if (!file.getName().toLowerCase().endsWith(".pdf")) {
                                file = new File(file.getAbsolutePath() + ".pdf");
                            }
                            
                            // 덮어쓰기 확인
                            if (file.exists()) {
                                int overwrite = JOptionPane.showConfirmDialog(
                                    null,
                                    "이미 같은 이름의 파일이 존재합니다.\n덮어쓰시겠습니까?",
                                    "파일 덮어쓰기 확인",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE
                                );
                                if (overwrite != JOptionPane.YES_OPTION) {
                                    return;
                                }
                            }
                            
                    	    try {
                    	    	new PDFWriter(
                    	                doc.getSimple_doc_code(),
                    	                rrn,
                    	                MainFrameState.civil,
                    	                file.getAbsolutePath()  // 추가된 저장 경로
                    	            );
                    	        JOptionPane.showMessageDialog(null, "파일이 성공적으로 저장되었습니다.");
                    	    } catch (Exception ex) {
                    	        ex.printStackTrace();
                    	        JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.");
                    	    }
                    	}
//                        System.out.println("출력 클릭: " + doc.getSimple_doc_code());
                    });

                    btnWrapper.add(btn);
                    rowPanel.add(btnWrapper);
                } else {
                    JLabel label = new JLabel(data[j], SwingConstants.CENTER);
                    label.setFont(font);
                    label.setPreferredSize(new Dimension(columnWidths[j], rowHeight));
                    label.setMaximumSize(new Dimension(columnWidths[j], rowHeight));
                    label.setMinimumSize(new Dimension(columnWidths[j], rowHeight));
                    rowPanel.add(label);
                }
            }

//            rowPanel.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    System.out.println("행 클릭: " + doc.getSimple_doc_code());
//                }
//                @Override
//                public void mouseEntered(MouseEvent e) {
//                    rowPanel.setBackground(new Color(230, 230, 250));
//                }
//                @Override
//                public void mouseExited(MouseEvent e) {
//                    rowPanel.setBackground(Color.WHITE);
//                }
//            });
            issueTableContainer.add(rowPanel);
        }
        issueTableContainer.revalidate();
        issueTableContainer.repaint();
    }


    
}