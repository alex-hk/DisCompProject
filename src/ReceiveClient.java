import java.io.*;
import java.net.*;
import java.util.*;

public class ReceiveClient implements Runnable{
    private Socket sock;
    private ArrayList<String> messages;
    private String filename;
    private int id;

    public ReceiveClient(int id, Socket mysock){
	sock = mysock;
	messages = new ArrayList<String>();
	this.id = id;
	filename = "files/process" + id + "_msg.txt";
    }

    public String getMessage(int index){
	if(index >= 0 && index <= messages.size()){
	    return messages.get(index);
	}
	return null;
    }

    public void storeMessages(){
	try{
	    FileWriter fw = new FileWriter(filename, true);
	    for(String s : messages){
		fw.write(s);
		fw.flush();
	    }
	    fw.close();
	} catch (IOException e){
	    e.printStackTrace();
	}
    }

    public void run(){
	String message = null;
	long f0 = System.nanoTime();
	long f1;
	try{
	    BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	    while(((f1 = System.nanoTime() - f0) / 1000000000.0) < 100.0){
		if((message = br.readLine())!= null){
		    System.out.println("Message received: " + message);
		    messages.add(message);
		}
	    }
	} catch (IOException e){
	    e.printStackTrace();
	}
    }



}
