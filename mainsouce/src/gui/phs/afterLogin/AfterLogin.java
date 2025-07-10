package gui.phs.afterLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.Year;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gui.phs.simpleDocPanel.FrameTop;

public class AfterLogin extends JFrame {
	private static final long serialVersionUID = 1L;


	public AfterLogin() {
        basicFrame("After log-in UI");

        FrameTop topPanel = new FrameTop();
        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(10, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 300, 30, 300));
        formPanel.setBackground(new Color(217, 217, 217));

        JPanel[] rows = new JPanel[10];
        for (int i = 0; i < 10; i++) {
            rows[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            rows[i].setBackground(new Color(217, 217, 217));
            formPanel.add(rows[i]);
        }

        //고쳐야함 !!!!!
        rows[0].add(new JLabel("신청인"));
        rows[0].add(new JTextField(20));

        rows[1].add(new JLabel());
        

        rows[2].add(new JLabel("주소"));
        rows[2].add(new JTextField("시도", 10));
        rows[2].add(new JTextField("시군구", 10));
        
        rows[3].add(new JLabel("연락처"));
        rows[3].add(new JTextField(20));
        
        rows[4].add(new JLabel("생년월일 / 성별"));
        
        int currentYear = Year.now().getValue(); // 예: 2025
        int startYear = 1850;
        int count = currentYear - startYear + 1;

        String[] yearStrings = new String[count];

        // 2025, 2024, ..., 1850 순으로 채움
        for (int i = 0; i < count; i++) {
            yearStrings[i] = String.valueOf(currentYear - i);
        }

        JComboBox<String> yearsBox = new JComboBox<>(yearStrings);
        rows[4].add(yearsBox);
        
        add(formPanel, BorderLayout.CENTER);

        setVisible(true);	
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AfterLogin::new);
    }

//		basicFrame("After Log-in UI");
//		
//		FrameTop ft = new FrameTop();
//		add(ft, BorderLayout.NORTH);
//		
//		JPanel gridPanel = new JPanel(new GridLayout(10, 1, 10, 10));
//		gridPanel.setBorder(BorderFactory.createEmptyBorder(30, 300, 30, 300));
//		gridPanel.setBackground(new Color(217, 217, 217));
//		
//		JPanel[] rows = new JPanel[10];
//		
//		for (int i = 0; i < 9; i++) {
//			rows[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
//			rows[i].setBackground(new Color(217, 217, 217));
//			gridPanel.add(rows[i]);
//		}
//		
//		
//	}
	
	public void basicFrame(String title) {
		setTitle("title");
		setSize(1600, 900); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}
	
}
