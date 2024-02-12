/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.game2566;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;

/**
 *
 * @author Arthit LungYa
 */
public class BallTest2 extends javax.swing.JFrame implements Runnable, KeyListener {

    /**
     * Creates new form BallTest2
     */
    Clip clip;
    Clip clip2;
    Clip clip3;
    Clip clip4;
    Clip clip5;
    Clip clip6;
    Monster monster;
    Monster_2 monster_2;
    Basket basket;
    Ball[] ball = new Ball[5];
    Bullet[] bullet = {null, null, null, null, null};
    Bonus bonus;
//    Bullet bullet;
    public int y = 35;
    public int x = 10;
    boolean play = true;
    public int score = 0;
    private BufferedImage buffer;
    private JButton resetButton;
    private JButton exitButton;
    private boolean paused = false;
    private String pauseMessage = "Paused";
    public BallTest2() {
        initComponents();
        setTitle("เกมรับบอล");
        createBufferStrategy(2); /// แก้กระพริบ
        BufferStrategy bs = getBufferStrategy(); /// แก้กระพริบ
        Graphics g = bs.getDrawGraphics(); /// แก้กระพริบ
        super.setSize(600, 600);
        new Thread(this).start();
        this.addKeyListener(this);
        for (int i = 0; i < ball.length; i++) {
            ball[i] = new Ball(getHeight());
            new Thread(ball[i]).start();
        }
        bonus = new Bonus();
        monster = new Monster();
        monster_2 = new Monster_2();
        basket = new Basket();
//        bullet = new Bullet();
        new Thread(monster).start();
        new Thread(monster_2).start();
        new Thread(bonus).start();
        createSound();
        g.dispose(); /// แก้กระพริบ
        bs.show(); /// แก้กระพริบ

        resetButton = new JButton("Reset");
        resetButton.setBounds(250, 300, 100, 40);
        resetButton.setBackground(Color.PINK);
        resetButton.setForeground(Color.RED);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        exitButton = new JButton("Exit");
        exitButton.setBounds(250, 350, 100, 40);
        exitButton.setBackground(Color.pink);
        exitButton.setForeground(Color.red);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application
            }
        });

        // Add mouse listener for hover effect
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetButton.setBackground(Color.RED);
                resetButton.setForeground(Color.PINK);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetButton.setBackground(Color.PINK);
                resetButton.setForeground(Color.RED);
            }
        });
        resetButton.setVisible(false);
        add(resetButton);

        // Add mouse listener for hover effect
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(Color.BLUE);
                exitButton.setForeground(Color.YELLOW);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(Color.YELLOW);
                exitButton.setForeground(Color.BLUE);
            }
        });
        exitButton.setVisible(false);
        add(exitButton);
    }

    public void createSound() {
        try {
            File file = new File("C:\\Users\\Arthit LungYa\\Downloads\\Video\\for_java_2.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volumeLevel = -10.0f;
            volumeControl.setValue(volumeLevel);
            clip.start();
        } catch (Exception ex) {
        }
        try {
            File file = new File("C:\\Users\\Arthit LungYa\\Downloads\\Music\\I Got U Homie Sound Effect (HD).wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip2 = AudioSystem.getClip();
            clip2.open(audioIn);
//            clip.start();
        } catch (Exception ex) {
        }
        try {
            File file = new File("C:\\Users\\Arthit LungYa\\Downloads\\Music\\Game Over sound effect.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip3 = AudioSystem.getClip();
            clip3.open(audioIn);
//            clip.start();
        } catch (Exception ex) {
        }
        try {
            File file = new File("C:\\Users\\Arthit LungYa\\Downloads\\Music\\Mario Coin Sound - Sound Effect (HD).wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip4 = AudioSystem.getClip();
            clip4.open(audioIn);
//            clip.start();
        } catch (Exception ex) {
        }
        try {
            File file = new File("C:\\Users\\Arthit LungYa\\Downloads\\Music\\Bullet-Sound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip5 = AudioSystem.getClip();
            clip5.open(audioIn);
//            clip.start();
        } catch (Exception ex) {
        }
        try {
            File file = new File("C:\\Users\\Arthit LungYa\\Downloads\\Music\\slap.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip6 = AudioSystem.getClip();
            clip6.open(audioIn);
//            clip.start();
        } catch (Exception ex) {
        }
    }

    public void decreaseScore() {
        score -= 1;
        System.out.println("ลด : " + score);
        if (score >= 10) {
            score -= 3;
        }
    }

    public void increaseScore() {
        score += 1;
        System.out.println("เพิ่ม : " + score);
        if (score == score++) {
            clip4.loop(1);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (play) {
            g.drawImage(buffer, 0, 0, this);
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.red);
            Font scoreFont = new Font("Arial", Font.PLAIN, 30);
            g.setFont(scoreFont);
            String scoreText = "Score: " + score;
            int x = 20;
            int y = 70;
            g.drawString(scoreText, x, y);

            // Calculate the bounding box for the text
            FontMetrics metrics = g.getFontMetrics(scoreFont);
            Rectangle textBounds = new Rectangle(x, y - metrics.getAscent(), metrics.stringWidth(scoreText), metrics.getHeight());

            // Draw a rectangular border around the score text
            ((Graphics2D) g).setColor(Color.black); // You can set a different color if needed
            ((Graphics2D) g).drawRect(textBounds.x - 5, textBounds.y - 5, textBounds.width + 10, textBounds.height + 10);
            g.drawString(scoreText, 20, 70);
            for (Ball currentBall : ball) {
                currentBall.paint(g);
            }
            monster.paint(g);
            monster_2.paint(g);
            basket.paint(g);
            bonus.paint(g);
            for (int i = 0; i < bullet.length; i++) {
                if (bullet[i] != null) {
                    bullet[i].paint(g);
                }
            }
        } else {
            String message = "Game Over";
            Font font = new Font("Arial", Font.BOLD, 30);
            g.setFont(font);
            g.setColor(Color.RED);
            FontMetrics fm = g.getFontMetrics();
            int messageWidth = fm.stringWidth(message);
            int xCoordinate = (getWidth() - messageWidth) / 2;
            int yCoordinate = getHeight() / 2;
            g.drawString(message, xCoordinate, yCoordinate);
        }
    }

    @Override /// แก้กระพริบ
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception ex) {
            }
            if (!paused) {
                checkWall();
                checkWall2();
                checkWall3();
                if (score < 0) {
                    play = false;
                    score = 0;
                    clip.stop();
                    clip2.stop();
                    resetButton.setVisible(true);
                    exitButton.setVisible(true);
                }
                repaint();
            } else {
                clip3.loop(1);
            }
        }
    }

    private void checkWall() {
        Rectangle rBasket = new Rectangle(basket.x, basket.y, 50, 50);
        Rectangle rMonster = new Rectangle(monster.x, monster.y, 50, 50);
        if (rBasket.intersects(rMonster)) {
            decreaseScore();
            clip2.loop(1);
            monster = new Monster();
            new Thread(monster).start();
        } else {
            Rectangle rMonster_2 = new Rectangle(monster_2.x, monster_2.y, 50, 50);
            if (rBasket.intersects(rMonster_2)) {
                decreaseScore();
                clip2.loop(1);
                monster_2 = new Monster_2();
                new Thread(monster_2).start();
            }
        }
    }

    private void checkWall2() {
        Rectangle rBasket = new Rectangle(basket.x, basket.y, 55, 50);
        for (int i = 0; i < ball.length; i++) {
            Rectangle rBall = new Rectangle(ball[i].x, ball[i].y, 50, 50);
            if (rBasket.intersects(rBall)) {
                increaseScore();
                ball[i] = new Ball(getHeight());
                new Thread(ball[i]).start();
                break;
            }
        }
    }

    private void checkWall3() {
        for (int i = 0; i < bullet.length; i++) {
            if (bullet[i] != null) {
                Rectangle rBullet = new Rectangle(bullet[i].x, bullet[i].y, 50, 50);
                Rectangle rMonster = new Rectangle(monster.x, monster.y, 50, 50);
                if (rBullet.intersects(rMonster)) {
                    bullet[i] = null;
                    monster = new Monster();
                    new Thread(monster).start();
                    clip6.loop(1);
                    break;
                }
                Rectangle rMonster_2 = new Rectangle(monster_2.x, monster_2.y, 50, 50);
                if (rBullet.intersects(rMonster_2)) {
                    bullet[i] = null;
                    monster_2 = new Monster_2();
                    new Thread(monster_2).start();
                    clip6.loop(1);
                    break;
                }
            }
        }
    }

    private void resetGame() {
        play = true;
        score = 0;
        clip3.setFramePosition(0);
        clip.setFramePosition(0);
        clip.start();
        clip3.stop();
        resetButton.setVisible(false);
        exitButton.setVisible(false);
        basket.x = 0;
        repaint();
        this.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BallTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BallTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BallTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BallTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BallTest2().setVisible(true);
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (play) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                paused = !paused;
            }
            if (e.getKeyCode() == 32) {
                clip5.loop(1);
                for (int i = 0; i < bullet.length; i++) {
                    if (bullet[i] == null) {
                        bullet[i] = new Bullet();
                        bullet[i].x = basket.x + basket.width / 2;
                        bullet[i].y = basket.y;
                        new Thread(bullet[i]).start();
                        break;
                    }
                    if (bullet[i].y < 0) {
                        bullet[i] = null;
                        break;
                    }
                }
            }
            if (e.getKeyCode() == 37) {
                basket.ToLeft();
            }
            if (e.getKeyCode() == 39) {
                basket.ToRight();
            }
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
