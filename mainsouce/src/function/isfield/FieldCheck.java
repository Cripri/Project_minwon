package function.isfield;

import javax.swing.*;
import java.awt.*;

public class FieldCheck {
    public static boolean validateFields(Component parent,JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(parent, "빈칸을(를) 채워주세요.", "미입력 오류", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public static boolean validateComboBox(Component parent, JComboBox<?>... comboBox) {
        for(JComboBox<?> jComboBox : comboBox){
            if(jComboBox.getSelectedItem() == null || jComboBox.getSelectedItem().toString().trim().isEmpty()){
                JOptionPane.showMessageDialog(parent, "빈칸을(를) 채워주세요.", "미입력 오류", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
