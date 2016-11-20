package test;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundMapPanel extends JPanel{
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("/Users/martinaldrin/git/gui-test/src/main/resource/worldmap.jpg", "MAP");
		g.drawImage(icon.getImage(), 0, 0, this);
		
	}
}
