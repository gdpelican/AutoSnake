package element;

import java.awt.Color;
import java.awt.Point;

import static ui.GridPanel.*;


@SuppressWarnings("serial")
public class Fruit extends GridMember {

	public static final Color COLOR = Color.red;
	
	public Fruit(Grid parent, Point p) {
		super(parent, p);
		setColor(COLOR);
	}
	
	public Fruit(Grid parent, int x, int y) {
		super(parent, x, y);
		setColor(COLOR);
	}
	
	@Override
	public Color getColor() { return Color.red; }
	
	@Override
	public int getType() { return FRUIT; }
	
}
