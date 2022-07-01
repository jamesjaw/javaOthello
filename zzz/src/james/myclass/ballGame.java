package james.myclass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ballGame extends JFrame {
    private myPanel mP;

    public ballGame() {
        setLayout(new BorderLayout());



        setSize(640,480);

        mP = new myPanel();
        add(mP, BorderLayout.CENTER);
        setVisible(true);


        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private class myPanel extends JPanel {
        BufferedImage ball;
        public myPanel() {
            setBackground(Color.YELLOW);
            try {
                ball = ImageIO.read(new File("dir1/ball2.png"));
            } catch (Exception e) {System.out.println("somewhere wrong");}
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(ball, 100,100 ,null);
        }
    }

    public static void main(String[] args) {
        new ballGame();
    }
}
