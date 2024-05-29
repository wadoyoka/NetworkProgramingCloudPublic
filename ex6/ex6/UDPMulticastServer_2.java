import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPMulticastServer_2 {
    public static void main(String[] argv)
            throws Exception {
        // 5100番ポートを監視するUDPソケットを生成
        DatagramSocket receiveSocket = new DatagramSocket(5100);

        // 受け付けるデータバッファとUDPパケットを作成
        byte receiveBuffer[] = new byte[1024];
        DatagramPacket receivePacket =
                new DatagramPacket(receiveBuffer, receiveBuffer.length);

        while (true) {
            // UDPパケットを受信
            receiveSocket.receive(receivePacket);

            // 受信したデータを標準出力へ出力
            System.out.println
                    (new String(receivePacket.getData(),
                            0, receivePacket.getLength()));
        }
    }
}