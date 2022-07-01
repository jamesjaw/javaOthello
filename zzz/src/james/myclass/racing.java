package james.myclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class racing extends JFrame {
        JButton go;
        JLabel[] lan;
        car[] cars;
        Vector<Integer> rank = new Vector<>();
    public racing() {
        setSize(800, 480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 1));
        go = new JButton("Go");
        add(go);
        lan = new JLabel[8]; //只有做出一個指標陣列
        cars = new car[8];
        for(int i=0;i<8;i++) {
            lan[i] = new JLabel(i + 1 + ".");
            add(lan[i]);
        }

        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go();
                //wait(100000);
            }
        });
    }

    private void go() {
        for(int i=0;i<8;i++) {
            cars[i] = new car(i);
            cars[i].start();
        }
    }

    private class car extends Thread{
        int no;
        StringBuilder s;
        car(int no) {
            this.no = no;
            s = new StringBuilder(".");
        }
        @Override
        public void run() {
            for(long i=0;i<500000000L;i++) {
                if((i % 100000000) == 0) {
                    s.append(">");
                    lan[no].setText(s.toString());
                }
            }
            rank.add(no);
        }
    }

    public static void main(String[] args) {
        new racing();
    }
}
