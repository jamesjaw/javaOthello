package james.myclass;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class net {
    public static void main(String[] args) {
        byte[] buf = "中文測試".getBytes();
        try {
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(buf,buf.length,InetAddress.getByName("10.0.104.154"), 8888);
            socket.send(packet);
            socket.close();
            System.out.println("send ok!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
