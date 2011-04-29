package ui;

import java.awt.event.*;

import autosnake.Main;

import element.Snake;
import static element.Direction.*;

public class UI implements KeyListener, MouseListener {

	private Snake userSnake;
	
	public UI(Snake userSnake) { this.userSnake = userSnake; }

	@Override
	public void keyPressed(KeyEvent key) { 		
		switch(key.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:		userSnake.setDirection(LEFT); break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:		userSnake.setDirection(RIGHT); break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:		userSnake.setDirection(UP); break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:		userSnake.setDirection(DOWN); break; 
		case KeyEvent.VK_R:		Main.restart();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
