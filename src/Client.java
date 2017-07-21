import java.io.*;
import java.net.*;
import java.net.Socket;


/**     TODO
 *      -change general structure to match rubric
 *          -change how it is started (need to enter process ID as input argument)
 *              ./a.out 1
 *          -50% chance per second of sending a message
 *          -record each message ent in "processID_msg.txt"
 *          -rearrange Client
 *              -currently it alters message as opposed to simply receiving them
 *      -create 10 processes (currently just one)
 *          -read from "setup.txt"
 *      -causal ordering
 *      -Birman-Schiper-Stephenson (BSS) Protocol
 */



class Client {
	private int vectortime[];
	private int pid;

    	public static void main(String argv[]) throws Exception { //don't know why its String argv[] vs String args[]
		if(argv.length < 1){
			throw new IllegalArgumentException("Client requires 1 argument!\nUsage: java client.java {PID#}\n");
		}
		try {
			pid = Integer.ParseInt(argv[0]);
		}
		catch(NumberFormatException nfe){
			
		}
	}

	public static void startSocket(){}
	
	public static void stopSocket(){}

	public static void run(){
		String sentence;    //what the user enters
        	String modifiedSentence;    //user + the server's response
        	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));   //reads input, becomes sentence
        	Socket clientSocket = new Socket("localhost", 6789);    //create socket
        	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());    //responsible for sending to server
        	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //returns server's response
        	sentence = inFromUser.readLine();   //what the user enters
        	outToServer.writeBytes(sentence + '\n');    //writes what the client wrote on the server's side
        	modifiedSentence = inFromServer.readLine();    //server's response
        	System.out.println("FROM SERVER: " + modifiedSentence);     //print server's response
        	clientSocket.close();   //finished

	}

    	public static void init(){}
}

