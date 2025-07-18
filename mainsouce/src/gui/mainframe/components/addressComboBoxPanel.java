package gui.mainframe.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import function.connector.District;
import function.connector.QueryRequest;
import gui.mainframe.MainFrameState;

public class addressComboBoxPanel {
	
	String sido;
	String sigungu;

	JComboBox<String> sidoComboBox = new JComboBox<>();
	JComboBox<String> sggComboBox = new JComboBox<>();
	
	public JPanel addressComboBoxPanel() {
		
		JPanel panel = new JPanel();
        panel.setBackground(new Color(217, 217, 217));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        // 시도 콤보박스
		sidoComboBox.setBackground(Color.WHITE);
        // 시군구 콤보박스
        sggComboBox.setBackground(Color.WHITE);

        QueryRequest<District> sidoreq = new QueryRequest<>(
                "select distinct sd_name from district",
                null,
                District.class,
                MainFrameState.civil
        );
        
        List<District> dis = sidoreq.getResultList();

		sidoComboBox.addItem(null);
        for(District d : dis) {
        	sidoComboBox.addItem(d.getSd_name());
        }
        sido = dis.get(0).getSd_name();
        sggimport(sggComboBox,sido);
        
        sidoComboBox.addActionListener((e) -> {
            sido = (String) sidoComboBox.getSelectedItem();
            sggimport(sggComboBox,sido);
        });

		sggComboBox.addItem(null);
        sggComboBox.addActionListener((e) -> {
        	sigungu = (String) sggComboBox.getSelectedItem();
        });

        panel.add(sidoComboBox);
        panel.add(sggComboBox);

        return panel;
    }
	
	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigungu() {
		return sigungu;
	}

	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}

	public JComboBox getsidocombo(){
		return sidoComboBox;
	}

	public JComboBox getsigungucombo(){
		return sggComboBox;
	}
	
	public int findDistrictCode(String sido, String sigungu) {
	      List<Object> list = new ArrayList<>();
	      list.add(sido);
	      list.add(sigungu);
	      QueryRequest<District> qr = new QueryRequest<>(
	            "select * from district where sd_name like ? and sgg_name like ?",
	            list,
	            District.class,
	            MainFrameState.civil
	         );
	      District d = qr.getSingleResult();
	      
	      return d != null ? d.getDistrict_code() : -1;
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
