package gui.mainframe.wldb_main_frame;

import javax.swing.JPanel;

import gui.mainframe.wldb_main_frame.material.Color_list_main;
import gui.mainframe.wldb_main_frame.material.Get_main_base;

public class Employees_petition_list_panel extends JPanel{
	
	static Color_list_main coll = new Color_list_main();
	static Get_main_base gmb = new Get_main_base();
	
	
	static {
		coll.setInside_number(8);
	}
	
	public Employees_petition_list_panel() {
		JPanel emp_pet_list = gmb.setting_public_panel(this, coll.getInside_color());
		
		
		
		
	}

}
