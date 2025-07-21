package gui.phs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import function.connector.Department;
import function.connector.Employees;
import function.connector.QueryRequest;
import function.connector.Sinmungo;
import gui.mainframe.MainFrameState;

public class ComplaintAnswerListPanel extends JPanel {

    private String searchKeyword = null;
    private Integer employeeCode = null;

    public ComplaintAnswerListPanel() {
        initUI();
    }

    public ComplaintAnswerListPanel(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        initUI();
    }

    public ComplaintAnswerListPanel(int employeeCode) {
        this.employeeCode = employeeCode;
        initUI();
    }

    public ComplaintAnswerListPanel(String searchKeyword, int employeeCode) {
        this.searchKeyword = searchKeyword;
        this.employeeCode = employeeCode;
        initUI();
    }
    
    public String getSearchKeyword() {
			return searchKeyword;
		}

		public void setSearchKeyword(String searchKeyword) {
			this.searchKeyword = searchKeyword;
		}

		public Integer getEmployeeCode() {
			return employeeCode;
		}

		public void setEmployeeCode(Integer employeeCode) {
			this.employeeCode = employeeCode;
		}

		private void initUI() {
    	setLayout(new BorderLayout());
        setBackground(new Color(217, 217, 217));

        // 제목
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(217, 217, 217));
        titlePanel.setBorder(new EmptyBorder(30, 30, 10, 30));

        JLabel title = new JLabel("민원신청내역");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//        JLabel subtitle = new JLabel("클릭한 민원에 대한 내용 또는 검색결과 / 할당된 민원");
//        subtitle.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        titlePanel.add(title);
//        titlePanel.add(subtitle);

        add(titlePanel, BorderLayout.NORTH);

        // 테이블 박스
        JPanel tableWrapper = new JPanel();
        tableWrapper.setLayout(new BorderLayout());
        tableWrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(10, 10, 10, 10)
        ));
        tableWrapper.setBackground(Color.WHITE);

        JTable table = createFilteredTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        tableWrapper.setPreferredSize(new Dimension(1500, 300));
        add(tableWrapper, BorderLayout.CENTER);
    }

    private JTable createFilteredTable() {
        String[] columnNames = {"접수 번호", "제목", "처리상태", "처리기관", "등록일"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sql = null;

        if(searchKeyword == null){
            sql = new StringBuilder("SELECT * FROM sinmungo WHERE status = 'P'");
        }else{
            if (searchKeyword.equals("처리중")) {
                sql = new StringBuilder("SELECT * FROM sinmungo WHERE status = 'I'");
            }
        }
        List<Object> params = new ArrayList<>();
        if (employeeCode != null) {
            sql.append(" AND employee_code = ?");
            params.add(employeeCode);
        }

        if (searchKeyword != null) {
            sql.append(" AND sinmungo_title LIKE ?");
            params.add("%" + searchKeyword + "%");
        }

        QueryRequest<Sinmungo> request = new QueryRequest<>(
            sql.toString(),
            params,
            Sinmungo.class,
            MainFrameState.civil
        );
        List<Sinmungo> list = request.getResultList();
        
        Object[][] data = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            Sinmungo sinmungo = list.get(i);
            Employees emp = MainFrameState.civil.find(Employees.class, sinmungo.getEmployee_code());
            Department dept;
            if (emp != null) {
            	dept = MainFrameState.civil.find(Department.class, emp.getDepartment_code());
            } else {
            	dept = null;
            }

            data[i][0] = sinmungo.getSinmungo_code();
            data[i][1] = sinmungo.getSinmungo_title();
            data[i][2] = dept != null ? dept.getDepartment_name() : "판별 불가";
            data[i][3] = sdf.format(sinmungo.getCreate_date());
            data[i][4] = sinmungo.getAnswer_date() != null ? sdf.format(sinmungo.getAnswer_date()) : "답변 없음";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(240, 240, 240));

        // 가운데 정렬
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    Integer sinmungoCode = Integer.parseInt(table.getValueAt(row, 0).toString());

//                    Component[] components = MainFrameState.card.getComponents();
//                	for (Component comp : components) {
//                	    if (comp instanceof CivilComplaintDetailPanel) {
//                	    	// 디테일 패널이 원래 있었다면 제거
//                	        MainFrameState.card.remove(comp);
//                	        break;
//                	    }
//                	}

                	// 새로운 상세 패널 생성 후 추가
                	CivilComplaintDetailPanel detailPanel = new CivilComplaintDetailPanel(sinmungoCode);
                	MainFrameState.card.add(detailPanel, "detail_" + sinmungoCode);
                	MainFrameState.card.show("detail_" + sinmungoCode);
                }
            }
        });

        return table;
    }
}
