import java.net.*;
import java.util.*;
import java.io.*;

import java.nio.*;


public class PeerClient implements Runnable{
    private ArrayList<Socket> pservers;
    private Thread [] ptservers;

    private ArrayList<String> peers;

    private Socket clientsock;
    private ArrayList<Integer> idconnected;

    private int id;
    private String [] servers;
    private Thread t;

    public PeerClient(int id, ArrayList<String> peers){
	t = Thread.currentThread();
	this.id = id;
	this.peers = peers;
	pservers = new ArrayList<Socket>();
	idconnected = new ArrayList<Integer>();
    }


    public void joinPeers(){
	try{
	    Socket sock = null;
	    String [] line;
	    int count = 0;
	    idconnected.add(this.id);
	    Thread.sleep(1000*id);
	    while(pservers.size() < 9){
		if(count >= peers.size()) count = 0;
		line = peers.get(count).split(" "); 
		if(!idconnected.contains(line[0])){
		    System.out.println("Attempting to join peer at port " + Integer.parseInt(line[2]) + "...");
		    if((sock = new Socket(line[1], Integer.parseInt(line[2]))) != null){
			pservers.add(new Socket(line[1], Integer.parseInt(line[2])));
			count++;
		    } else {
			Thread.sleep(3000);
		    }
		}
	    }
	} catch (IOException io){
	    System.out.println(id + " IOException joinPeers in thread: " + t.getName());
	    io.printStackTrace();
	} catch (InterruptedException ie){
	    System.out.println(id + " InterruptionException joinPeers in thread: " + t.getName());
	    ie.printStackTrace();
	}
    }

    public void broadcast(String message){
	for(Socket peer : pservers){
	    // TODO: send message
	    // TODO: wait for response
	}
    }

    public void run(){
	joinPeers();
    }
}	
