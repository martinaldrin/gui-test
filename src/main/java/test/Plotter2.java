package test;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Plotter2 extends JPanel implements Runnable {
	private final BlockingQueue messageQueue = new ArrayBlockingQueue(2048);
	ImageIcon worldMapImage = getBackgroundImage();

	public void run() {
		JFrame frame = startSwing();
		while (true) {
			Data message = take();
			//frame.setIconImage(getImage(1, 0, 0));
			System.out.println("Plotter: " + message.toString());

		}
	}

	private JFrame startSwing() {
		
		final JFrame frame = new JFrame();
		//frame.getContentPane().add(getIcon(1,20,120));
		frame.add(new Plotter());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
		return frame;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (worldMapImage != null) {
			g.drawImage(worldMapImage.getImage(), 0, 0, this);
		}
	}

	private Data take() {
		try {
			Data message = (Data) messageQueue.take();
			return message;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addMessage(Data message) {
		messageQueue.add(message);
	}

	private ImageIcon getBackgroundImage() {
		ImageIcon icon = new ImageIcon("/Users/martinaldrin/git/gui-test/src/main/resource/worldmap.jpg", "MAP");
		return icon;
	}
	private Image getImage(int type, int x, int y) {
		Path path = Paths.get("/Users/martinaldrin/git/gui-test/src/main/resource/");
		switch (type) {
		case 1:
			path = path.resolve("red.jpg");
			break;
		case 2:
			path = path.resolve("blue.jpg");
			break;
		case 3:
			path = path.resolve("green.jpg");
			break;
		default:
			//throw...
			break;
		}
		ImageIcon icon = new ImageIcon("/Users/martinaldrin/git/gui-test/src/main/resource/worldmap.jpg", "MAP");
		return icon.getImage();
	}
	
	private IconWithPosition getIcon(int type, int x, int y) {
		IconWithPosition panel = null;
		Path path = Paths.get("/Users/martinaldrin/git/gui-test/src/main/resource/");
		switch (type) {
		case 1:
			path = path.resolve("red.jpg");
			break;
		case 2:
			path = path.resolve("blue.jpg");
			break;
		case 3:
			path = path.resolve("green.jpg");
			break;
		default:
			//throw...
			break;
		}
		try {
			panel = new IconWithPosition(path.toString(),x,y);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return panel;
	}

	public class IconWithPosition extends JPanel {
		private Image backgroundImage;
		private int x = 0;
		private int y = 0;

		public IconWithPosition(String fileName, int x, int y) throws IOException {
			this.backgroundImage = ImageIO.read(new File(fileName));
			this.x = x;
			this.y = y;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, x, y, this);
		}
	}
}
