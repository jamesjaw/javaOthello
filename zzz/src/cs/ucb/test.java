package cs.ucb;

import java.util.Scanner;

public class test {

    static public void tower(int n, char s, char m, char e) {
        if(n == 1) {
            System.out.printf("move disk %d from rod %c to rod %c\n",n,s,e);
            return;
        }
        else {
            tower(n-1,s,e,m);
            System.out.printf("move disk %d from rod %c to rod %c\n",n,s,e);
            tower(n-1,m,s,e);
        }
    }

    static public void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        tower(n, 'A','B','C');
    }
}
