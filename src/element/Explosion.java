package element;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.*;

public class Explosion {
	
	public static final int SPEED = 50;

	private Collection<Shrapnel> points;
	private Color color;
	
	public Explosion(Grid grid, Collection<Segment> segments, Color color) {
		
		points = new ArrayList<Shrapnel>();
		
		this.color = color;
		
		for (Point p : segments) {
			int speed = (int)(SPEED + (Math.random() * SPEED - (SPEED / 2)));
			this.points.add(new Shrapnel(p, 0, 0, grid.width(), grid.height(), speed));
		}
		
	}
		
	public Collection<Point2D.Double> getPoints() { 
		Collection<Point2D.Double> result = new LinkedList<Point2D.Double>();
		
		for (Shrapnel s : points)
			result.add(new Point2D.Double(s.getX(), s.getY()));
		
		return result;
	}
	
	public Color getColor() { return color; }
	
	public void animate() {
		for (Shrapnel s : points) {
			Thread t = new Thread(s);
			t.start();}
	}
	
	@SuppressWarnings("serial")
	private class Shrapnel extends Point2D.Double implements Runnable {
		
		private Point2D.Double vector;
		private int minX, minY, maxX, maxY;
		private int speed;
		
		public Shrapnel(Point p, int minX, int minY, int maxX, int maxY, int speed) {
			super(p.x, p.y);
			this.maxX = maxX; this.minX = minX;
			this.maxY = maxY; this.minY = minY;
			this.speed = speed;
			
			vector = new Point2D.Double((Math.random() * 2) - 1, (Math.random() * 2) - 1);
		}
		
		public void run() {
			
			do {
				try {
				setLocation(x + vector.x, y + vector.y);						
				Thread.currentThread();
				Thread.sleep(speed);} 
				catch (Exception e) { e.printStackTrace(); }
				
				
			} while (x >= minX - 1 && x <= maxX + 1 && y >= minY - 1 && y <= maxY + 1);
			
		}
				
	}
	
}
