package element;

import java.awt.Color;
import java.awt.Point;


@SuppressWarnings("serial")
public abstract class GridMember extends Point {
	
	private Color color;

	protected Grid grid;
	
	public GridMember(Grid grid, Point position) {
		super(position.x, position.y);
		this.grid = grid;
	}
	
	public GridMember(Grid grid, int x, int y) {
		super(x, y);
		this.grid = grid;
	}
	
	public int x() { return (int)x; }
	public int y() { return (int)y; }
	
	public Color getColor() { return color; }
	protected void setColor(Color color) { this.color = color; }

	public abstract int getType();
	
}
