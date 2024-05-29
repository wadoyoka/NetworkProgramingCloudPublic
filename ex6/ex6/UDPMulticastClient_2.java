import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class UDPMulticastClient_2 {
        public static void main(String[] argv)
                        throws Exception {
                // UDPパケットを送信する先となるブロードキャストアドレス (5100番ポート) //ネットワーク部が全てビットがたっているものが
                // ブロードキャストアドレス
                /*
                 * 192.168.1.255」は自分のネットワーク環境に合わせて書き変える必要がある。
                 * ホストが所属しているネットワークが192.168.0.xxx/255.255.255.0なら「192.168.0.255」と指定する。
                 */
                InetAddress myHost=null;
                try {
                        myHost = InetAddress.getLocalHost();
                        System.out.println("Host Name:" + myHost.getHostName());
                        System.out.println("IP Address:" + myHost.getHostAddress());
                } catch (UnknownHostException e) {
                        System.err.println(e);
                }
                InetSocketAddress remoteAddress = new InetSocketAddress(myHost.getHostAddress(), 5100);

                String str = "HELLO";
                // UDPパケットに含めるデータ
                byte[] sendBuffer = str.getBytes();

                // UDPパケット
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, remoteAddress);

                // DatagramSocketインスタンスを生成して、UDPパケットを送信
                new DatagramSocket().send(sendPacket);
        }
}
