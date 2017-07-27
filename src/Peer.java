package p2p;

import java.net.*;
import java.util.*;
import java.io.*;

import java.nio.*;

import p2p.PeerServer;
import p2p.PeerClient;

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

    public Peer(String address, int port, int id, Boolean connected){
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
	pclient = new PeerClient(this.id);
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

    // Connecting to peers
    public void connections(){
	Thread tlisten = new Thread(this,plisten);
	Thread tconnect = new Thread(this,pconnect);
	tlisten.start();
	tconnect.start();
    }

    // Listening for peers to connect
    public void plisten(){
	pserver.listen();
    }

    // Connecting to peers
    public void pconnect(){
	pclient.connect();
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
