package cs.ucb;

import java.util.Scanner;

public class IDs {
    static int len = 10;
    static int[] code = new int[] {1,9,8,7,6,5,4,3,2,1,1};
    static int[] ABC = new int[95];

    static {
        ABC[65] = 10;
        ABC[66] = 11;
        ABC[67] = 12;
        ABC[68] = 13;
        ABC[69] = 14;
        ABC[70] = 15;
        ABC[71] = 16;
        ABC[72] = 17;
        ABC[73] = 34;
        ABC[74] = 18;
        ABC[75] = 19;
        ABC[76] = 20;
        ABC[77] = 21;
        ABC[78] = 22;
        ABC[79] = 35;
        ABC[80] = 23;
        ABC[81] = 24;
        ABC[82] = 25;
        ABC[83] = 26;
        ABC[84] = 27;
        ABC[85] = 28;
        ABC[86] = 29;
        ABC[87] = 32;
        ABC[88] = 30;
        ABC[89] = 31;
        ABC[90] = 33;
    }
    public static boolean isValid(String id) {

        {
            System.out.println("call is Valid");
        }



        //長度
        if(id.length() != len) {
            System.out.println("長度不足");
            return false;
        }
        //英文
        if((int)(id.charAt(0)) < 65 || (int)(id.charAt(0)) > 90) {
            System.out.println("首字必須英文大寫");
            return false;
        }
        //第一碼1 or 2
        if(id.charAt(1) != '1' && id.charAt(1) != '2') {
            System.out.println("第一碼為1或2");
            return false;
        }
        //其他都0
        for(int i=2;i<10;i++) {
            if(id.charAt(i) < 48 || id.charAt(i) > 57) {
                System.out.println("必須為數字");
                return false;
            }
        }
        //驗證算式
        int val = 0;
        int frist = ABC[(int)(id.charAt(0))];
        val += (frist / 10) * code[0];
        val += (frist % 10) * code[1];
        for(int i=2;i<11;i++) {
            val += ((int)(id.charAt(i - 1)) - 48) * code[i];
        }
        if(val % 10 != 0) {
            System.out.println("無效號碼");
            System.out.println(val);
            System.out.println(frist);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            String s = input.nextLine();
            //exit
            if(s.charAt(0) == 'e' && s.charAt(3) == 't') {
                break;
            }

            boolean re = isValid(s);
            if(re) {
                System.out.println(s + " is valid " );
            }
            else {
                System.out.println(s + " is not valid " );
            }
        }
    }
}
