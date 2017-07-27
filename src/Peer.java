import java.net.*;
import java.util.*;
import java.io.*;

import java.nio.*;

public class Peer implements Runnable{
    private static String filename = "../setup.txt";
    private static ArrayList<String> fservers;

    private Thread t;

    private Socket[] peers;

    private static ServerSocket serverSocket;
    private static Socket psock;

    private static PeerServer pserver;
    private static PeerClient pclient;


    private static int id;
    private static String address;
    private static int port;
    private Boolean connected;
    private String message;
    private Queue<String> messages;

    public Peer(String address, int port, int id, Boolean connected){
	this.address = address;
	this.port = port;
	this.id = id;
	this.connected = connected;
    }

    public static void init(){
	address = "localhost";
	port = 5000 + (id-1);
	
	fservers = new ArrayList<String>();
	readFromFile();
	pserver = new PeerServer(address, port, id);
	pclient = new PeerClient(id, fservers);
    }


    // GET SETS
    public int getID(){return id;}
    public String getAddress(){return address;}
    public int getPort(){return port;}

    // Deprecated, maybe
    public Boolean getConnected(){return connected;}
    public void setConnected(){connected = true;}


    // Reading servers from file
    public static void readFromFile(){
	try{
	    FileReader fread = new FileReader(filename);
	    BufferedReader bread = new BufferedReader(fread);
	    String line = null;

	    while((line = bread.readLine()) != null){
		fservers.add(line);
	    }
	    bread.close();
	} catch(FileNotFoundException fnfe) {
	    System.out.println("File not found");
	} catch(IOException ioe){
	    System.out.println("IOException");
	}
    }

    // Connecting to peers
    public static void connections(){
	Thread tclient = new Thread(pclient);
	Thread tserver = new Thread(pserver);
	
	tclient.setName("Client Thread id: " + id);
	tserver.setName("Server Thread id: " + id);
	tserver.start();	
	tclient.start();
    }

    // Listening for peers to connect
    public void plisten(){
    }

    // Connecting to peers
    public void pconnect(){
    }

    @Override
    public void run(){
    }



    public static void main(String args[]){
	if(args.length != 1){
	    System.out.println("Invalid number of arguments");
	    System.exit(1);
	}
	id = Integer.parseInt(args[0]);
	init();
	connections();
    }
}
