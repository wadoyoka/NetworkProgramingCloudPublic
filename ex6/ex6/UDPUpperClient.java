
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPUpperClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            String message = "abc";
            byte[] sendData = message.getBytes();
            byte[] receiveData = new byte[1024];

            // 送信パケットを作成
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            socket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("サーバからの返信: " + serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
