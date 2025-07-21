package function.pdfwriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PDFViewerFromPath extends JFrame {

    public PDFViewerFromPath(String pdfFilePath) {
        super("PDF Viewer");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);

        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFRenderer renderer = new PDFRenderer(document);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // 모든 페이지 렌더링
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 150); // 150 DPI
                JLabel label = new JLabel(new ImageIcon(image));
                panel.add(label);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            getContentPane().add(scrollPane);
            setVisible(true);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    // 실행 예시
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new PDFViewerFromPath("sample.pdf"));
//    }
}