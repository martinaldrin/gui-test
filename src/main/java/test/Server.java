package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
	private static Random randomGenerator = new Random();

	Server() {

	}

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		boolean connected = true;
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					while (connected) {
						out.println("ID=" + getId() + ";X=" + getRandomValue() + ";Y=" + getRandomValue() + ";TYPE="
								+ getType());
						connected = socket.isConnected();
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
		List<String> ids = new ArrayList<String>();
		ids.add("2691882127991893");
		ids.add("2691882127234543");
		ids.add("2691882127221587");
		int index = randomGenerator.nextInt(ids.size());
		return ids.get(index);
	}

	private static int getRandomValue() {
		return 1 + (int) (Math.random() * 255);
	}

	private static int getType() {
		return 1 + (int) (Math.random() * 3);
	}
}
