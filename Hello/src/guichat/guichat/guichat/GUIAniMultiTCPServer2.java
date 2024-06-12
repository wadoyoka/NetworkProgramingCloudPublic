package guichat;

//複数接続Socket通信サンプルプログラム(サーバー)
//クライアントからの接続を待ち、接続が行なわれたら
//1行のデータを受け取り、コンソールに表示して接続を切断する。
//複数のクライアントとの通信をスレッドにより行なう。
//プログラム終了は，コマンドプロンプトでCTRL-C
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class GUIAniMultiTCPServer2 {

	GUIAnimationMain animation;

	/*
	public static void main(String[] args) {
		new MultiTCPServer(null);

	}// mainend
	*/

	public GUIAniMultiTCPServer2(GUIAnimationMain animation) {
		this.animation = animation;
		System.out.println("server started:" + this.animation);
		System.out.println("creating srv socket");

		ServerSocket serverSoc = null;
		try {
			// ポート番号は、5000
			// ソケットを作成
			serverSoc = new ServerSocket(5000);
			boolean flag = true;

			// クライアントからの接続を待機するaccept()メソッド。
			// accept()は、接続があるまで処理はブロックされる。
			// もし、複数のクライアントからの接続を受け付けるようにするには
			// スレッドを使う。
			// accept()は接続時に新たなsocketを返す。これを使って通信を行なう。
			System.out.println("Waiting for Connection. ");
			int thcounter = 0;
			while (flag) {
				Socket socket = null;
				socket = serverSoc.accept();
				// accept()は、クライアントからの接続要求があるまでブロックする。
				// 接続があれば次の命令に移る。
				// スレッドを起動し、クライアントと通信する。
				new SrvWorkerThread(socket, thcounter++).start();

				// System.out.println("Waiting for New Connection. ");
				// flag=false;
			}// while end
		} catch (IOException e) {
			System.out.println("IOException!");
			e.printStackTrace();
		} finally {
			try {
				if (serverSoc != null) {
					serverSoc.close();
				}
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
		}// finally end

	}// MultiTCPServer(GUIAnimationFaceObjMain animation) end

	class SrvWorkerThread extends Thread {
		private Socket soc;

		public SrvWorkerThread(Socket sct, int thcounter) {
			soc = sct;
			System.out.println("Thread " + thcounter
					+ "is Generated.  Connect to " + soc.getInetAddress());
		}

		public void run() {
			try {
				// socketからのデータはInputStreamReaderに送り、さらに
				// BufferedReaderによってバッファリングする。
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(soc.getInputStream()));
				// Clientへの出力用PrintWriter
				PrintWriter sendout = new PrintWriter(soc.getOutputStream(),
						true);
				// データ読み取りと表示
				String line;
				line = reader.readLine();
				System.out.println("Message from client :" + line);

				if (line.equals("end")) {// endで 強制終了
					System.exit(1);
				}

				//受け取ったコマンドがface,colorで始まりかつanimationがあるなら。
				if (line.startsWith("face,color") && animation != null) {
					String[] sline=line.split(",");
					System.out.println("face"+sline[0]);
					System.out.println("color"+sline[1]);
					System.out.println("which"+sline[2]);
					System.out.println("color"+sline[3]);
					int which=Integer.parseInt(sline[2]);
					String  c=sline[3];

					if(c.equals("yellow")){
						animation.setFaceColor(which, Color.yellow);
					}else if(c.equals("red")){
						animation.setFaceColor(which, Color.red);
					}


				}else
				//受け取ったコマンドがface,placeで始まりかつanimationがあるなら。
				if (line.startsWith("face,place") && animation != null) {
					System.out.println("受信完了 :" + line);
					/***課題はここからを改造***/

					String[] sline=line.split(",");
					System.out.println("face"+sline[0]);
					System.out.println("place"+sline[1]);
					System.out.println("which"+sline[2]);
					System.out.println("x"+sline[3]);
					System.out.println("y"+sline[4]);
					int which=Integer.parseInt(sline[2]);
					int x=Integer.parseInt(sline[3]);
					int y=Integer.parseInt(sline[4]);
					animation.setFacePlace(which, x, y, line);

					// 顔の0番を強制的に移動させる関数
					//animation.addFace(0, 50, 50, line);

					//animation.addFace(1, 200, 50, line);

					/***課題はここまでを改造***/
				}else
					//受け取ったコマンドがface,placeで始まりかつanimationがあるなら。
					if (line.startsWith("face,emotion") && animation != null) {

						String[] sline=line.split(",");
						System.out.println("face"+sline[0]);
						System.out.println("emotion"+sline[1]);
						System.out.println("which"+sline[2]);
						System.out.println("angly"+sline[3]);

						int which=Integer.parseInt(sline[2]);
						String  emotion=sline[3];

						animation.setFaceEmotion(which, emotion);


					}

				// Clientにメッセージ送信
				sendout.println("Message is received at Server. Thankyou! your message is ["
						+ line + "]");

			} catch (IOException ioex) {
				ioex.printStackTrace();
			} finally {
				try {
					if (soc != null) {
						soc.close();
					}
				} catch (IOException ioex) {
					ioex.printStackTrace();
				}
			}// finall end
		}// run end
	}// SrvWorkerThread end

}// class MultiServerSample end

