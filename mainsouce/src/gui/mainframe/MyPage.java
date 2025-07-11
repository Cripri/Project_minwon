package gui.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.mainframe.components.RoundedButton;

public class MyPage extends JPanel {

	private static final long serialVersionUID = 1L;

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

    private JPanel titlePanel(String title, String subtitle) {
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
        String[] headers = {"번호", "제목", "처리기관", "등록일"};
        Object[][] rows = {
            {"51", "노원구 어쩌구 아스팔트 파임", "도로 교통공사", "2025-07-03"},
            {"50", "노원구 어쩌구 아스팔트 파임", "도로 교통공사", "2025-07-03"}
        };

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
            new LineBorder(Color.LIGHT_GRAY),
            new EmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;

        Font font = new Font("맑은 고딕", Font.PLAIN, 13);
        Font headerFont = new Font("맑은 고딕", Font.BOLD, 14);

        double[] columnWeights = {0.1, 0.4, 0.2, 0.3};

        // 헤더
        for (int col = 0; col < headers.length; col++) {
            gbc.gridx = col;
            gbc.gridy = 0;
            gbc.weightx = columnWeights[col];
            JLabel label = new JLabel(headers[col], SwingConstants.CENTER);
            label.setFont(headerFont);
            panel.add(label, gbc);
        }

        // 데이터 행
        for (int row = 0; row < rows.length; row++) {
            for (int col = 0; col < headers.length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                gbc.weightx = columnWeights[col];

                JLabel dataLabel = new JLabel(rows[row][col].toString(), SwingConstants.CENTER);
                dataLabel.setFont(font);
                panel.add(dataLabel, gbc);
            }
        }
        return panel;
    }

    private JPanel issueTableSection() {
        String[] headers = {"번호", "제목", "처리기관", "등록일"};
        Object[][] rows = {
            {"51", "등본", "출력", "2025-07-03"},
            {"50", "초본", "출력", "2025-07-03"}
        };

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
            new LineBorder(Color.LIGHT_GRAY),
            new EmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;

        Font font = new Font("맑은 고딕", Font.PLAIN, 13);
        Font headerFont = new Font("맑은 고딕", Font.BOLD, 14);

        double[] columnWeights = {0.1, 0.4, 0.2, 0.3};

        // 헤더
        for (int col = 0; col < headers.length; col++) {
            gbc.gridx = col;
            gbc.gridy = 0;
            gbc.weightx = columnWeights[col];
            JLabel label = new JLabel(headers[col], SwingConstants.CENTER);
            label.setFont(headerFont);
            panel.add(label, gbc);
        }

        // 데이터 행
        for (int row = 0; row < rows.length; row++) {
            for (int col = 0; col < headers.length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                gbc.weightx = columnWeights[col];

                if (col == 2) {
                	RoundedButton btn = new RoundedButton("출력");
                    btn.setPreferredSize(new Dimension(120, 30));
                    btn.setForeground(Color.WHITE);
                    btn.setFocusPainted(false);
                    btn.setFont(font);
                    btn.setBorderPainted(false);

                    JPanel btnWrapper = new JPanel();
                    btnWrapper.setBackground(Color.WHITE);
                    btnWrapper.add(btn);
                    panel.add(btnWrapper, gbc);
                } else {
                    JLabel dataLabel = new JLabel(rows[row][col].toString(), SwingConstants.CENTER);
                    dataLabel.setFont(font);
                    panel.add(dataLabel, gbc);
                }
            }
        }
        return panel;
    }
}