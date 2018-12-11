package Game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.KeyEvent;
import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener {
	
	Goodguy link = new Goodguy(600,600,400,200,"files/Penguin.png");

	
	public MyCanvas() {
		this.setSize(600,400);
		this.addKeyListener(this);
		playIt("files/audio.wav");
	}
	
	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void paint(Graphics g) {
		g.drawImage(link.getImg(),  link.getxCoord(),  link.getyCoord(),  link.getWidth(),  link.getHeight(),  this);
	}
	public void keyPressed(KeyEvent e) {
		link.moveIt(e.getKeyCode());
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}