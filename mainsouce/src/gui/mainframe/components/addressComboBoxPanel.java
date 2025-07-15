package gui.mainframe.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import function.connector.District;
import function.connector.QueryRequest;
import gui.mainframe.MainFrameState;

public class addressComboBoxPanel {
	
	String sido;
	String sigungu;
	
	public JPanel addressComboBoxPanel() {
		
		JPanel panel = new JPanel();
        panel.setBackground(new Color(217, 217, 217));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        // 시도 콤보박스
        JComboBox<String> sidoComboBox = new JComboBox<>();
        sidoComboBox.setBackground(Color.WHITE);
        // 월 콤보박스
        JComboBox<String> sggComboBox = new JComboBox<>();
        sggComboBox.setBackground(Color.WHITE);

        QueryRequest<District> sidoreq = new QueryRequest<>(
                "select distinct sd_name from district",
                null,
                District.class,
                MainFrameState.civil
        );
        
        List<District> dis = sidoreq.getResultList();
        
        for(District d : dis) {
        	sidoComboBox.addItem(d.getSd_name());
        }
        
        sidoComboBox.addActionListener((e) -> {
            sido = (String) sidoComboBox.getSelectedItem();
            sggimport(sggComboBox,sido);
        });
        
        sggComboBox.addActionListener((e) -> {
        	sigungu = (String) sggComboBox.getSelectedItem();
        });

        panel.add(sidoComboBox);
        panel.add(sggComboBox);

        return panel;
    }
	
	private void sggimport(JComboBox box,String str) {
		QueryRequest<District> ssgreq = new QueryRequest<>(
                "select DISTINCT sgg_name FROM district WHERE sd_name = ?",
                str,
                District.class,
                MainFrameState.civil
        );
		List<District> dis = ssgreq.getResultList();
		box.removeAllItems();
		for(District d : dis) {
        	box.addItem(d.getSgg_name());
        }
	}
	
}
