package gui.mainframe.components;

import javax.swing.*;
import java.awt.*;

public class BirthDateSelector {
	
	Integer year;
	Integer month;
	Integer day;

    private JComboBox<String> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;

    public JPanel getBirthDatePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(217, 217, 217));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        // 년도 콤보박스

        yearComboBox.setBackground(Color.WHITE);
        for (int y = 2025; y >= 1926; y--) {
            yearComboBox.addItem(y + "년");
        }
        
        yearComboBox.addActionListener((e) -> {
            String selected = (String) yearComboBox.getSelectedItem();
            if (selected != null) {
                year = Integer.parseInt(selected.replace("년", ""));
                updateDayComboBox();
            }
        });

        // 월 콤보박스

        monthComboBox.setBackground(Color.WHITE);
        for (int m = 1; m <= 12; m++) {
            monthComboBox.addItem(m + "월");
        }

        monthComboBox.addActionListener((e) -> {
            String selected = (String) monthComboBox.getSelectedItem();
            if (selected != null) {
                month = Integer.parseInt(selected.replace("월", ""));
                updateDayComboBox();
            }
        });

        // 일 콤보박스

        dayComboBox.setBackground(Color.WHITE);
        // 초기 기본값 (예: 31일까지 표시)
        for (int d = 1; d <= 31; d++) {
            dayComboBox.addItem(d + "일");
        }

        dayComboBox.addActionListener((e) -> {
            String selected = (String) dayComboBox.getSelectedItem();
            if (selected != null) {
                day = Integer.parseInt(selected.replace("일", ""));
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
    // --------------------- 도우미 메서드 ---------------------

    private void updateDayComboBox() {
        if (year == null || month == null) return;

        int days = getDaysInMonth(year, month);

        dayComboBox.removeAllItems();  // 기존 일자 초기화
        for (int d = 1; d <= days; d++) {
            dayComboBox.addItem(d + "일");
        }
    }

    private boolean isLeapYear(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    private int getDaysInMonth(int y, int m) {
        switch (m) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(y) ? 29 : 28;
            default:
                return 0;
        }
    }

    // --------------------- Getter/Setter ---------------------


    public void setYear(Integer year) {
        this.year = year;
        yearComboBox.setSelectedItem(year + "년");
        updateDayComboBox();
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
        monthComboBox.setSelectedItem(month + "월");
        updateDayComboBox();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
        if (dayComboBox.getItemCount() >= day) {
            dayComboBox.setSelectedItem(day + "일");
        }
    }
}
