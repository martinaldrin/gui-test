package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Client {
	private static final String serverAddress = "127.0.0.1";
	 
	public static void main(String[] args) throws IOException {
		Plotter plotter = startPlotter();
		Socket s = new Socket(serverAddress, 9090);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		while (s.isConnected()) {
			String answer = input.readLine();
			Data message = getData(answer);
			plotter.addMessage(message);
			System.out.println(message.toString());	
		}
		s.close();
		System.exit(0);
	}

	private static Plotter startPlotter(){
		Plotter plotter = new Plotter();
		Thread t1 = new Thread(plotter);
	    t1.start();
	    return plotter;
	}
	
	static Data getData(String input){
		return new Data(input);
	}
	
	
}
