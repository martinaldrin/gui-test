package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
	private final BlockingQueue messageQueue = new ArrayBlockingQueue(2048);

	public Data take(){
		try {
			return (Data)messageQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void add(Data data){
		try {
			messageQueue.put(data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
