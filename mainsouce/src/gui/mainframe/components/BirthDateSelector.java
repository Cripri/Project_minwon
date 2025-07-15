package gui.mainframe.components;

import javax.swing.*;
import java.awt.*;

public class BirthDateSelector {
   
   String year;
   String month;
   String day;

    public JPanel getBirthDatePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(217, 217, 217));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        // 년도 콤보박스
        JComboBox<String> yearComboBox = new JComboBox<>();
        yearComboBox.setBackground(Color.WHITE);
        for (int year = 2025; year >= 1926; year--) {
            yearComboBox.addItem(year + "년");
        }
        
        yearComboBox.addActionListener((e) -> {
            String selected = (String) yearComboBox.getSelectedItem();
            if (selected != null) {
                year = selected.replace("년", "");
            }
        });

        // 월 콤보박스
        JComboBox<String> monthComboBox = new JComboBox<>();
        monthComboBox.setBackground(Color.WHITE);
        for (int month = 1; month <= 12; month++) {
            monthComboBox.addItem(month + "월");
        }
        
        monthComboBox.addActionListener((e) -> {
            String selected = (String) monthComboBox.getSelectedItem();
            if (selected != null) {
                month = selected.replace("월", "");
            }
        });

        // 일 콤보박스
        JComboBox<String> dayComboBox = new JComboBox<>();
        dayComboBox.setBackground(Color.WHITE);
        for (int day = 1; day <= 31; day++) {
            dayComboBox.addItem(day + "일");
        }
        
        dayComboBox.addActionListener((e) -> {
            String selected = (String) dayComboBox.getSelectedItem();
            if (selected != null) {
                day = selected.replace("일", "");
            }
        });

        panel.add(yearComboBox);
        panel.add(monthComboBox);
        panel.add(dayComboBox);

        return panel;
    }
}