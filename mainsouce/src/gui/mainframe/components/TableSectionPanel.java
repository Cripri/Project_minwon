package gui.mainframe.components;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import gui.mainframe.SinmungoDetailPanel;
import gui.mainframe.model.Petition;

import static gui.mainframe.MainFrameState.card;

public class TableSectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public TableSectionPanel(List<Petition> data) {
        String[] headers = {"번호", "제목", "처리기관", "등록일"};
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(new CompoundBorder(new LineBorder(Color.WHITE), new EmptyBorder(10, 10, 10, 10)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font font = new Font("맑은 고딕", Font.PLAIN, 14);
        Font headerFont = new Font("맑은 고딕", Font.BOLD, 15);
        double[] weights = {0.1, 0.5, 0.2, 0.2};

        for (int col = 0; col < headers.length; col++) {
            gbc.gridx = col;
            gbc.gridy = 0;
            gbc.weightx = weights[col];
            JLabel header = new JLabel(headers[col], SwingConstants.CENTER);
            header.setFont(headerFont);
            add(header, gbc);
        }

        for (int row = 0; row < data.size(); row++) {
            Petition p = data.get(row);
            String[] rowData = {p.getNumber(), p.getTitle(), p.getOrganization(), p.getDate()};

            for (int col = 0; col < rowData.length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                gbc.weightx = weights[col];
                JLabel cell = new JLabel(rowData[col], SwingConstants.CENTER);
                cell.setFont(font);

                if (col == 1) {
                    cell.setForeground(Color.BLUE.darker());
                    cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    cell.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            cell.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            //JOptionPane.showMessageDialog(null, "상세 페이지 이동: " + p.getTitle());

                            card.add(new SinmungoDetailPanel(p.getNumber(), "마이페이지 아님"), "detailPanel_" + p.getNumber());
                            card.show("detailPanel_" + p.getNumber());
                        }
                    });
                }

                add(cell, gbc);
            }
        }
    }
}