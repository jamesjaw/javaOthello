package go.go;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
//棋子
class chress extends JButton {
    int x;
    int y;
    public chress(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean compar(chress i) {
        if(x == i.x && y == i.y) return true;
        else return false;
    }
    public chress add(chress i) {
        int nx = x + i.x;
        int ny = y + i.y;
        return new chress(nx, ny);
    }
    public chress sub(chress i) {
        int nx = x - i.x;
        int ny = y - i.y;
        return new chress(nx, ny);
    }
}

//位置
class loca {
    int x;
    int y;

    loca() {
        x = 0;
        y = 0;
    }
    loca(int x, int y) {
        this.x = x;
        this.y = y;
    }
    boolean eq(loca in) {
        return x == in.x && y == in.y;
    }

    loca add(loca in) {
        loca p;
        int xx = x + in.x;
        int yy = y + in.y;
        p = new loca(xx, yy);
        return p;
    }
}

//邏輯棋盤
class broad extends JFrame {
    static loca[] directions = new loca[] {
            new loca(-1, -1), new loca(-1,0), new loca(-1, 1),
            new loca(0, -1)   /*  center */       , new loca(0, 1),
            new loca(1, -1),  new loca(1, 0), new loca(1, 1)};

    //boolean youTurn;
    loca pick = new loca(0,0);
    JLabel nowPick = new JLabel();

    int[][] aBroad = new int[8][8];
    chress [][] display = new chress[8][8];
    JButton enter = new JButton("列隊等待");
    JButton restart = new JButton("重新一局");
    JLabel info = new JLabel();
    JLabel waitingTime = new JLabel();
    int[] disc_count = new int[] {60, 2, 2};
    int player = 1;
    int cur_player = 1;
    int watingMode = 0;

    ImageIcon w = new ImageIcon("./dir1/w.png");
    ImageIcon b = new ImageIcon("./dir1/b.png");

    InetAddress myIP;
    InetAddress opIP;

    Vector<loca> next_valid_spots;


    ServerSocket sever;
    Socket socket;

    public broad(int[][] a) {
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                aBroad[i][j] = a[i][j];
                disc_count[aBroad[i][j]]++;
            }
        }
        cur_player = player;
        next_valid_spots = get_valid_spots();

    }
    public broad(broad old) {
        disc_count = new int[3];
        aBroad = new int[8][8];
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                aBroad[i][j] = old.aBroad[i][j];
                disc_count[aBroad[i][j]]++;
            }
        }
        disc_count[0] = old.disc_count[0];
        disc_count[1] = old.disc_count[1];
        disc_count[2] = old.disc_count[2];
        cur_player = old.cur_player;
        next_valid_spots = new Vector<>();

    }

    public broad() {
        super("Othello");
        restart.setBounds(230 ,10,100,30);
        add(restart);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initNewBroad();
            }
        });
        info.setText("Black go first");
        info.setBounds(60,10,350,30);
        add(info);

        nowPick.setText("[ - . - ]");
        nowPick.setBounds(330, 9 ,70, 30);
        add(nowPick);

        waitingTime.setText("");
        waitingTime.setBounds(455, 9, 50,30);
        add(waitingTime);

        enter.setBounds(375, 10, 80,30);
        //Todo
        //add(enter);

        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                aBroad[i][j] = 0;
                display[i][j] = new chress(i, j);
                display[i][j].setText("");
                display[i][j].setIcon(null);
                display[i][j].setBounds(50*(j+1), 50*(i+1), 50, 50);
                add(display[i][j]);
            }
        }
        //init for display and game backend
        display[3][3].setIcon(w);display[3][4].setIcon(b);
        display[4][3].setIcon(b);display[4][4].setIcon(w);
        aBroad[3][3] = 2;aBroad[3][4] = 1;
        aBroad[4][3] = 1;aBroad[4][4] = 2;
        next_valid_spots = get_valid_spots();
        hit(next_valid_spots, true);
        //setting flame
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++){
                //why need finalI instead of use i ?
                int finalI = i;
                int finalJ = j;
                display[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //這是啥？為啥能用到外部變數
                        setloca(finalI, finalJ);

                        int x = finalI + 1;
                        char y = (char)(finalJ + 65);
                        nowPick.setText("[ " + x + " . " + y + " ]");
                        //nowPick.setText("[ " + finalI + " . " + finalJ + " ]");
                        hit(next_valid_spots, false);


                        info.setText(" ");
                        loca pick = getloca();
                        System.out.println(pick.x + "." + pick.y);
                        if(is_spot_valid(pick)) {
                            System.out.println("allow");
                            put_disc(pick);
                        }
                        else {
                            System.out.println("not allow");
                        }

                        if(disc_count[0] == 0) con();
                        next_valid_spots = get_valid_spots();

                        if(next_valid_spots.size() == 0) {
                            cur_player = 3 - cur_player;
                            next_valid_spots = get_valid_spots();
                        }
                        hit(next_valid_spots, true);
                        String temp = cur_player==1?"B":"W";
                        info.setText("B:"+disc_count[1]+" | W:"+disc_count[2]+" | cur_player : " + temp);
                    }
                });
            }
        }
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(watingMode == 0) {
                    watingMode = 1;
                    inToWaiting();
                }
                else if(watingMode == 1) {
                    watingMode = 0;
                    outOfWaiting();
                }
            }
        });
        //test
