package com.snek.dangernoodle;

import javax.swing.*;
import java.awt.*;

public class Food {
    private Image image;
    private int x;
    private int y;

    public Food(int position, int size) {
        move(position, size);
        updateImage();
    }

    public void updateImage(){
        this.image = getRandomImage();
    }

    private Image getRandomImage(){
        return new ImageIcon(FoodImage.getRandomImage().toString()).getImage();
    }

    public void draw(Graphics g, NopeRope imageObserver) {
        g.drawImage(this.image, this.x, this.y, imageObserver);
    }

    public void move(int position, int size){
        int r = (int) (Math.random() * position);
        this.x = ((r * size));

        r = (int) (Math.random() * position);
        this.y = ((r * size));
    }

    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
}
