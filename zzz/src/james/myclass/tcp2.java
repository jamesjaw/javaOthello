package james.myclass;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class tcp2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),7777);

            BufferedOutputStream bout = new BufferedOutputStream(
                    socket.getOutputStream());
            bout.write("Hello, Brad".getBytes());
            bout.close();

            socket.close();
            System.out.println("cl ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
