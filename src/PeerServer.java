import java.net.*;
import java.util.*;
import java.io.*;

import java.nio.*;

public class PeerServer implements Runnable{
    private ArrayList<Socket> pclients;
    private ArrayList<Thread> ptclients;
    private ArrayList<ReceiveClient> rcs;

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
	rcs = new ArrayList<ReceiveClient>();
	ptclients = new ArrayList<Thread>();
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

    public void setupReceive(){
	for(Socket s : pclients){
	    rcs.add(new ReceiveClient(id, s));
	}
	for(ReceiveClient rc : rcs){
	    ptclients.add(new Thread(rc));
	}
    }

    public void listen(){
	for(Thread t : ptclients){
	    t.start();
	}	    
    }

    public void storeMessages(){
	for(ReceiveClient rc : rcs){
	    rc.storeMessages();
	}
    }

    public void run(){	
        try{
	    startServer();
	    listenConnections();

	    Thread.sleep(15000);

	    setupReceive();
	    listen();
	    Thread.sleep(100000);
	    storeMessages();
	    
	    // printClients();
	} catch(InterruptedException ie){
	    System.out.println("Stuff happened");
	}
    }

    public void stopServer(){
	try{
	    pserver.close();
	} catch(IOException e){
	    e.printStackTrace();
	}
    }
}
