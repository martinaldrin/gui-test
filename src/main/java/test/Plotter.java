package test;

import java.awt.Graphics;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Plotter extends JPanel implements Runnable {
	private final BlockingQueue messageQueue = new ArrayBlockingQueue(2048);
	ImageIcon worldMapImage = getIcon();
	
	public void run() {
		startSwing();
		while(true){
			Data message = take();
			System.out.println("Plotter: " + message.toString());
		}
	}
	private void startSwing(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
		        frame.add(new Plotter());
		        frame.setIconImage(worldMapImage.getImage());
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(400, 400);
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
			}
		});
	} 
	@Override
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (worldMapImage != null) {
	      g.drawImage(worldMapImage.getImage(), 0, 0, this);
	    }
	  }

	private Data take(){
		try {
			Data message = (Data) messageQueue.take();
			return message;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void addMessage(Data message){
		messageQueue.add(message);
	}
	
	
	private ImageIcon getIcon(){		
		ImageIcon icon = new ImageIcon("worldmap.gif","MAP");
		return icon;
	}
	
	
}
