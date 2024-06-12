//FacesAWTMain 目標 インナークラスのFaceObjクラスを作ってみよう。描画処理を移譲してください。
//3x3　の顔を描画してください。色などもぬってオリジナルな楽しい顔にしてください。

package ex7;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWTMain {

	public static void main(String[] args) {
		new FacesAWTMain();
	}

	FacesAWTMain() {
		FaceFrame f = new FaceFrame();
		f.setSize(800, 800);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setVisible(true);
	}

	// インナークラスを定義
	class FaceFrame extends Frame {

		private int w;
		private int h;
		private int xStart;
		private int yStart;
		private FaceObj[] fobj1;

		FaceFrame() {
			fobj1 = new FaceObj[9];
		}

		public void paint(Graphics g) {
			// この中には、g.drawLine というのは入ってこない
			// Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す
			w = 200;
			h = 200;
			xStart = 50;
			yStart = 50;
			String state = "normal";

			for (int i = 0; i < fobj1.length; i++) {
				if (i % 3 == 0 && i != 0) {
					yStart += h;
					state = "smile";
				}
				fobj1[i] = new FaceObj(g, w, h, xStart + w * (i % 3), yStart, state);
			}
		}
	}// FaceFrame end

	// Faceクラスを作ってみよう。
	private class FaceObj {
		private int w;
		private int h;
		private int xStart;
		private int yStart;
		private String state;

		FaceObj(Graphics g, int w, int h, int xStart, int yStart, String state) {
			this.w = w;
			this.h = h;
			this.xStart = xStart;
			this.yStart = yStart;
			this.state = state;
			drawRim(g);
			drawunderNose(g, 35);
			drawEye(g, 35);
			drawEye(g, 35);
			drawNose(g, 35);
			drawMouth(g, 100, this.state);
		}

		public void drawRim(Graphics g) { // wが横幅、hが縦幅
			g.drawLine(xStart, yStart, xStart + w, yStart);
			g.drawLine(xStart, yStart, xStart, yStart + h);
			g.drawLine(xStart, yStart + h, xStart + w, yStart + h);
			g.drawLine(xStart + w, yStart, xStart + w, yStart + h);
		}

		public void drawunderNose(Graphics g, int r) { // xは目の幅 呼ばれる方(=定義する方)
			int bigw = r;
			int miniw = (int) bigw / 2;
			g.drawLine(xStart + w / 2, yStart + 30 + (int) (1.25 * bigw) + miniw, xStart + w / 2,
					yStart + 30 + (int) (1.25 * bigw) + miniw + 40);
		}

		public void drawNose(Graphics g, int r) { // xは鼻の長さ
			g.setColor(Color.red);
			int bigw = r;
			int miniw = (int) bigw / 2;
			g.fillOval(xStart + w / 2 - miniw / 2 - 1, yStart + 30 + (int) (1.25 * bigw), miniw, miniw);
			g.setColor(Color.black);
		}

		public void drawEye(Graphics g, int r) { // rは目の半径
			int bigw = r;
			g.drawOval(xStart + w / 2 - bigw, yStart + 30, bigw, (int) (1.5 * bigw));
			g.drawOval(xStart + w / 2, yStart + 30, bigw, (int) (1.5 * bigw));
			int miniw = (int) bigw / 3;
			g.fillOval(xStart + w / 2 - miniw - 5, yStart + 30 + (int) (1.5 * bigw / 2) - (int) (1.5 * miniw) / 2,
					miniw, (int) (1.5 * miniw));
			g.fillOval(xStart + w / 2 + 5, yStart + 30 + (int) (1.5 * bigw / 2) - (int) (1.5 * miniw) / 2, miniw,
					(int) (1.5 * miniw));

			g.setColor(Color.white);
			int miniminiw = (int) (miniw / 1.95);
			g.fillOval(xStart + w / 2 - miniw - 3, yStart + 30 + (int) (1.5 * bigw / 2) - (int) (1.5 * miniw) / 2 + 1,
					miniminiw, (int) (1.5 * miniminiw));
			g.fillOval(xStart + w / 2 + 6, yStart + 30 + (int) (1.5 * bigw / 2) - (int) (1.5 * miniw) / 2 + 1,
					miniminiw, (int) (1.5 * miniminiw));
			g.setColor(Color.BLACK);
			// g.drawOval(xStart + 45, yStart + 65, r, r);
		}

		public void drawMouth(Graphics g, int mx, String state) { // xは口の幅
			int xMiddle = xStart + w / 2;
				int yMiddle = yStart + h - 30;
			if (state.equals("normal")) {
				g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle + mx / 2, yMiddle);
			}else if (state.equals("smile")) {
				g.drawLine(xMiddle - mx / 2, yMiddle-20, xMiddle, yMiddle);
				g.drawLine(xMiddle, yMiddle, xMiddle + mx / 2, yMiddle-20);
			}
		}
	}

}// Faces class end
