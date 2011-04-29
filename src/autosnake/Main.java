package autosnake;

import javax.swing.*;
import ui.*;


public class Main {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	public static final int CUBESIZE = 7;
	
	public static final int SNAKECOUNT = 4;
	public static final int FRUITATATIME = 3;
	public static final int REFRESHSPEED = 25;
	public static JFrame frame;
	
	public static void main(String... args) {
		
		GridPanel panel = new GridPanel(WIDTH, HEIGHT, CUBESIZE, REFRESHSPEED, SNAKECOUNT, FRUITATATIME);
		UI ui = panel.getInterface();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		frame.addKeyListener(ui);
		
		frame.pack();
		frame.setVisible(true);
		
		panel.animate();
		
	}
	
	public static void restart() {
		
		frame.dispose();
		GridPanel panel = new GridPanel(WIDTH, HEIGHT, CUBESIZE, REFRESHSPEED, SNAKECOUNT, FRUITATATIME);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
}
