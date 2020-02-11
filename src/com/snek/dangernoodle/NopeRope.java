package com.snek.dangernoodle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class NopeRope extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 300;
    private final int SIZE = 10;
    private final int NUM_DOTS = 900;
    private final int POSITION = 23;
    private final int DELAY = 140;

    private final int x[] = new int[NUM_DOTS];
    private final int y[] = new int[NUM_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public NopeRope(){
        initBoard();

    }
    private void initBoard(){

        addKeyListener(new TAdapter());
        setBackground(Color.pink);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        loadImages();
        initGame();
    }
    private void loadImages(){

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    private void initGame(){

        dots = 3;

        for(int z = 0; z < dots; z++){
            x[z]=50-z*10;
            y[z]=50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g){
        if (inGame){

            g.drawImage(apple, apple_x, apple_y, this);

            for(int z = 0; z<dots; z++){
                if (z==0){
                    g.drawImage(head, x[z], y[z], this);
                }else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else{
            gameOver(g);
        }
    }

    private void gameOver(Graphics g){
        String msg ="Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.PINK);
        g.setFont(small);
        g.drawString(msg, (BOARD_WIDTH-metr.stringWidth(msg))/2, BOARD_HEIGHT/2);

    }
    private void checkApple(){
        if((x[0]==apple_x) && (y[0] == apple_y)){
            dots++;
            locateApple();
        }
    }

    private void move(){
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z-1)];
            y[z] = y[(z-1)];
        }

        if(left){
            x[0] -= SIZE;
        }

        if(right){
            x[0] += SIZE;
        }

        if(up){
            y[0] -= SIZE;
        }

        if(down){
            y[0] += SIZE;
        }
    }

    private void checkCollision(){
        for (int z = dots; z > 0; z--){
            if ((z>4) && (x[0] == x[z]) && (y[0] == y[z])){
                inGame = false;
            }
        }
        if (y[0] >= BOARD_HEIGHT){
            inGame = false;
        }
        if(y[0] < 0){
            inGame = false;
        }
        if(x[0] >= BOARD_WIDTH){
            inGame = false;
        }
        if(x[0] < 0){
            inGame = false;
        }
        if(!inGame){
            timer.stop();
        }
    }

    private void locateApple(){
        int r = (int) (Math.random() * POSITION);
        apple_x = ((r * SIZE));

        r = (int) (Math.random() * POSITION);
        apple_y = ((r * SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(inGame){
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    private class  TAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){

            int key = e.getKeyCode();

            if((key == KeyEvent.VK_LEFT) && (!right)){
                left = true;
                up = false;
                down = false;
            }

            if((key == KeyEvent.VK_RIGHT) && (!left)){
                right = true;
                up = false;
                down = false;
            }
            if((key == KeyEvent.VK_UP) && (!down)){
                up = true;
                right = false;
                left = false;
            }
            if((key==KeyEvent.VK_DOWN) && (!up)){
                down = true;
                right = false;
                left = false;
            }
        }
    }
}

