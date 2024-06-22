package common;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
	private Image backgroundImage;

	public BackgroundPanel(String fileName) {
		backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
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
