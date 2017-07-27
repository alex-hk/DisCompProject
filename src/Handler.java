import java.util.*;


public class Handler{
    public static void main(String args[]){
	ArrayList<Peer2> peers;
	ArrayList<Thread> pthreads;


        peers = new ArrayList<Peer2>();
	pthreads = new ArrayList<Thread>();

	for(int i = 0; i < 10; i++){
	    peers.add(new Peer2("localhost", 5000+(i), i+1, false));
	    pthreads.add(new Thread(peers.get(i)));
	}


	for(Thread t : pthreads){
	    t.start();
	}
    }
}