//        test();
//        test2(next_valid_spots);
    }

    private void inToWaiting() {
        //拿到自己的ip
        try {
            myIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println(e.toString());
        }

        try {
            sever = new ServerSocket(8787);
            socket = sever.accept();
            System.out.println("find opp and can play online");

            sever.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void outOfWaiting() {
        if(!sever.isClosed()) {
            try {
                sever.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }

    }

    private void setloca (int x, int y) {
        pick.x = x;
        pick.y = y;
    }
    public loca getloca() {
        return pick;
    }
    public void setdisc(loca p, int player) {
//        String c;
//        if(player == 0) c = "";
//        else if(player == 1) c = "O";
//        else if(player == 2) c = "X";
//        else c = "?";
//        display[p.x][p.y].setText(c);
        if(player == 0) display[p.x][p.y].setIcon(null);
        else if(player == 1) display[p.x][p.y].setIcon(b);
        else if(player == 2) display[p.x][p.y].setIcon(w);
    }

    //該位置合法性
    boolean is_spot_valid(loca center) {
        //System.out.println(get_disc(center) );
        //System.out.println(cur_player);
        if (get_disc(center) != 0) {
            return false;
        }
        for (loca dir: directions) {
            //System.out.println("dir:x"+dir.x+"y"+dir.y);
            // Move along the direction while testing.
            loca p = center.add(dir);
            //System.out.println("dir:x"+p.x+"y"+p.y);
            if (!is_disc_at(p, get_next_player(cur_player))) continue;
            p = p.add(dir);
            /// check is other side have my chest
            while (is_spot_on_board(p) && get_disc(p) != 0) {
                if (is_disc_at(p, cur_player)) return true;
                p = p.add(dir);
            }
        }
        return false;
    }

    int get_next_player(int x) {
        return 3 - x;
    }
    boolean is_spot_on_board(loca p) {
        return 0 <= p.x && p.x < 8 && 0 <= p.y && p.y < 8;
    }
    int get_disc(loca p) {
        return aBroad[p.x][p.y];
    }
    void set_disc(loca p, int disc) {
        aBroad[p.x][p.y] = disc;
    }
    boolean is_disc_at(loca p, int disc) {
        if (!is_spot_on_board(p))
            return false;
        if (get_disc(p) != disc)
            return false;
        return true;
    }
    //反轉敵旗
    void flip_discs(loca center) {
        for (loca dir: directions) {
            // Move along the direction while testing.
            loca p = center.add(dir);
            if (!is_disc_at(p, get_next_player(cur_player)))
                continue;
            Vector<loca> discs = new Vector<>();
            discs.add(p);
            p = p.add(dir);
            while (is_spot_on_board(p) && get_disc(p) != 0) {
                if (is_disc_at(p, cur_player)) {
                    for (loca s: discs) {
                        set_disc(s, cur_player);
                        setdisc(s, cur_player);
                    }
                    disc_count[cur_player] += discs.size();
                    disc_count[get_next_player(cur_player)] -= discs.size();
                    break;
                }
                discs.add(p);
                p = p.add(dir);
            }
        }
    }

    Vector<loca> get_valid_spots() {
        Vector<loca> valid_spots = new Vector<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                loca p = new loca(i, j);
                if (aBroad[i][j] != 0)
                    continue;
                if (is_spot_valid(p))
                    valid_spots.add(p);
            }
        }
        return valid_spots;
    }

    boolean put_disc(loca p) {
        if(!is_spot_valid(p)) {
            return false;
        }
        //
        set_disc(p, cur_player);
        setdisc(p, cur_player);
        //
        disc_count[cur_player]++;
        disc_count[0]--;
        flip_discs(p);
        // Give control to the other player.
        cur_player = get_next_player(cur_player);
        // find where can put chese
        next_valid_spots = get_valid_spots();
        return true;
    }
    //hit display
    void hit(Vector<loca> z, boolean vis) {
        String hi;
        if(vis) hi = new String(".");
        else hi = new String("");

        for(loca h : z) {
            display[h.x][h.y].setText(hi);
        }
    }
    //輸出看一下邏輯的棋盤
    void test() {
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                System.out.print(aBroad[i][j]);
            }
            System.out.println();
        }
    }
    void test2(Vector<loca> z) {
        for(loca a : z) {
            System.out.print(" " + a.x + "." + a.y);
        }
        System.out.println();
    }
    //計算結果
    void con() {
        if(disc_count[1] > disc_count[2]) {
            JOptionPane.showMessageDialog(null, "black win\nB:"+disc_count[1]+"/W:"+disc_count[2]);
        }
        else if(disc_count[2] > disc_count[1]) {
            JOptionPane.showMessageDialog(null, "white win\nB:"+disc_count[1]+"/W:"+disc_count[2]);
        }
        else {
            JOptionPane.showMessageDialog(null, "Tie\nB:"+disc_count[1]+"/W:"+disc_count[2]);
        }
        restart.setText("再來一局");
    }
    //重新一局init broad
    void initNewBroad() {
        for(int i=0;i<8;i++) {
            for(int j = 0; j < 8; j++) {
                aBroad[i][j] = 0;
                display[i][j].setText("");
                display[i][j].setIcon(null);
            }
        }
        cur_player = 1;
        disc_count[0] = 60;
        disc_count[1] = 2;
        disc_count[2] = 2;
        info.setText("O go first");
        display[3][3].setIcon(w);display[3][4].setIcon(b);
        display[4][3].setIcon(b);display[4][4].setIcon(w);
        aBroad[3][3] = 2;aBroad[3][4] = 1;
        aBroad[4][3] = 1;aBroad[4][4] = 2;
        next_valid_spots = get_valid_spots();
        hit(next_valid_spots, true);
        restart.setText("restart");
    }
}

public class Main {

    static public void main(String[] args) {
        broad gameBroad = new broad();
    }
}
