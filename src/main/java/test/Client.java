package main.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	private static final String serverAddress = "127.0.0.1";

	public static void main(String[] args) throws IOException {
		Socket s = new Socket(serverAddress, 9090);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		while (s.isConnected()) {
			String answer = input.readLine();
			System.out.println(getData(answer).toString());	
		}
		System.exit(0);
	}

	static Data getData(String input){
		return new Data(input);
	}
	
	
}
