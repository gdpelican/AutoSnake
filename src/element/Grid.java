package element;

import java.awt.*;
import java.util.*;
import tracker.*;

public class Grid {
	
	public static final int[] STARTX = {1, 1, 99, 99};
	public static final int[] STARTY = {1, 99, 1, 99};
	public static final Color[] COLOR = { Color.blue, Color.green, Color.orange, Color.cyan };
	
	private Collection<Explosion> explosions;
	
	private Tracker<Fruit> fruits;
	private Tracker<Snake> snakes;
	private Snake userSnake;
	
	private int width, height;
	
	public Grid(int width, int height, int snakeCount, int fruitAtATime) {
	
		this.width = width;
		this.height = height;
		
		fruits = new Tracker<Fruit>();
		snakes = new Tracker<Snake>();
		explosions = new ArrayList<Explosion>();
	
		for (int i = snakeCount - 1; i >= 0; i--)
			snakes.add(userSnake = new Snake(this, STARTX[i], STARTY[i], COLOR[i], i!=0));
		
		userSnake.setDirection(Direction.DOWN);
	
		for (int i = 0; i < fruitAtATime; i++)
			addFruit();
					
	}
	
	protected int width() { return width; }
	protected int height() { return height; }
	
	public Collection<Snake> getSnakes() { return snakes.getMembers(); }
	public Snake getUserSnake() { return userSnake; }
	
	public void animate() {
		for (Snake s : snakes.getMembers()) { 
			Thread t = new Thread(s);
			t.start();}
	}
	
	protected void addExplosion(Explosion e) { explosions.add(e); e.animate(); }
	public Collection<Explosion> getExplosions() { return explosions; } 
	
	public synchronized Collection<GridMember> getMembers() {
		Collection<GridMember> result = new ArrayList<GridMember>();
		
		result.addAll(fruits.getMembers());
		
		for(Snake s : snakes.getMembers())
			result.addAll(s.getSegments());
		
		return result;
	}
	
	protected synchronized Point getNearestFruit(Point position) {
		
		double min = Double.MAX_VALUE;
		Fruit closest = null;
		
		for (Fruit f : fruits.getMembers())
			if (f.distance(position) < min) {
				min = f.distance(position);
				closest = f; }
		
		return closest;
		
		
	}
	
	public synchronized boolean eat(int x, int y) {
		for (Fruit f : fruits.getMembers()) {
			if (f.getX() == x && f.getY() == y) {
				fruits.remove(f);
				
				addFruit();				
				return true;
			}
		}
		
		return false;
	}
	
	private synchronized void addFruit() {
		Point p = null;		
		boolean validSpot = true;
		
		do {
			p = new Point((int)(Math.random() * width), (int)(Math.random() * height));
			
			for (Fruit fr : fruits.getMembers())
				if (fr.getX() == p.x && fr.getY() == p.y) validSpot = false;
				
		} while(!validSpot);
		
		fruits.add(new Fruit(this, p));
		
	}
	
}
