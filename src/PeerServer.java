package p2p;

import java.net.*;
import java.util.*;
import java.io.*;

import java.nio.*;

public class PeerServer implements Runnable{
    private ArrayList<Socket> pclients;
    private Thread [] ptclients;

    private String address;
    private int port;

    private ServerSocket pserver;

    private boolean running = true;

    public PeerServer(String address, int port){
	this.address = address;
	this.port = port;
	pclients = new ArrayList();
    }

    public void startServer(){
	pserver = new ServerSocket(port);	
    }

    public void listenConnections(){
	Socket pcsock;
	while(pclients.size() < 9){
	    if((pcsock = pserver.accept()) != null){
		pclients.add(pcsock);
	    }
	}
    }

    public void printClients(){
	for(Socket psock : pclients){
	    System.out.println("Socket with port: " + psock.getPort());
	}
    }

    public void listen(){
	while(true){

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
