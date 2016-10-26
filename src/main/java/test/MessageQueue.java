package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
	private final BlockingQueue messageQueue = new ArrayBlockingQueue(2048);
}
