package element;

import java.awt.*;
import static ui.GridPanel.*;

@SuppressWarnings("serial")
public class Segment extends GridMember {

	private Color color;
	
	public Segment(Grid grid, int x, int y, Color color) {
		super(grid, x, y);
		this.color = color;}
	
	public Segment(Grid grid, Point p, Color color) {
		super(grid, p);
		this.color = color;}
	
	@Override
	public Color getColor() { return color; }
	
	@Override
	public int getType() { return SEGMENT; }

}
