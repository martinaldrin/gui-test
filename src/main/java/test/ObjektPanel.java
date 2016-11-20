package test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ObjektPanel extends JPanel{
	private final Color color;
	private final int x;
	private final int y;
	ObjektPanel(int type, int x, int y){
		switch (type) {
		case 1:
			this.color = Color.GREEN;
			break;
		case 2:
			this.color = Color.RED;
			break;
		case 3:
			this.color = Color.BLUE;
			break;

		default:
			color = Color.GRAY;
			break;
		}
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillRoundRect(x, y, 10, 10,5,5);
	}
	
	
}
