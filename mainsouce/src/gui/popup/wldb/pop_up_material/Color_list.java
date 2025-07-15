package gui.popup.wldb.pop_up_material;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.border.LineBorder;

public class Color_list {
	
	
	private static Color bright_gray = new Color(240, 240, 240);
	private static Color pink = new Color(255, 164, 164);
	private static Color red = new Color(241, 95, 95);
	private static Color blue = new Color(103, 153, 255);
	
	private static Color[] colors = {
			Color.white, bright_gray, Color.gray,
			pink, red, Color.green,
			blue, Color.black
			};
	private static ArrayList<Color> color_list =
			new ArrayList<Color>(Arrays.asList(colors));
	
	private static int inside_number = 1;
	private static int outside_number = 0;

	private static Color inside_color = color_list.get(inside_number);
	private static Color outside_color = color_list.get(outside_number);
			
	private static LineBorder line = new LineBorder(outside_color, 10);

	public static Color getPink() {
		return pink;
	}

	public static Color getInside_color() {
		return inside_color;
	}

	public static Color getOutside_color() {
		return outside_color;
	}

	public static LineBorder getLine() {
		return line;
	}

	public static void setInside_number(int inside_number) {
		Color_list.inside_number = inside_number;
	}

	public static void setOutside_number(int outside_number) {
		Color_list.outside_number = outside_number;
	}
	
	protected static void color_list_add(Color col) {
		color_list.add(col);
	}
	
}
