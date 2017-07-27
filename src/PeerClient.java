import java.net.*;
import java.util.*;
import java.io.*;

import java.nio.*;


public class PeerClient implements Runnable{
    private ArrayList<Socket> pservers;
    private Thread [] ptservers;
    
    private int numPeers = 3;
    private String message;

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
	    while(pservers.size() < numPeers){
		if(count > numPeers) count = 0;
		line = peers.get(count).split(" ");
		if(Integer.parseInt(line[0]) != id){
		    if(!idconnected.contains(line[0])){
			System.out.println(id + " attempting to join peer at port " + Integer.parseInt(line[2]) + "...");
			if((sock = new Socket("localhost", Integer.parseInt(line[2]))) != null){
			    pservers.add(new Socket("localhost", Integer.parseInt(line[2])));
			    idconnected.add(Integer.parseInt(line[0]));
			    count++;
			} else {
			    Thread.sleep(3000);
			}
		    }
		}else{
		    count++;
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


    public void sendMessages(){
	try{
	    int n = 0;
	    message = id + ": hello others";
	    while(n++ < 30){
		Random rand = new Random();
		double d = rand.nextDouble();
		if(d >= 0.5) broadcast(message);
		Thread.sleep(3000);
	    }
	}catch(InterruptedException ie){
	    ie.printStackTrace();
	}
    }

    public void broadcast(String message){
	try{
	    for(Socket peer : pservers){
		PrintWriter out = new PrintWriter(peer.getOutputStream(), true);
		System.out.println("Sending message: " + message + " | to socket " + peer.getPort());
		out.println(message);
	    }
	} catch (IOException e){
	    e.printStackTrace();
	}
    }

    public void run(){
	try{
	    joinPeers();
	    Thread.sleep(((numPeers*1000)+5000) - (id*1000));
	    System.out.println("Pservers length for process " + id + " is " + pservers.size());


	    sendMessages();
	} catch (InterruptedException ex){
	    System.out.println("InterruptionException run");   
	}
    }
}	
