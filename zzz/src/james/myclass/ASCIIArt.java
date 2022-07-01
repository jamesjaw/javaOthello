package james.myclass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ASCIIArt extends JFrame {
    JPanel top, center;
    JButton pickFile, change, saveFile;
    ASCIIArt() {
        setVisible(true);

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        top = new JPanel();
        add(top, BorderLayout.NORTH);

        pickFile = new JButton("選擇檔案");
        change = new JButton("轉換");
        saveFile = new JButton("儲存檔案");
        top.add(pickFile);
        top.add(change);
        top.add(saveFile);
        setSize(640, 480);
        center = new JPanel();
        center.setBackground(Color.pink);
        center.setSize(100, 100);
        add(center, BorderLayout.CENTER);



        pickFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
                try {
                    BufferedImage img = ImageIO.read(new File("./dir1/mypaint"));
                    JLabel JG = new JLabel(new ImageIcon(img));
                    center.add(JG);
                    //Graphics G = img.getGraphics();
                    //center.paint(G);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics G = center.getGraphics();

            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage img = new BufferedImage(center.getWidth(), center.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics G = img.createGraphics();
                center.paint(G);

                try {
                    ImageIO.write(img, "jpg",new File("./dir1/ascii"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                center.repaint();
            }
        });
    }

    public static void main(String[] args) {
        new ASCIIArt();
    }

}
