package com.company;
import james.myclass.ball;

import java.util.Vector;

class printEve<T> {
    T val;
    printEve(T input) {
        val = input;
    }
    printEve() {

    }
    void printIt() {
        System.out.println(val);
    }
}
class list {
    public int val;
    list next;

    list(int input, list n) {
        val = input;
        next = n;
    }
    public int size() {
        if(this.next == null) {
            return 1;
        }
        else {
            //要next進到下一個node
            return 1 + this.next.size();
        }
    }
    public int itsize() {
        int num = 0;
        list temp = this;
        while(temp != null) {
            temp = temp.next;
            num++;
        }
        return num;
    }
    public int get(int x) {
        list p = this;
        for (int i = 0; i < x; i++) {
            p = p.next;
        }
        return p.val;
    }


}
//多包一層中間層，可以避免this不能改值的缺點
class Slist {
    public list frist;
    Slist(int x) {
        frist = new list(x, null);
    }
    public void addFrist(int x) {
        frist = new list(x, frist);
    }
    public int getFrist() {
        return frist.val;
    }

}
class Alist {
    int size = 100;
    int last = 0;
    int[] array = new int[size];

    void add(int x) {
        if(last == size -1) {
            size += 100;
            int [] new_array = new int[size];
            System.arraycopy(array,0,new_array,0,size -100);
            array = new_array;
        }
        array[last++] = x;
    }
}


class z {
    static protected void mth() {

    }
    private void a() {
        System.out.println("a");
    }
    protected void b() {
        System.out.println("b");
    }
}

class x extends z {
    public void c() {
        b();
        super.b();
    }
    public void b() {
        System.out.println("zzz");
    }

}

class c extends x {
    public void t() {

    }
}

public class Test {
    public static void main(String[] args) {
        /*
        list temp = new list(3, null);
        temp = new list(2, temp);
        temp = new list(1, temp);
        temp = new list(0, temp);

        System.out.println(temp.get(3));
        System.out.println(temp.size());
        System.out.println(temp.itsize());
         */ //linked list
        /*
        int t[][] = new int[3][3];
        int z = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                t[i][j] = z++;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
        var v = t[0][0];
        for(int i=0;i<9;i++) {
            v[i] = 1;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }

         */ // 測試指標
        /*
        List<Integer> ll = new ArrayList<>();
        for(int i=1;i<=52;i++) {
            ll.add(i);
        }
        Collections.shuffle(ll);
        for(int i=0;i<52;i++) {
            System.out.print(ll.get(i) + " ");
        }

         */ // suffer
        /*
        printEve<String> t = new printEve<>("hello");
        t.printIt();
         */ // template class
        /*
        ball my_ball = new ball(10);
        my_ball.ball_size();
        Alist vec = new Alist();
        */
//        Vector<Integer> z = new Vector<>();
//        System.out.println(z.size());
//        z.add(1);
//        System.out.println(z.size());
//        x example = new x();
//        example.b();
//        example.c();


    }



}

