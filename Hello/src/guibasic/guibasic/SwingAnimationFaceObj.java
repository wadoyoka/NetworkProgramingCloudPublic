package guibasic.guibasic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingAnimationFaceObj extends JPanel implements ActionListener {

	private Ellipse2D.Float ellipse = new Ellipse2D.Float();

	final int framespeed = 60;
	final private double MAXCounter = 500;

	private boolean isResetProcess = true;
	private double counter;
	private Timer timer;

	private int ballnum = 5;
	private BallRim[] myBalls = new BallRim[ballnum];

	public static void main(String[] args) {
		/* おまじない：開始 */
		SwingAnimationFaceObj animation = new SwingAnimationFaceObj();
		JFrame frame = new JFrame("SwingFaceObject");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(animation);
		frame.setSize(550, 550);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		/* おまじない：終了 */
	}// main end

	ActionListener updateProBar;

	public SwingAnimationFaceObj() {

		timer = new Timer(framespeed, this);
		timer.setInitialDelay(500);
		timer.start();

	}

	public void paint(Graphics g) {
		/* おまじない：開始 */

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2.setRenderingHints(rh);
		Dimension windowSize = getSize();

		if (isResetProcess) {
			initProcess(windowSize.width, windowSize.height);
			isResetProcess = false;
		}

		this.doNextStep(windowSize.width, windowSize.height);
		paintProcess(windowSize.width, windowSize.height, g2);
		/* おまじない：終了 */

	}// paint end

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void setEllipseSize(double size, int w, int h) {
		counter = size;
		ellipse.setFrame(10, 10, size, size);
	}

	public void initProcess(int w, int h) {

		for (int i = 0; i < myBalls.length; i++) {
			myBalls[i] = new BallRim(w, h);
		}

		setEllipseSize(1, w, h);
	}

	public void doNextStep(int w, int h) {
		counter++;

		if (counter > MAXCounter) {
			initProcess(w, h);

		} else {

			for (int i = 0; i < myBalls.length; i++) {
				myBalls[i].move();
			}

			ellipse.setFrame(ellipse.getX(), ellipse.getY(), counter, counter);
		}
	}

	public void paintProcess(int w, int h, Graphics2D g2) {

		g2.setColor(Color.BLUE);
		g2.draw(ellipse);
		g2.drawString(counter + "Step経過", 10, 10);

		for (int i = 0; i < myBalls.length; i++) {
			myBalls[i].draw(g2);
			;
		}

	}

	class BallRim {

		FaceObj fobj;

		String str = "";

		Random rdn;
		int w = 500;
		int h = 500;

		int x;
		double y;
		int vy;
		final static double G = 9.8 / 6;
		int radius; // 半径
		Color basicColor = Color.gray;

		double xDir = -1; // 1:+方向 -1: -方向
		double yDir = 1;
		private int strCounter;

		BallRim(int w, int h) {
			vy = 1;

			rdn = new Random();
			xDir = rdn.nextDouble() * 2 - 1;
			yDir = rdn.nextDouble() * 2 - 1;
			this.w = w;
			this.h = h;

			setPosition(rdn.nextInt(w), rdn.nextInt(h));
			setRadius(100);// 30-60のサイズの顔の輪郭

			Color bcolor = new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255));
			setBasicColor(bcolor);

			fobj = new FaceObj();

		}

		void setBasicColor(Color bcolor) {
			this.basicColor = bcolor;
		}

		public void setCollisionText(String str, int strCounter) {
			this.str = str;
			this.strCounter = strCounter;

		}

		void move() {

			if ((xDir > 0) && (x + this.radius * 2 >= w)) {
				xDir = -1 * xDir;
				setCollisionText("右が痛いわぁ", 3);
			} else if ((xDir < 0) && (x <= 0)) {
				xDir = -1 * xDir;
				setCollisionText("左の頬がめちゃ痛いわ！", 6);
			} else if (xDir > 0) {
				x = x + 10;
			} else {
				x = x - 10;
			}

			if ((yDir > 0) && (y + this.radius * 2 >= h)) {
				yDir = -1 * yDir;
				setCollisionText("顎が痛いわぁ", 3);
			}
			if ((yDir < 0) && (y <= 0)) {
				yDir = -1 * yDir;
				setCollisionText("頭がめちゃ痛いわ！", 6);
			}

			// if (yDir >0) {
			vy += G;
			y = y + vy;
			if (y > 550 - radius * 2.6) {
				y = 550 - radius * 2.6;
				vy *= -1.5;
				y += vy;
			}
			// } else {
			// y = y - 10;
			// }

		}

		void setPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void setRadius(int r) {
			this.radius = r;
		}

		void draw(Graphics g) {
			g.setColor(basicColor);
			g.fillOval(x, (int) y, 2 * radius, 2 * radius); // rは半径なので2倍にする
			if (strCounter > 0) {
				g.drawString(str, x, (int) y);
				strCounter--;
			} else {
				str = "";
			}

			fobj.setXY(x, (int) y);
			fobj.setSize(2 * radius, 2 * radius);
			fobj.makeFace(g);

		}

	}// ball end

	class FaceObj {
		// コンストラクタ
		private String state ="smile";

		int h = 100;
		int w = 100;

		int xStart = 0;
		int yStart = 0;

		public void setXY(int x, int y) {
			this.xStart = x;
			this.yStart = y;
		}

		public void setSize(int h, int w) {
			this.h = h;
			this.w = h;
		}

		public FaceObj() {

		}

		// 個々にメソッドを追加

		public void makeFace(Graphics g) {
			// makeRim(g);
			// drawRim(g);
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
			} else if (state.equals("smile")) {
				g.drawLine(xMiddle - mx / 2, yMiddle - 20, xMiddle, yMiddle);
				g.drawLine(xMiddle, yMiddle, xMiddle + mx / 2, yMiddle - 20);
			}
		}

		/*
		 * public void makeRim(Graphics g) {
		 * g.drawLine(xStart, yStart, xStart + h, yStart);
		 * g.drawLine(xStart, yStart, xStart, yStart + w);
		 * g.drawLine(xStart, yStart + w, xStart + h, yStart + w);
		 * g.drawLine(xStart + h, yStart, xStart + h, yStart + w);
		 * }
		 */

		// public void makeEyes(Graphics g, int eyeSize) {
		// // setColor(Color.red);
		// // g.fillRect(xStart + (h * 1 / 3) - 20, yStart + (w * 1 / 3) - 10,
		// // 10, 10);
		// g.setColor(Color.blue);

		// // g.fillRoundRect()
		// // g.fillOval()
		// g.fillArc(xStart + (h * 2 / 7), yStart + (w * 1 / 3), eyeSize,
		// eyeSize, 0, 300);
		// g.setColor(Color.black);

		// // g.drawLine(xStart + (h * 1 / 3) - 20, yStart + (w * 1 / 3) - 10,
		// // xStart + (h * 1 / 3) + 20, yStart + (w * 1 / 3) - 10);
		// // g.drawLine(xStart + (h * 2 / 3) - 20, yStart + (w * 1 / 3) - 10,
		// // xStart + (h * 2 / 3) + 20, yStart + (w * 1 / 3) - 10);

		// g.drawOval(xStart + (h * 2 / 7), yStart + (w * 1 / 3),
		// eyeSize, eyeSize);
		// g.drawOval(xStart + (h * 4 / 7), yStart + (w * 1 / 3),
		// eyeSize, eyeSize);
		// }

		// public void makeNose(Graphics g, int noseSize) {
		// g.drawLine(xStart + (h * 1 / 2), yStart + (w * 2 / 4), xStart
		// + (h * 1 / 2), yStart + (w * 2 / 4) + noseSize);
		// }

		// public void makeMouth(Graphics g, int mouseSize) {
		// int xMiddle = xStart + h / 2;
		// int yMiddle = yStart + 3 * w / 4;
		// g.drawLine(xMiddle - mouseSize / 2, yMiddle, xMiddle + mouseSize
		// / 2, yMiddle);
		// }
	}// FaceObj End

}