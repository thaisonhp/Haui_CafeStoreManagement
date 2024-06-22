package view;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;

class ChangeBackground extends JPanel {
    private Image backgroundImage;

    public ChangeBackground(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

