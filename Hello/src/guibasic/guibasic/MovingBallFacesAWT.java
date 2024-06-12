package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MovingBallFacesAWT {

	public static void main(String[] args) {
		new MovingBallFacesAWT();
	}

	MovingBallFacesAWT() {

		MovingInnerFFrame f = new MovingInnerFFrame();
		Thread th = new Thread(f);
		th.start();


		f.setSize(500, 500);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.show();


		int MAXTime=20;
		for(int i=0;i<20;i++){
			try {

				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			}
			System.out.println("�c��"+(MAXTime-i)+"�b�ł�");
		}
		System.out.println("�I�����܂�");
		f.setTh_enable(false);
		System.out.println("�I�����܂���");

		//System.exit(0);

	}


	class MovingInnerFFrame extends Frame implements Runnable {

		int ballnum=5;
		BallRim[] myBalls= new BallRim[ballnum];

		private boolean th_enable = true;
		public boolean isTh_enable() {
			return th_enable;
		}

		public void setTh_enable(boolean th_enable) {
			this.th_enable = th_enable;
		}

		private int th_counter = 0;
		//Color[] colors={Color.RED,Color.BLUE,Color.BLACK,Color.yellow};

		int w=500;
		int h=500;

		public void run() {

			for(int i=0;i<myBalls.length;i++){
				myBalls[i] = new BallRim();
			}

			int timer=0;
			while (th_enable) {

				try {

					Thread.sleep(100);
					th_counter++;
					/*
					if(th_counter%10==0){
						timer++;
						System.out.println("�c��"+(20-timer));
					}*/
					if (th_counter >= 500)
						th_enable = false;
				} catch (InterruptedException e) {
				}


				for(int i=0;i<myBalls.length;i++){
					myBalls[i].move();
				}

				repaint(); // paint()���\�b�h���Ăяo�����
			}
		}

		public void paint(Graphics g) {

			for(int i=0;i<myBalls.length;i++){
				myBalls[i].draw(g);;
			}

		}

	}// FFrameend

	// BallRim
	class BallRim {
		FaceObj fobj;
		String str="";

		Random rdn;
		int w=500;
		int h=500;

		int x;
		int y;
		int radius; //ボールの半径
		Color color = Color.RED;

		double xDir = -1;
		double yDir = 1;

		BallRim(){

			rdn=new Random();
			xDir=rdn.nextDouble()*2-1;
			yDir=rdn.nextDouble()*2-1;

			setPosition(rdn.nextInt(w), rdn.nextInt(h));
			setRadius(rdn.nextInt(30)+30);//30-60�̃T�C�Y�̊�̗֊s

			Color c= new Color(rdn.nextInt(255),rdn.nextInt(255),rdn.nextInt(255));
			setColor(c);

			fobj=new FaceObj();

		}

		void setColor(Color c) {
			this.color = c;
		}

		public void setText(String str){
			this.str=str;

		}

		void move() {


			if ((xDir >0) && (x+this.radius*2 >= w)) {
				xDir = -1*xDir;
			}
			if ((xDir <0) && (x <= 0)) {
				xDir = -1*xDir;
			}

			if (xDir >0) {
				x = x + 10;
			} else {
				x = x - 10;
			}

			if ((yDir >0) && (y+this.radius*2 >= h)) {
				yDir = -1*yDir;
			}
			if ((yDir <0) && (y <= 25)) {
				yDir = -1*yDir;
			}

			if (yDir >0) {
				y = y + 10;
			} else {
				y = y - 10;
			}

		}

		void setPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void setRadius(int r) {
			this.radius = r;
		}

		void draw(Graphics g) {
			g.setColor(color);
			g.fillOval(x, y, 2 * radius, 2 * radius);
			fobj.setXY(x, y);
			fobj.setSize(2*radius, 2*radius);
			fobj.makeFace(g);
		}

	}// ball end


	class FaceObj {


		int h=100;
		int w=100;

		int xStart = 0;
		int yStart = 0;

		public void setXY(int x, int y){
			this.xStart = x;
			this.yStart = y;
		}

		public void setSize(int h, int w){
			this.h = h;
			this.w = h;
		}

		public FaceObj() {

		}



		public void makeFace(Graphics g) {
			//makeRim(g);
			makeEyes(g, w/5);
			makeNose(g, h/5);
			makeMouth(g, w/2);
		}

		public void makeRim(Graphics g) {
			g.drawLine(xStart, yStart, xStart + h, yStart);
			g.drawLine(xStart, yStart, xStart, yStart + w);
			g.drawLine(xStart, yStart + w, xStart + h, yStart + w);
			g.drawLine(xStart + h, yStart, xStart + h, yStart + w);
		}

		public void makeEyes(Graphics g, int eyeSize) {
			g.setColor(Color.red);
			//g.fillRect(xStart + (h * 1 / 3) - 20, yStart + (w * 1 / 3) - 10,
			//		10, 10);
			g.setColor(Color.blue);

			// g.fillRoundRect()
			// g.fillOval()
			g.fillArc(xStart + (h * 2/ 7) , yStart + (w * 1 / 3), eyeSize,
					eyeSize, 0, 300);
			g.setColor(Color.black);

			//g.drawLine(xStart + (h * 1 / 3) - 20, yStart + (w * 1 / 3) - 10,
			//		xStart + (h * 1 / 3) + 20, yStart + (w * 1 / 3) - 10);
			//g.drawLine(xStart + (h * 2 / 3) - 20, yStart + (w * 1 / 3) - 10,
			//		xStart + (h * 2 / 3) + 20, yStart + (w * 1 / 3) - 10);

			g.drawOval(xStart + (h * 2 / 7) , yStart + (w * 1 / 3),
					eyeSize, eyeSize);
			g.drawOval(xStart + (h * 4 / 7) , yStart + (w * 1 / 3),
					eyeSize, eyeSize);
		}

		public void makeNose(Graphics g, int noseSize) {
			g.drawLine(xStart + (h * 1 / 2), yStart + (w * 2 / 4), xStart
					+ (h * 1 / 2), yStart + (w * 2 / 4) + noseSize);
		}

		public void makeMouth(Graphics g, int mouseSize) {
			int xMiddle = xStart + h / 2;
			int yMiddle = yStart + 3*w/4;
			g.drawLine(xMiddle - mouseSize / 2, yMiddle, xMiddle + mouseSize
					/ 2, yMiddle);
		}

	}// Ball Rim end

}