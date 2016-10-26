package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class Server {
	private static Random randomGenerator = new Random();

	Server() {

	}

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		boolean connected = true;
		try {
			while (connected) {
				Socket socket = listener.accept();
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					while (connected) {
						out.println("ID=" + getId() + ";X=" + getRandomValue() + ";Y=" + getRandomValue() + ";TYPE="
								+ getType());
						connected = socket.isConnected();
						
						randomSleep();
					}
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}

	private static String getId() {
		List<String> ids = Lists.newArrayList("1111111111111111","2222222222222222","3333333333333333");
		int index = randomGenerator.nextInt(ids.size());
		return ids.get(index);
	}

	private static void randomSleep() {
		int time =  100 + (int) (Math.random() * 1000);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static int getRandomValue() {
		return 1 + (int) (Math.random() * 255);
	}

	private static int getType() {
		return 1 + (int) (Math.random() * 3);
	}
}
