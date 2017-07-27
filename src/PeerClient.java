package p2p;

import java.net.*;
import java.util.*;
import java.io.*;

import java.nio.*;


public class PeerClient implements Runnable{
    private ArrayList<Socket> pservers;
    private Thread [] ptservers;

    private Socket clientsock;
    private ArrayList<Integer> idconnected;

    private int id;
    private String [] servers;

    public PeerClient(int id, Socket csock){
	this.id = id;
	clientsock = csock;
	pservers = new ArrayList();
    }


    public void joinPeers(ArrayList<String> peers){
	String [] line;
	int count = 0;
	idconnected.add(this.id);
	while(pservers.size() < 9){
	    if(count > peer.size()) count = 0;
	    line = peers[count].split(" "); 
	    if(!idconnected.contains()){
		//TODO: connect
		count++;
	    }
	}	    
    }

    public void broadcast(String message){
	for(Socket peer : pservers){
	    // TODO: send message
	    // TODO: wait for response
	}
    }
}	
