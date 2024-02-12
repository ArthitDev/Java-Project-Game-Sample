/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game2566;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthit LungYa
 */
public class Monster_2 implements Runnable {

    public int s;
    Image image = null;
    int x = 200, y = 0;

    
    public void Ran(){
        this.s = new Random().nextInt(50) + 70;
    }
    
    public Monster_2() {
        image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Arthit LungYa\\Pictures\\meme\\meme_1_2.png");
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, 80, 80, null);
    }

    public void randomXY() {
        this.x = new Random().nextInt(500) + 50;
//        this.y = new Random().nextInt(500)+50;
        y = 30;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(s);
            } catch (Exception ex) {
            }
            Random random = new Random();
            s = random.nextInt(30)+20;
            y = y + 10;
            if (y > 550) {
                randomXY();
            }
        }
    }
}
