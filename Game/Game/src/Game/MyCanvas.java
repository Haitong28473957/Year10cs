package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import sun.audio.*;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.*;


public class MyCanvas extends Canvas implements KeyListener, MouseListener, ActionListener {
	
	Goodguy gameover = new Goodguy (0, 0, 200, 200, "files/gameover.png");
	Goodguy penguin = new Goodguy (0, 0, 200, 200, "files/Penguin.png");
	boolean clicked = false;
	int score = 0;
	int cup = 0;
	int bgnum = 0;
	Random rand1 = new Random();
	Buttons button = new Buttons (200, 200, 50, 50, "files/download-2.png");
	Timer timer = new Timer("Timer");
	LinkedList buttons = new LinkedList();
	long bgrtime = 0;
	boolean bgrtimer = false;
	Buttons newbutton = new Buttons (100, 100, 50, 50, "files/redbutton.png");
	boolean instructions = false;
	boolean gameOver = false;
	boolean won = false;
	
	public MyCanvas() {
		this.setSize(1440,867);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.ActionListener(this);
		JPanel panel = new JPanel();
		this.add(panel);
		
		this.setBackground(Color.WHITE);
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i<14; i++) {
			Buttons bg = new Buttons(rand.nextInt(850)+60, rand.nextInt(600)+75, 50, 50, "files/download-2.png");
			System.out.println(i);
			Rectangle r = new Rectangle (100, 100, 100, 100);	
			buttons.add(bg);
		}
	}
				
	


	
		
	public void onTimer() {
			
			cup = 0;
			
			TimerTask repeatedTask = new TimerTask() {

		        public void run() {
		        	
		        	if (!won) {
		        		cup++;
		        	}
		        	repaint();
		        }

		    }; 

		    long delay  = 1000L;
		    long period = 1000L;

		    timer.scheduleAtFixedRate(repeatedTask, delay, period);	
		}
	

		





	private void ActionListener(MyCanvas myCanvas) {
		// TODO Auto-generated method stub
		
	}



	private void add(JPanel panel) {
		// TODO Auto-gnerated method stub
		
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

	
	
	public void mouseClicked(MouseEvent e) {
		
		Rectangle nr = new Rectangle(1000, 50, 400, 675);
		if (nr.contains(e.getX(), e.getY())) {
			instructions = true;
		repaint();
		}
		
		
		Rectangle br = new Rectangle(50, 60, 100, 25);
		if (br.contains(e.getX(), e.getY()) && !clicked) {
			onTimer();
			clicked = true;
		}
				
				
				playIt("files/Button.wav");	
		if (cup>0) {
			/*for(int i = 0;i < buttons.size(); i++) {
				Buttons bg = (Buttons) buttons.get(i);
				Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
				
				if (r.contains(e.getX(), e.getY()) && !bg.getbClicked()) {
					score = score + 1;
					
					
					playIt("files/Button.wav");
					if (!bgrtimer) {
						bgrtime = System.currentTimeMillis();
						bg.setImg("files/download-3.png");
						bgrtimer = true;
						bg.setbClicked(true);
						score = score + 1;
						buttons.set(i, bg);
						repaint();
					}
				}
				if (System.currentTimeMillis() - bgrtime >= 1000 && bg.getbClicked()) {
					buttons.remove(i);
					bgrtimer = false;
					bgrtime = 0;
					
					repaint();
				}
				
			}*/
			
			for (int i = 0; i<buttons.size(); i++) {
				Buttons bg = (Buttons) buttons.get(i);
				Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
				
				if (r.contains(e.getX(), e.getY()) && !bg.getbClicked()) {
					score = score + 1;
					playIt("files/Button.wav");
					//if (!bgrtimer) {
						bgrtime = cup;
						bg.setImg("files/download-3.png");
						//bgrtimer = true;
						bg.setbClicked(true);
						//score = score + 1;
						buttons.set(i, bg);
						repaint();
					//}
				}
				
				if (cup - bgrtime >= 1 && bg.getbClicked()) {
					buttons.remove(i);
					//bgrtimer = false;
					//bgrtime = 0;
					
					repaint();
				}
			}
		}
	}

	
		



	public void paint(Graphics g) {
		Font myFont = new Font ("Cardo", 1, 25);
		Font newFont = new Font ("Times New Roman", 1, 15);
		Font newerFont = new Font ("Times New Roman", 1, 20);
		Font endfont = new Font ("Courier New", 1, 50);
		if (gameOver) {
			g.setFont(endfont);
			g.drawString("Game Over", 580, 350);
			g.setFont(newerFont);
			g.drawString("Score: " + Integer.toString(score), 675, 425);
		}
		else if (won) {
			g.setFont(endfont);
			g.drawString("You Won!!!!", 580, 350);
			g.setFont(newerFont);
			g.drawString("Time Left: " + (20 - cup) + " seconds", 675, 425);
		}
		else{
			
			for (int i = 0; i<buttons.size(); i++) {
				Buttons bg = (Buttons) buttons.get(i);
				Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
				
				if (cup - bgrtime >= 1 && bg.getbClicked()) {
					buttons.remove(i);
					//bgrtimer = false;
					//bgrtime = 0;
					
					repaint();
				}
			}
			
			if (cup >= 20) {
			
				/*g.drawString("GAME OVER", 0, 0);
				try {
					Thread.sleep(290000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				gameOver = true;
				
			} else if (score >=11) {
				won = true;
					/*try {
					Thread.sleep(290000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			} else {
			g.setFont (myFont);
			g.drawString("Timer: " + (20-cup), 150, 30);
			g.drawString("Score: " + Integer.toString(score), 20, 30);
			g.drawRect(50, 60, 100, 25);
			g.setFont (newFont);
			g.drawString("Click for Timer", 50, 75);
			g.setFont(newerFont);
			g.drawString("Welcome to CogniReact! Click the timer button to start the timer, read instructions at the right before starting.", 350, 30);
			g.drawRect(1000, 50, 400, 675);
				for(int i = 0; i < buttons.size(); i++) {
				Buttons bg = (Buttons) buttons.get(i);
				g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
			if (instructions) {
				g.setFont(newerFont);
				g.drawString("The goal of the game is to clear all buttons", 1010, 105);
				g.drawString("on the screen within a set time frame.", 1010, 135);
				g.drawString("Instructions:", 1010, 75);
				g.drawString("Within 30 seconds, you must reach a score:", 1010, 195);
				g.drawString("of at least 10, then you win!", 1010, 225);
				g.drawString("If you fail to reach 10 score in 30 seconds", 1010, 285);
				g.drawString("then you'll need to try again.", 1010, 315);
				g.drawString("Click each button once to remove it from", 1010, 375);
				g.drawString("the screen and get one point.", 1010, 405);
				g.drawString("GOOD LUCK!", 1010, 465);
			}
			} 
			}	
		}
	}
	
		

	
	public void keyPressed(KeyEvent e) {
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


	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Rectangle nr = new Rectangle(1000, 50, 400, 675);
		if (nr.contains(e.getX(), e.getY())) {
			
		// TODO Auto-generated method stub
		
	}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
	}
	}
		

	/*private long startTime = 0;
	  private long stopTime = 0;
	  private boolean running = false;


	  public void start() {
	    this.startTime = System.currentTimeMillis();
	    this.running = true;
	  }


	  public void stop() {
	    this.stopTime = System.currentTimeMillis();
	    this.running = false;
	  }


	  //elapsed time in milliseconds
	  public long getElapsedTime() {
	    long elapsed;
	    if (running) {
	      elapsed = (System.currentTimeMillis() - startTime);
	    } else {
	      elapsed = (stopTime - startTime);
	    }
	    return elapsed;
	  }


	  //elapsed time in seconds
	  public long getElapsedTimeSecs() {
	    long elapsed;
	    if (running) {
	      elapsed = ((System.currentTimeMillis() - startTime) / 1000);
	    } else {
	      elapsed = ((stopTime - startTime) / 1000);
	    }
	    return elapsed;
	  }
	}*/
