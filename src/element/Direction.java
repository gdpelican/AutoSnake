package element;

import java.awt.Point;

@SuppressWarnings("serial")
public class Direction extends Point {

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int NONE = 4;

	private static final Direction U 	= new Direction(0, -1);
	private static final Direction D 	= new Direction(0, 1);
	private static final Direction L 	= new Direction(-1, 0);
	private static final Direction R 	= new Direction(1, 0);
	private static final Direction N 	= new Direction(0, 0);
	
	
	public static Direction getDirection(int type) {
		switch(type) {
		case UP: 		return U;
		case DOWN: 		return D; 
		case LEFT: 		return L; 
		case RIGHT: 	return R;
		default:		return N;}
	}
	
	private Direction(int x, int y) {
		super(x, y);}
	
	public Direction opposite() { return new Direction( -1 * x, -1 * y); }
	
}
