package james.myclass;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class net2 {
    public static void main(String[] args) {
        byte[] input = new byte[2048];

        try {
            DatagramSocket socket = new DatagramSocket(8888);
            DatagramPacket packet = new DatagramPacket(input, input.length);
            socket.receive(packet);
            socket.close();
            byte[] rec = packet.getData();
            int len = packet.getLength();
            System.out.println(new String(rec,0,len));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
