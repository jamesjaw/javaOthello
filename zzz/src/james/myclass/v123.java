package james.myclass;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class v123 {
    public static void main(String[] args) throws IOException {
        /*
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);

        File f1 = new File("/Users/james/IdeaProjects/zzz/dir1");
        if(f1.exists()) System.out.println("ok");
        try {
            FileInputStream Fin = new FileInputStream("./dir1/123");
            byte[] a = Fin.readAllBytes();

            String aa = new String(a);
            System.out.println(aa);
            FileOutputStream Fout = new FileOutputStream("./dir1/333");
            String s = "boring";
            a = s.getBytes();
            Fout.write(a);
            Fout.flush();
            Fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream("./dir1/maskdata.csv")));
        String oneLine;
        while((oneLine = fin.readLine()) != null) {
            String[] data = oneLine.split(",");
            System.out.println(data[1] + " " + data[4] + " " + data[5]);
        }
        fin.close();
        */
        LinkedList<Integer> l = new LinkedList<>();
        for(int i=0;i<10;i++) {
            l.add(i);
        }

    }

}
