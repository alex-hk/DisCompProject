package p2p.project;

import java.net.Socket;

public class Peers implements Runnable{
	private Thread t;
	
	private Socket[] peers;

	private int id;
	private String address;
	private int port;
	private Boolean connected;

	public Peers(String address, int port, int id, Boolean connected){
		this.address = address;
		this.port = port;
		this.id = id;
		this.connected = connected;
	}

	public void init(){
		
	}

	public int getID(){return id;}
	public String getAddress(){return address;}
	public int getPort(){return port;}
	public Boolean getConnected(){return connected;}
	public Boolean setConnected(){connected = true;}

	public void connections(){

	}

	public void plisten(){
	
	}

	public void pconnect(){
	
	}

	public void run(){
	
	}

	public static void main(String args[]){
		init();
		connections();
	}

}	
