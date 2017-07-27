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

    private int id;
    private boolean running = true;

    public PeerServer(String address, int port, int id){
	this.id = id;
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
	    while(pclients.size() < 9){
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
	    System.out.println("Server " + id + ": Socket with port: " + psock.getLocalPort());
	}
    }

    public void listen(){
	while(true){

	}	
    }

    public void run(){	
        try{
	    startServer();
	    listenConnections();
	
	    System.out.println("Press any key to continue...");
	    System.in.read();
	    Thread.sleep(15000);
	   // printClients();
	} catch(InterruptedException ie){
	    System.out.println("Stuff happened");
	} catch (IOException e){
	    e.printStackTrace();
	}
    }

    public void stopServer() throws IOException{
	pserver.close();
    }
}
