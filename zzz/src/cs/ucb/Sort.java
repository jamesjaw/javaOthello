package cs.ucb;

public class Sort {

    public static void sort(String[] input) {
        for(int i=0;i<input.length;i++) {
            int index = i;
            for(int j= i + 1;j<input.length;j++) {
                int re = input[index].compareTo(input[j]);
                if(re > 0) {
                    index = j;
                }
            }
            /*
            //指標相關 思考錯誤範例
            String temp = input[i];
            input[i] = min;
            min = temp;
             */
            String temp = input[index];
            input[index] = input[i];
            input[i] = temp;



        }
    }
}
