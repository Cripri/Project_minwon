package gui.mainframe.components;

import javax.swing.*;
import java.awt.*;

public class BirthDateSelector {
	
	Integer year;
	Integer month;
	Integer day;

    JComboBox<String> yearComboBox = new JComboBox<>();
    JComboBox<String> monthComboBox = new JComboBox<>();
    JComboBox<String> dayComboBox = new JComboBox<>();

    public JPanel getBirthDatePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(217, 217, 217));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        // 년도 콤보박스

        yearComboBox.setBackground(Color.WHITE);
        for (int year = 2025; year >= 1926; year--) {
            yearComboBox.addItem(year + "년");
        }
        
        yearComboBox.addActionListener((e) -> {
            String selected = (String) yearComboBox.getSelectedItem();
            if (selected != null) {
                String y = selected.replace("년", "");
                year = Integer.parseInt(y);
            }
        });

        // 월 콤보박스

        monthComboBox.setBackground(Color.WHITE);
        for (int month = 1; month <= 12; month++) {
            monthComboBox.addItem(month + "월");
        }
        
        monthComboBox.addActionListener((e) -> {
            String selected = (String) monthComboBox.getSelectedItem();
            if (selected != null) {
                String m = selected.replace("월", "");
                month = Integer.parseInt(m);
            }
        });

        // 일 콤보박스

        dayComboBox.setBackground(Color.WHITE);
        for (int day = 1; day <= 31; day++) {
            dayComboBox.addItem(day + "일");
        }
        
        dayComboBox.addActionListener((e) -> {
            String selected = (String) dayComboBox.getSelectedItem();
            if (selected != null) {
                String d = selected.replace("일", "");
                day = Integer.parseInt(d);
            }
        });

        panel.add(yearComboBox);
        panel.add(monthComboBox);
        panel.add(dayComboBox);

        return panel;
    }

    public JComboBox getYearbox(){
        return yearComboBox;
    }

    public JComboBox getMonthbox(){
        return monthComboBox;
    }

    public JComboBox getDaybox(){
        return dayComboBox;
    }

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
}
