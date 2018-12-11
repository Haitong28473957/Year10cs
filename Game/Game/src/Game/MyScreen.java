package Game;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyScreen extends JFrame {
	public MyScreen() {
		this.setSize(1440,867);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		MyScreen screen = new MyScreen();
		MyCanvas canvas = new MyCanvas();
		screen.getContentPane().add(canvas);
	}
}
