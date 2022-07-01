package james.myclass;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class tcp {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(7777);
            Socket inputSocket = socket.accept();

            BufferedInputStream inputStream = new BufferedInputStream(inputSocket.getInputStream());
            byte[] buf = new byte[2048];
            int len;
            while((len = inputStream.read(buf)) != -1) {
                System.out.print(new String(buf,0,len));
            }

//            BufferedInputStream bin = new BufferedInputStream(inputSocket.getInputStream());
//            byte[] buf = new byte[4096];
//            int len;
//            while ((len = bin.read(buf)) != -1) {
//                System.out.print(new String(buf,0,len));
//            }

            //bin.close();

            inputSocket.close();
            socket.close();
            System.out.println("return 0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
