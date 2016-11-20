package test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame implements Runnable {
	private final MessageQueue messageQueue;
	private static JFrame frame = new JFrame();

	public Frame(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}

	public void startFront() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				frame.add(new BackgroundMapPanel());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400, 400);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});
	}

	public void run() {
		while (true) {
			Data message = messageQueue.take();
			if(message != null){
			frame.add(new ObjektPanel(message.type, message.x, message.y));
			frame.repaint();
			System.out.println("Frame: " + message.toString());
			}
		}
	}

}
