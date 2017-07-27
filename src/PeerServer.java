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
	    System.out.println("Server " + id + ": Socket on port: " + psock.getLocalPort());
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
	    Thread.sleep(13000 - (id*1000));

	    setupReceive();
	    System.out.println("Socket length for process " + id + " is " + pclients.size());
	    System.out.println("Thread length for process " + id + " is " + ptclients.size());
	    System.out.println("ReceiveClient length for process " + id + " is " + rcs.size());


	    listen();
	    Thread.sleep(50000);
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
