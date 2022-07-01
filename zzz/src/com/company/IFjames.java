package com.company;

import javax.swing.*;

//介面只有public 沒寫也是public
public interface IFjames {
    //介面都是抽象方法
    /*public*/void m1();
    int x = 0;

}


class wantToBeIFjames implements IFjames {
    //int x = 1;
    final static int c = 0;
    public void m1() {
        System.out.println("overridding m1");
        System.out.println(x);
    }
    static void eee() {
        //c = 1;
    }


}
