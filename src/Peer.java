package p2p.project;

import java.net.*;
import java.util.*;
import java.io.*;

public class Peer implements Runnable{
	private String filename = "../setup.txt";
	private ArrayList<String> fservers;

	private Thread t;

	private Socket[] peers;

	private ServerSocket serverSocket;
	private Socket psock;

	private PeerServer pserver;
	private PeerClient pclient;


	private int id;
	private String address;
	private int port;
	private Boolean connected;
	private String message;
	private Queue<String> messages;

	public Peers(String address, int port, int id, Boolean connected){
		this.address = address;
		this.port = port;
		this.id = id;
		this.connected = connected;
	}

	public void init(){
		address = "localhost";
		port = 5000 + (id-1);

		readFromFile();
		pserver = new PeerServer(address, port);
		pclient = new PeerClient();
	}


	// GET SETS
	public int getID(){return id;}
	public String getAddress(){return address;}
	public int getPort(){return port;}

	// Deprecated, maybe
	public Boolean getConnected(){return connected;}
	public Boolean setConnected(){connected = true;}

	
	// Reading servers from file
	public void readFromFile(){
		FileReader fread = new FileReader(filename);
		BufferedReader bread = new BufferedReader(fread);
		String line = null;

		while((line = bread.readLine()) != null){
			fservers.add(line);
		}
		bread.close();
	}

	public void connections(){
		Thread tlisten = new Thread(plisten);
		Thread tconnect = new Thread(pconnect);
		tlisten.start();
		tconnect.start();
	}

	public void plisten(){
		try{	
			pserver.listen();	
		}
	}

	public void pconnect(){
		try{
			
		}
	}

	public void run(){
	}

	

	public static void main(String args[]){
		if(args.length != 1){
			System.out.println("Invalid number of arguments");
			System.exit();
		}
		id = Integer.ParseInt(args[0]);
		init();
		connections();
		run();
	}

}

public class PeerServer implement Runnable{
	private ArrayList pclients;
	private Thread [] ptclients;

	private String address;
	private int port;

	private ServerSocket pserver;

	public PeerServer(String address, int port){
		this.address = address;
		this.port = port;
		pclients = new ArrayList();
	}

	public void startServer(){
		pserver = new ServerSocket(port);	
	}

	public void listen(){
		try{
			while(true){
				
			}	
		}
	}

	public void run(){
		while(true){

		}
	}

	public void stopServer(){
		pserver.close();
	}
}

public class PeerClient implements Runnable{
	private ArrayList<Socket> pservers;
	private Thread [] ptservers;

	private ArrayList slist;

	private int id;
	private String [] servers;

	public Client(int id, String [] servers){
		this.id = id;
		this.servers = servers;
		pservers = new ArrayList();
	}
	

	public void joinPeers(ArrayList<String> peers){
		
	}

	public void broadcast(String message){
		
	}
}	
