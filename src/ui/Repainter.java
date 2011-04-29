package ui;

import javax.swing.JPanel;

public class Repainter extends Thread {

	private JPanel panel;
	private int speed;
	
	public Repainter(JPanel panel, int speed) {
		this.panel = panel;
		this.speed = speed;
	}
	
	public void run() {
		while(true) {
			try {
				panel.repaint();
				sleep(speed);}
			catch (Exception e) { e.printStackTrace(); }
		}
	}
	
}
