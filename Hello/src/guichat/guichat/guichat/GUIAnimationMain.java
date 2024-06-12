package guichat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUIAnimationMain extends JPanel implements ActionListener {

	private Ellipse2D.Float ellipse = new Ellipse2D.Float();

	final int FRAME_SPEED = 50;
	final private double MAX_Counter = 5000;

	private boolean isResetProcess = true;
	private double counter;
	private Timer timer;

	private int INIT_BALLNUM = 3;
	private GUIAnimationBall[] myBallRims = new GUIAnimationBall[INIT_BALLNUM];

	public static void main(String[] args) {// main 関数
		/* Frame関係調整：開始 */
		System.out.println("GUIAnimationFaceObjMain started");
		GUIAnimationMain animation = new GUIAnimationMain();

		JFrame frame = new JFrame("GUIAnimationFaceObjMain");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(animation);
		frame.setSize(550, 550);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		/* Frame関係調整終了：終了 */

		/***********************/
		// TCPChatサーバをスタート!!!!!
		/***********************/
		new GUIAniMultiTCPServer2(animation);

	}// main end

	ActionListener updateProBar;

	// コンストラクタ
	public GUIAnimationMain() {
		timer = new Timer(FRAME_SPEED, this);
		timer.setInitialDelay(500);
		timer.start();
	}

	// i番目の顔の位置を変更する関数。
	public void setFacePlace(int i, int x, int y, String message) {
		System.out.println("setFacePlace() :" + message);
		myBallRims[i].setPosition(x, y);
		myBallRims[i].setMessage(message);
	}

	// i番目の感情を変更する関数。
	public void setFaceEmotion(int which, String emotion) {
		System.out.println("setFaceEmotion() :" + emotion);
		myBallRims[which].setEmotion(emotion);
	}

	// i番目の顔の色を変更する関数。
	public void setFaceColor(int which, Color c) {
		System.out.println("setFaceColor()");

		myBallRims[which].setBasicColor(c);
		myBallRims[which].setMessage(c.toString());

	}

	// 初期化処理
	void initProcess(int w, int h) {
		for (int i = 0; i < myBallRims.length; i++) {
			myBallRims[i] = new GUIAnimationBall(w, h);
		}
		setEllipseSize(1, w, h);
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

	void doNextStep(int w, int h) {
		counter++;

		if (counter > MAX_Counter) {
			initProcess(w, h);

		} else {
			// ボールの移動
			for (int i = 0; i < myBallRims.length; i++) {
				myBallRims[i].move();
			}
			ellipse.setFrame(ellipse.getX(), ellipse.getY(), counter, counter);
		}
	}// doNextStep() end

	void paintProcess(int w, int h, Graphics2D g2) {

		g2.setColor(Color.BLUE);
		g2.draw(ellipse);
		g2.drawString(counter + "Step経過", 10, 10);

		for (int i = 0; i < myBallRims.length; i++) {
			myBallRims[i].draw(g2);
		}
	}// paintProcess end

	// 顔の輪郭



}// GUI Animation End