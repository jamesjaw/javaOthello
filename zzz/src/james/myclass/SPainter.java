package james.myclass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

//外部class 只有沒特別講或是public
class SPanel extends JPanel {
    private LinkedList<HashMap<String,Integer>> line;
    private Vector<Point> line2;
    LinkedList<Vector<Point>> line2s;
    LinkedList<Vector<Point>> templine;
    int flag = 0;
    //Graphics2D GG;
    int xx;
    int yy;
    private class Mouse extends MouseAdapter {
        int x;
        int y;
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println(e.getX() + " . " + e.getY());
            //HashMap<String, Integer> point = new HashMap<>();
            //point.put("x", e.getX());
            //point.put("y", e.getY());
            //line.add(point);
            xx = e.getX();
            yy = e.getY();

            templine.clear();

            Point p = new Point(e.getX(), e.getY());
            Vector<Point> line22 = new Vector<>();
            line22.add(p);
            line2s.add(line22);
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("X:" + e.getX() + " . Y:" + e.getY());
            //GG.drawLine(0, 0, 10 ,500);
            xx = e.getX();
            yy = e.getY();
            Point p = new Point(e.getX(), e.getY());
            line2s.getLast().add(p);

            //line2.add(p);
            repaint();
        }
    }

    public SPanel() {
        setBackground(Color.PINK);
        line = new LinkedList<>();
        line2 = new Vector<>();
        line2s = new LinkedList<>();
        templine = new LinkedList<>();
        Mouse myM = new Mouse();
        addMouseListener(myM);
        addMouseMotionListener(myM);
        //setSize(600, 400);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D G = (Graphics2D) g;
        G.setColor(Color.gray);
        G.setStroke(new BasicStroke(3));
        for(var v : line2s) {
            for(int i=1;i<v.size();i++) {
                G.drawLine(  v.elementAt(i -1).x,v.elementAt(i -1).y,v.elementAt(i).x,v.elementAt(i).y);
            }
        }
    }

    public void saveF(File name) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(name)));
        out.writeObject(line2s);
        out.flush();
        out.close();
    }

    public  void loadF(File name) throws  Exception {
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(name)));
        line2s = (LinkedList<Vector<Point>>) in.readObject();
        in.close();
    }

}

//=======================================================


public class SPainter extends JFrame implements ActionListener{
    private JButton cleaner;
    private JButton undo;
    private JButton redo;
    private SPanel myPanel;
    private JButton save,load;

    SPainter() {
        super("Signer");
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout());
        save = new JButton("存檔");
        load = new JButton("載入");
        cleaner = new JButton("清除");
        undo = new JButton("上一步");
        redo = new JButton("下一步");
        add(top, BorderLayout.NORTH);
        top.add(cleaner);
        top.add(undo);
        top.add(redo);
        top.add(save);
        top.add(load);


        setSize(640, 480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        myPanel = new SPanel();
        add(myPanel, BorderLayout.CENTER);

        setListener();
    }

    private void setListener() {
        cleaner.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
    }



    static public void main(String[] args) {
        new SPainter();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "清除") {
            myPanel.flag = 1;
            myPanel.templine = myPanel.line2s;
            myPanel.line2s = new LinkedList<>();
            myPanel.repaint();
        }
        else if(e.getActionCommand() == "上一步") {
            if(myPanel.line2s.size() > 0) {
                myPanel.templine.add(myPanel.line2s.getLast());
                myPanel.line2s.removeLast();
                myPanel.repaint();
            }
        }
        else if(e.getActionCommand() == "下一步") {
            myPanel.line2s.add(myPanel.templine.getLast());
            myPanel.templine.removeLast();
            myPanel.repaint();
        }

        else if(e.getActionCommand() == "存檔") {
            JFileChooser f = new JFileChooser();

            try {
                myPanel.saveF(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getActionCommand() == "載入") {
            try {
                myPanel.loadF(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveJPG() {

        BufferedImage img = new BufferedImage(myPanel.getWidth(), myPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gra = img.createGraphics();
        myPanel.paint(gra);

        try {
            ImageIO.write(img, "jpg", new File("./dir1/mypaint"));
        }
        catch (Exception e) {
            System.out.println("save wrong");
        }

    }


}
