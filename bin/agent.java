import java.net.Socket;
import java.util.*;

class Agent implements Runnable {
	private Thread t;

	private int pid;
	private InetAddress host;
	private int port;
	private int vectorTime = 0;

	/*
	 * sinceLastMsg - holds time since last message
	 * elapsedTime - holds time compared to sinceLastMsg
	 * Use to determine when to move messageQueue to messageHoldQueue
	 */
	private long sinceLastMsg;
	private long elapsedTime; 


	// messageQueue - holds messages being received
	// 		  sends messages to messageHoldQueue when after x time
	// broadcastQueue - holds messages to send, in case of some holdup
	// messageHoldQueue - holds messages to be displayed
	private Queue messageQueue;
	private Queue broadcastQueue;
	private Queue messageHoldQueue;

	// connectedClients - clients connected to agent
	private ArrayList<Agent> connectedClients;

	// clientTimes - dynamic array of clients vector clocks
	private ArrayList<int> clientTimes;

	public static Agent(int pid, InetAddress host, int port){
		this.pid = pid;
		this.host = host;
		this.port = port;
	}
	

	public static void send(){
		private long time = System.nanoTime();
		private string message = "<" + pid + ", Hello
	}

	public static void receive(){
	
	}
}
