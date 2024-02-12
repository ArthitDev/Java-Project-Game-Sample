package com.mycompany.game2566;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball implements Runnable {

    public int x = 50;
    public int y = 50;
    public int s;
    public int size;  // เพิ่มขนาดของลูกบอล
    public Color color;  // เพิ่มสีของลูกบอล
    int h;

    public Ball(int h) {
        this.h = h;
        this.size = 50;  // ขนาดเริ่มต้นของลูกบอล
        this.color = Color.red;  // สีเริ่มต้นของลูกบอล
    }

    public void Ran() {
        this.s = new Random().nextInt(20) + 50;
    }

    public void paint(Graphics g) {
        g.setColor(color);  // ใช้สีที่กำหนด
        g.fillOval(x, y, size, size);  // ให้ขนาดของลูกบอลเป็น size
    }

    @Override
    public void run() {
        while (true) {
            while (y < h - size - 10) {
                try {
                    Thread.sleep(s);
                } catch (Exception ex) {
                }
                y = y + 10;
            }
            Random random = new Random();
            x = random.nextInt(600);
            s = random.nextInt(50) + 15;
            y = 0;
//            size = random.nextInt(50) + 30;  // เปลี่ยนขนาดของลูกบอลเมื่อลูกบอลเคลื่อนที่ไปที่ด้านบน
            color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));  // สุ่มสีใหม่
        }
    }
}
