package com.mycompany.game2566;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author Arthit LungYa
 */
public class Bullet implements Runnable {

    public int x = 250;
    public int y = 575;

    public void paint(Graphics g) {
        int[] xPoints = {x, x + 5, x - 5}; // Triangle x-coordinates
        int[] yPoints = {y, y + 20, y + 20}; // Triangle y-coordinates

        g.setColor(Color.red);
        g.fillPolygon(new Polygon(xPoints, yPoints, 3)); // Draw the triangle
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
            }
            y -= 15;
        }
    }
}
