/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author drzik2
 */
public class Platno extends JComponent {

    private BufferedImage image;

    public Platno() {
        try {
            image = ImageIO.read(new File("Pepega.png"));
        } catch (IOException e) {
            System.out.println("Obrazok nebol najdeny");
        }
    }

    public void nastavObrazok(File file) {
        try {
            image = ImageIO.read(file);
            repaint();
        } catch (IOException e) {
            System.out.println("Obrazok nebol najdeny");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            g.drawImage(image, 40, 0, null);
        }
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        xPoints[0] = 20;
        yPoints[0] = 20;
        xPoints[1] = 30;
        yPoints[1] = 10;
        xPoints[2] = 40;
        yPoints[2] = 20;
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
