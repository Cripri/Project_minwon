package function;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class FileSaveExample {


    public static void saveToFile(String data) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("파일 저장");

        // 확장자 필터 추가 (선택사항)
        fileChooser.setFileFilter(new FileNameExtensionFilter("텍스트 파일 (*.txt)", "txt"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // 확장자 자동 추가
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try (FileWriter writer = new FileWriter(fileToSave)) {
                writer.write(data);
                JOptionPane.showMessageDialog(null, "파일이 저장되었습니다: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "파일 저장 중 오류 발생");
                e.printStackTrace();
            }
        }
    }
}
