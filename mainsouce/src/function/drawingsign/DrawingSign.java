package function.drawingsign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class DrawingSign extends JFrame {

    private final float strokeWidth = 14f;  // 원하는 펜 굵기

    public DrawingSign() {
        setTitle("Sign");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        DrawingPanel drawingPanel = new DrawingPanel(strokeWidth);

        JButton clearButton = new JButton("초기화");
        clearButton.addActionListener(e -> drawingPanel.clearDrawing());

        JButton saveButton = new JButton("투명 배경 저장");
        saveButton.addActionListener(e -> drawingPanel.saveDrawingWithTransparentBackground());

        JPanel controls = new JPanel();
        controls.add(clearButton);
        controls.add(saveButton);

        setLayout(new BorderLayout());
        add(drawingPanel, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    class DrawingPanel extends JPanel {
        private List<List<Point>> paths = new ArrayList<>();
        private float strokeWidth;

        public DrawingPanel(float strokeWidth) {
            this.strokeWidth = strokeWidth;
            setOpaque(false); // 패널 자체를 투명하게
            setBackground(new Color(0,0,0,0));

            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<Point> newPath = new ArrayList<>();
                    newPath.add(e.getPoint());
                    paths.add(newPath);
                    repaint();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (!paths.isEmpty()) {
                        paths.get(paths.size() - 1).add(e.getPoint());
                        repaint();
                    }
                }
            };
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 600);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create(); // 안전하게 copy

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            for (List<Point> path : paths) {
                for (int i = 1; i < path.size(); i++) {
                    Point p1 = path.get(i - 1);
                    Point p2 = path.get(i);
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
            g2.dispose();
        }

        public void clearDrawing() {
            paths.clear();
            repaint();
        }

        public void saveDrawingWithTransparentBackground() {
            try {
                BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();

                // 배경 투명하게 초기화
                g2d.setComposite(AlphaComposite.Clear);
                g2d.fillRect(0, 0, 800, 600);

                // 그림 그리기
                g2d.setComposite(AlphaComposite.SrcOver);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                for (List<Point> path : paths) {
                    for (int i = 1; i < path.size(); i++) {
                        Point p1 = path.get(i - 1);
                        Point p2 = path.get(i);
                        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }

                g2d.dispose();

                File outputfile = new File("resources/sign/sign.png");
                ImageIO.write(image, "png", outputfile);
                JOptionPane.showMessageDialog(this, "사인 저장 완료: " + outputfile.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "저장 실패!");
            }
        }
    }

//    public static void main(String[] args) {
//        불러올때 사용하는법
//        SwingUtilities.invokeLater(() -> new DrawingSign().setVisible(true));
//    }
}
