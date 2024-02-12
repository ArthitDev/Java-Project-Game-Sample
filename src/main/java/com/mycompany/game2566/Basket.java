/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game2566;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Arthit LungYa
 */
public class Basket {

    public int x = 250;
    public int y = 575;
    public int width = 50;

    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 20);
    }

    public void ToLeft() {
        x = x - 20;
    }

    public void ToRight() {
        x = x + 20;
    }
}
