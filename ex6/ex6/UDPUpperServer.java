import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPUpperServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];

            while (true) {
                // 受信パケットを作成
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("受信: " + message);
                byte[] sendMessage = message.toUpperCase().getBytes();

                // クライアントのアドレスとポートを取得
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // 返信をクライアントに送信
                DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
