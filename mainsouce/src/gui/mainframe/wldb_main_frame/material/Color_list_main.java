package gui.mainframe.wldb_main_frame.material;

import java.awt.Color;


import gui.popup.wldb.pop_up_material.Color_list;

public class Color_list_main extends Color_list {
	
	private static Color gray_white = new Color(217, 217, 217);
	
	static {
		color_list_add(gray_white);
	}

	public static Color getGray_white() {
		return gray_white;
	}
	
	
	
	

}
