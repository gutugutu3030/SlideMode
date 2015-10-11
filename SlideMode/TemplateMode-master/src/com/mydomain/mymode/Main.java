package com.mydomain.mymode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.illposed.osc.*;
import java.net.InetAddress;

public class Main extends JPanel
		implements ActionListener, MouseListener, MouseMotionListener, ComponentListener, WindowListener, KeyListener {

	BufferedImage img[];

	public Main(SlideMode mode) {
		setBackground(Color.black);
		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(this);
		addKeyListener(this);
		this.mode = mode;
		this.time = new Timer(16, this);
		this.time.start();
		ArrayList<BufferedImage> tmp = new ArrayList<BufferedImage>();
		for (int i = 1; i < 100; i++) {
			BufferedImage readImage;
			try {

				readImage = ImageIO.read(new File(mode.modePath("../data/スライド" + i + ".PNG")));
			} catch (Exception e) {
				e.printStackTrace();
				readImage = null;
			}
			if (readImage == null)
				break;
			tmp.add(readImage);
		}
		img = tmp.toArray(new BufferedImage[0]);
	}

	String map2str(int map[][]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (!(i == 0 && j == 0)) {
					sb.append(",");
				}
				sb.append(map[i][j]);
			}
		}
		return sb.toString();
	}

	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	SlideMode mode;

	public int mouseX = 0;
	public int mouseY = 0;
	public int frameCount = 0;
	public int width = 0;
	public int height = 0;
	Timer time;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.frameCount++ == 0) {
			setup(g);
		} else {
			draw(g);
		}
	}

	public void setframeRate(int frameRate) {
		this.time.setDelay(1000 / frameRate);
	}

	public void setup(Graphics g) {
	}

	int index = 0;

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		double time = Math.min((double) getWidth() / img[index].getWidth(),
				(double) getHeight() / img[index].getHeight());
		g.drawImage(img[index], 0, 0, (int) (img[index].getWidth() * time), (int) (img[index].getHeight() * time),
				this);
	}

	public void println(String str) {
		System.out.println(str);
	}

	public void println(int inte) {
		System.out.println(inte);
	}

	public void println(float flo) {
		System.out.println(flo);
	}

	public void println(double dou) {
		System.out.println(dou);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			index++;
			if (index >= img.length) {
				index = img.length - 1;
			}
			return;
		}
		index--;
		if (index < 0) {
			index = 0;
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseDragged(MouseEvent arg0) {
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void componentResized(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("ひええ");
		System.out.println(e.getKeyCode());
		System.out.println(KeyEvent.VK_RIGHT);

		// TODO 自動生成されたメソッド・スタブ
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			index++;
			if (index >= img.length) {
				index = img.length - 1;
			}
			break;
		case KeyEvent.VK_LEFT:
			index--;
			if (index < 0) {
				index = 0;
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
