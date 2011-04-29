package ui;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import element.*;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {
	
	public static final int FRUIT = 0;
	public static final int SEGMENT = 1;
	public static final int WALL = 2;
	
	private int width, height, cubeSize;
	private Grid grid;
	private Repainter repainter;
	
	public GridPanel(int width, int height, int cubeSize, int refreshSpeed, int snakeCount, int fruitAtATime) {
		
		this.width = width;
		this.height = height;
		this.cubeSize = cubeSize;
		
		grid = new Grid(width, height, snakeCount, fruitAtATime);
		repainter = new Repainter(this, refreshSpeed);
		
		setSize(width * cubeSize, height * cubeSize);
		setPreferredSize(new Dimension(width * cubeSize, height * cubeSize));
		
	}
	
	public UI getInterface() {
		return new UI(grid.getUserSnake());
	}
	
	public void animate() {
		grid.animate();
		repainter.start();
	}
		
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(new Color(0, 125, 0));
		for (int i = 0; i < width; i++)
			g.drawLine(0, i * cubeSize, getHeight(), i * cubeSize);
		for (int i = 0; i < height; i++)
			g.drawLine(i * cubeSize, 0, i * cubeSize, getWidth());
				
		for (GridMember m : grid.getMembers()) {
			g.setColor(m.getColor());
			switch (m.getType()) {
			case FRUIT: 
				g.fillOval((m.x() * cubeSize) + 1, (m.y() * cubeSize) + 1, cubeSize - 2, cubeSize - 2); break;
			case SEGMENT:
			case WALL: 
				g.fill3DRect(m.x() * cubeSize, m.y() * cubeSize, cubeSize, cubeSize, false); break; 
			}
		}
		
		for (Explosion e : grid.getExplosions()) {
			g.setColor(e.getColor());
			
			for (Point2D.Double p : e.getPoints())
				g.fill3DRect((int) (p.x * cubeSize), (int)(p.y * cubeSize), cubeSize, cubeSize, false);
			
		}
		
		Point pos = new Point(10, 10);
		for (Snake s : grid.getSnakes()) {
			g.setColor(Color.gray);
			g.drawString("Eaten: " + s.getLength(), pos.x + 1, pos.y + 1);
			g.drawString("Eaten: " + s.getLength(), pos.x - 1, pos.y - 1);
			g.setColor(s.getColor());
			g.drawString("Eaten: " + s.getLength(), pos.x, pos.y);
			pos.translate(0, 10);
		}
		
	}
	
}
