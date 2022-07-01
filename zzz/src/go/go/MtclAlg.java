
package go.go;

import java.util.Vector;

class myAI {
    static int SIZE = 8;
    static private int[] value_map = {
            500, -35, 10, 5, 5, 10, -35, 500,
            -35, -45, 1, 1, 1, 1, -45, -25,
            10, 1, 3, 2, 2, 3, 1, 10,
            5, 1, 2, 1, 1, 2, 1, 5,
            5, 1, 2, 1, 1, 2, 1, 5,
            10, 1, 3, 2, 2, 3, 1, 10,
            -35, -45, 1, 1, 1, 1, -45, -25,
            500, -35, 10, 5, 5, 10, -35, 500};

    //從可供選擇中選出評分最高的位置

    //evl1:value sheet
    int set_Q_value(broad in) {
        int Q = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (in.aBroad[i][j] == 0) {
                    //do nothing
                } else if (in.aBroad[i][j] == in.player) {
                    if (in.disc_count[0] > 20)
                        Q += value_map[i * 8 + j];
                    else
                        Q += value_map[i * 8 + j];
                } else if (in.aBroad[i][j] == in.get_next_player(in.player)) {
                    if (in.disc_count[0] > 20)
                        Q -= value_map[i * 8 + j];
                    else
                        Q -= value_map[i * 8 + j];
                }

            }
        }
        return Q;
    }

    //evl2:moving ablity
    int move_value(broad in) {
        //紀錄原始玩家
        int o_player = in.cur_player;
        //算我方行動力
        in.cur_player = in.player;
        Vector<loca> me = in.get_valid_spots();
        int me_move = (int) me.size();
        for (loca p : me) {
            if (p.eq(new loca(0, 0)) || p.eq(new loca(0, 7)) || p.eq(new loca(7, 7)) || p.eq(new loca(7, 0))) {
                //me_move += 300;
            }
        }
        //對手行動力
        in.cur_player = in.get_next_player(in.player);
        Vector<loca> you = in.get_valid_spots();
        int you_move = (int) me.size();
        for (loca p : you) {
            if (p.eq(new loca(0, 0)) || p.eq(new loca(0, 7)) || p.eq(new loca(7, 7)) || p.eq(new loca(7, 0))) {
                //me_move += 300;
            }
        }
        //回復原始的player
        in.cur_player = o_player;

        return me_move - you_move;
    }

    //evl3:stable
    int stable_value(broad in) {
        int s_value = 0;
        if (in.aBroad[0][0] == in.player) {
            s_value++;
            loca p1 = new loca(0, 0);
            loca L1 = new loca(0, 1);
            loca L2 = new loca(1, 0);
            loca p2 = new loca(0, 0);

            p1 = p1.add(L1);
            while (in.is_spot_on_board(p1)) {
                if (in.aBroad[p1.x][p1.y] == in.player)
                    s_value++;
                else
                    break;
                p1 = p1.add(L1);
            }
            /*
            p2 = p2 + L2;
            while(is_spot_on_board(p2)){
                if(board[p2.x][p2.y]==player)
                    s_value++;
                else
                    break;
                p2 = p2 + L2;
            }
            */
        }
        if (in.aBroad[0][7] == in.player) {
            s_value++;
            loca p1 = new loca(0, 7);
            loca L1 = new loca(0, -1);
            loca L2 = new loca(1, 0);
            loca p2 = new loca(0, 7);

            p1 = p1.add(L1);
            while (in.is_spot_on_board(p1)) {
                if (in.aBroad[p1.x][p1.y] == in.player)
                    s_value++;
                else
                    break;
                p1 = p1.add(L1);
            }
            /*
            p2 = p2 + L2;
            while(is_spot_on_board(p2)){
                if(board[p2.x][p2.y]==player)
                    s_value++;
                else
                    break;
                p2 = p2 + L2;
            }
            */
        }
        if (in.aBroad[7][7] == in.player) {
            s_value++;
            loca p1 = new loca(7, 7);
            loca L1 = new loca(0, -1);
            loca L2 = new loca(-1, 0);
            loca p2 = new loca(7, 7);

            p1 = p1.add(L1);
            while (in.is_spot_on_board(p1)) {
                if (in.aBroad[p1.x][p1.y] == in.player)
                    s_value++;
                else
                    break;
                p1 = p1.add(L1);
            }
            /*
            p2 = p2 + L2;
            while(is_spot_on_board(p2)){
                if(board[p2.x][p2.y]==player)
                    s_value++;
                else
                    break;
                p2 = p2 + L2;
            }
            */
        }
        if (in.aBroad[7][0] == in.player) {
            s_value++;
            loca p1 = new loca(7, 0);
            loca L1 = new loca(0, 1);
            loca L2 = new loca(-1, 0);
            loca p2 = new loca(7, 0);
            /*
             p1 = p1 + L1;
            while(is_spot_on_board(p1)){
                if(board[p1.x][p1.y]==player)
                    s_value++;
                else
                    break;
                p1 = p1 + L1;
            }
            */
            p2 = p2.add(L2);
            while (in.is_spot_on_board(p2)) {
                if (in.aBroad[p2.x][p2.y] == in.player)
                    s_value++;
                else
                    break;
                p2 = p2.add(L2);
            }
        }

        return s_value;
    }

    int ABPminimax(broad node ,int depth,int A,int B, boolean MorU){
        if(depth == 0 || node.next_valid_spots.isEmpty()){
            int move = 0;
            if(node.disc_count[0] > 40) move = move_value(node)*10;
            else if(node.disc_count[0] > 20) move = move_value(node)*10;
            else if(node.disc_count[0] > 0) move = move_value(node)*10;
            return set_Q_value(node) + move ;
        }
        //my turn
        if(MorU){
            int Q = -214700000;
            for(loca it:node.next_valid_spots){
                broad child = new broad(node);
                child.put_disc(it);
                int child_value = ABPminimax(child, depth - 1,A, B, false);
                Q = Math.max(Q, child_value);

                A = Math.max(A, child_value);
                if(B <= A){
                    break;
                }
            }
            return Q;
        }
        //you turn
        else{
            int Q = 214700000;
            for(loca it:node.next_valid_spots){
                broad child = new broad(node);
                child.put_disc(it);
                int child_value = ABPminimax(child, depth - 1,A, B, true);
                Q = Math.min(Q, child_value);

                B = Math.min(B, child_value);
                if(B <= A){
                    break;
                }
            }
            return Q;
        }

    }

    loca Queen(broad root){
        loca p;
        //pick
        p = root.next_valid_spots.elementAt(0);
        int maxQ = -214700000;
        //有角必下，我就爛
        for(loca it:root.next_valid_spots){
            if((it.x == 0 && it.y == 0) || (it.x == 0 && it.y == 7) || (it.x == 7 && it.y == 0) || (it.x == 7 && it.y == 7)){
                p = it;
                return p;
            }
            else if(it.eq(new loca(0,0)) || it.eq(new loca(0,7)) ||it.eq(new loca(7,7)) ||it.eq(new loca(7,0))){
                p = it;
                return p;
            }
            broad next = new broad(root.aBroad);
            next.put_disc(it);
            int child_Q = ABPminimax(next,5,-214700000,214700000,false);
            if(maxQ <= child_Q){
                maxQ = child_Q;
                p = it;
            }
        }
        return p;
    }
}
