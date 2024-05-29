import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastServer {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.0.0.0");
            int port = 49153;

            // マルチキャストソケットを作成し、指定したグループとポートに参加
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(group); // マルチキャストグループに参加
            // 受信用のバッファを作成
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Server started. Waiting for commands...");

            // コマンドを受信して永遠に待機
            while (true) {
                socket.receive(packet);
                String receivedCommand = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received command: " + receivedCommand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
