package p2p.project;

import java.net.*;
import java.util.*;
import java.io.*;

public class Peer implements Runnable{
	private Thread t;

	private Socket[] peers;

	private ServerSocket serverSocket;
	private Socket psock;


	private int id;
	private String address;
	private int port;
	private Boolean connected;
	private String message;
	private String [] messages;

	public Peers(String address, int port, int id, Boolean connected){
		this.address = address;
		this.port = port;
		this.id = id;
		this.connected = connected;
	}

	public void init(){
		address = "localhost"
			port = 5000 + (id-1);
		serverSocket = new ServerSocket(port);
	}

	public int getID(){return id;}
	public String getAddress(){return address;}
	public int getPort(){return port;}
	public Boolean getConnected(){return connected;}
	public Boolean setConnected(){connected = true;}

	public void connections(){
		Thread tlisten = new Thread(plisten);
		Thread tconnect = new Thread(pconnect);
		tlisten.start();
		tconnect.start();
	}

	public void plisten(){
		try{
			serverSocket = new ServerSocket(port);

		}
	}

	public void pconnect(){

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
		while(pclients.size() < 9){
			
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
	private ArrayList pservers;
	private Thread [] ptservers;

	private int id;
	private String [] servers;

	public Client(int id, String [] servers){
		this.id = id;
		this.servers = servers;
	}

	public void joinPeers(){
		
	}

	public void broadcast(){
		
	}
}
