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
	pclients = new ArrayList<Socket>();
    }

    public void startServer(){
	try{
	    pserver = new ServerSocket(port);
	} catch (IOException e){
	    System.out.println("IOException startServer");
	}
    }

    public void listenConnections(){
	try{
	    Socket pcsock = null;
	    while(pclients.size() <= 9){
		if((pcsock = pserver.accept()) != null){
		    pclients.add(pcsock);
		}
	    }
	} catch (IOException e){
	    System.out.println("IOException in listenConnections");
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
        startServer();
	listenConnections();
    }

    public void stopServer() throws IOException{
	pserver.close();
    }
}
