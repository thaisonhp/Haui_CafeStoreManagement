package common;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        // Sử dụng getClass().getResource để lấy đường dẫn ảnh
//        URL imageUrl = getClass().getResource(fileName);
        ImageIcon icon = new ImageIcon("/Users/luongthaison/Desktop/cafe.jpeg"); // Đường dẫn đến file ảnh nền
        Image backgroundImage = icon.getImage();
        if (imageUrl != null) {
            backgroundImage = new ImageIcon(imageUrl).getImage();
        } else {
            // Xử lý trường hợp ảnh không tồn tại hoặc đường dẫn không đúng
            System.err.println("Image not found! Check the path: " + fileName);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Lấy kích thước của JPanel
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Lấy kích thước của ảnh
            int imageWidth = backgroundImage.getWidth(null);
            int imageHeight = backgroundImage.getHeight(null);

            // Vẽ ảnh chỉ nửa bên phải của JPanel
            g.drawImage(backgroundImage, panelWidth / 2, 0, panelWidth, panelHeight, imageWidth / 2, 0, imageWidth,
                    imageHeight, this);
        }
    }
}
