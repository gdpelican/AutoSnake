package element;


import java.util.*;
import java.awt.*;

import static element.Direction.*;
import static ui.GridPanel.FRUIT;

public class Snake implements Runnable {

	public static final int SPEED = 50;
	public static final int STARTLENGTH = 5;
	
	private Grid grid;
	private Collection<GridMember> members;
	private Queue<Segment> segments;
	private Point position, direction;
	private int length;
	private boolean alive, cpu;
	private Color color;
	
	public Snake(Grid grid, int x, int y, Color color, boolean cpu) {
		
		this.grid = grid;
		position = new Point(x, y);
		this.color = color;
		this.cpu = cpu;
		
		segments = new LinkedList<Segment>();
		segments.add(new Segment(grid, position.x, position.y, color));
		
		length = STARTLENGTH;
		direction = Direction.getDirection(NONE);
	}
	
	public synchronized void move() { 
		getDirection();
		position.translate(direction.x, direction.y);
		
		boolean goodMove = true;
		
		if (position.x < 0 ||
				position.y < 0 ||
				position.x > grid.width() ||
				position.y > grid.height())
				goodMove = false;
		
		for (GridMember m : members) {
			if (m.x() == position.x && m.y() == position.y) {
				if (m.getType() == FRUIT) {
					grid.eat(position.x, position.y);
					length++;}
				else
					goodMove = false;
			}
		}
		
		if (goodMove)
			segments.add(new Segment(grid, position.x, position.y, color));
		else
			explode();
		
		while(segments.size() > length)
			segments.poll();
		
	}
	
	private void explode() {
		grid.addExplosion(new Explosion(grid, segments, color));
		
		segments.clear();
		kill();
	}
	
	public int getLength() { return length - STARTLENGTH; }
	public Color getColor() { return color; }
	
	protected void getDirection() {
		
		if (cpu) {
		
			Point[] preference = new Point[4];
			Direction yDir, xDir;
			Point closest = (grid.getNearestFruit(position));
			
			int dx = closest.x - position.x;
			int dy = closest.y - position.y;
			
			if (dx > 0) xDir = Direction.getDirection(RIGHT);
			else		xDir = Direction.getDirection(LEFT);
			if (dy > 0) yDir = Direction.getDirection(DOWN);
			else		yDir = Direction.getDirection(UP);
			
			if (Math.abs(dx) > Math.abs(dy)) {
				Point[] temp = { xDir, yDir, yDir.opposite(), xDir.opposite() };
				preference = temp; }
			else {
				Point[] temp = { yDir, xDir, xDir.opposite(), yDir.opposite() };
				preference = temp; }
			
			int i = 0;
			
			do 		{ direction = preference[i]; i++; }
			while 	(!validDirection() && i < preference.length);

		}
		
	}	
	
	private boolean validDirection() {	
		
		boolean result = true;
		
		for (GridMember g : members)
			if (g.x == position.x + direction.x && 
				g.y == position.y + direction.y &&
				g.getType() != FRUIT) 
			result = false; 
			
		return result;
	}
	
	public void setDirection(int direction) { this.direction = Direction.getDirection(direction); }
	
	public Collection<Segment> getSegments() { return segments; }
	
	public void run() {
		alive = true;
		
		do {
			try { 	
					members = grid.getMembers();
					move();
					Thread.currentThread();
					Thread.sleep(SPEED);}
		
			catch (Exception e) { e.printStackTrace(); }
		}
		
		while (alive);
		
	}
	
	public void kill() { alive = false; }
		
}
