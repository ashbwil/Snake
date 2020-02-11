package com.snek.dangernoodle;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class LeglessLizard extends JFrame {
    public LeglessLizard() {

        initUI();
    }

    private void initUI() {

        add(new NopeRope());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new LeglessLizard();
            ex.setVisible(true);
        });
    }
}
