package p2p.project;

import java.io.*;
import java.net.Socket;
import java.util.Random;


import static java.lang.Thread.sleep;

public class Process {

    //Open up all connections FIRST, then start sending messages
    /**
     *  Note for implementation:
     *      -Idea for using a Client-Server based design is the idea that the process serves as a
     *       combination of both the Client and Server, meaning that all that is needed is the ability
     *       to create multiples
     *
     *      -Really really incomplete at the moment
     *
     */

    

    static int id;
    static String address = "127.0.0.1";
    static int port;
    static ArrayListSocket [] connection;
    static String message;
    static Peers [] peers;

    public Process()
    {
        this.message = "Hello from ID " + id;
        connection = new Socket[9];
        int iterate = 0; //used to build connection[]

        //setup file reader (setup.txt)
        //for each line that doesn't start with id, create new connection
        try {
            FileReader reader = new FileReader("setup.txt");
            BufferedReader read = new BufferedReader(reader);
            String line;
            while((line = read.readLine()) != null)
            {
                String [] proc = line.split(" ");
                if(Integer.parseInt(proc[0]) != this.id)
                {
                    connection[iterate] = new Socket("localhost", Integer.parseInt(proc[2]));
                    iterate++;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Sup");
        }
    }

    public static void main(String[] argv) throws Exception {
        /**
         *      -print a.n.out in commandline
         *          -./a.1.out specifies which this current terminal is
         *          -search through txt file, find line that matches the number, setupt
         *          -others are others
         *
         * after download
         *  javac
         *  java Process
         *  //open 9 more terminals
         *
         */

	if(argv.length <1){
		System.out.Println("Invalid number of arguments");
		System.exit(1);
	}

	try{
		id = Integer.ParseInt(argv[0]);
	}
	
	peers = new Peers[9];
	getPeers();
	

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new FileWriter("processID_msg.txt"));
        String ID = inFromUser.readLine();
        int input = Integer.parseInt(ID);
        Process process = new Process(input);

        DataOutputStream out[] = new DataOutputStream[9];
        BufferedReader in[] = new BufferedReader[9];

        for (int i = 0; i < 9; i++)
        {
            out[i] = new DataOutputStream(process.connection[i].getOutputStream());
            in[i] = new BufferedReader(new InputStreamReader(process.connection[i].getInputStream()));
        }

        int count = 50;

        Random rand = new Random();
        while(count > 0) {
            if(rand.nextDouble() > 0.5) {
                for(int i = 0; i < 9; i++)
                {
                    out[i].writeBytes(message + '\n');    //writes to others
                }
                writer.write(message);

                //TODO ERROR: need to be able to specify which one to read from
                    //TODO If can fix this one, then should be able to use this code
                    //TODO If can't, need to start over
                String response = in.readLine();    //other's response, move to txt file
                writer.write(response);
                //count--;
            }
            sleep(1);
        }

        for(int i = 0; i < 9; i++) {
            connection[i].close();   //finished
        }
    }

    public void getPeers(){
	int i = 0;
    	try {
            FileReader reader = new FileReader("setup.txt");
            BufferedReader read = new BufferedReader(reader);
            String line;
            while((line = read.readLine()) != null)
            {
                String [] proc = line.split(" ");
		int procID = Integer.parseInt(proc[0]);
                if(procID != this.id)
                {
			peers[i] = new Peers("localhost", Integer.parseInt(proc[2]), procID, false);
			i++;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Sup");
        }
    }

    public void connectPeers(){
    	int connected = 0;
	try {
            FileReader reader = new FileReader("setup.txt");
            BufferedReader read = new BufferedReader(reader);
            String line;
            while((line = read.readLine()) != null)
            {
                String [] proc = line.split(" ");
                if(Integer.parseInt(proc[0]) != this.id)
                {
                    connection[iterate] = new Socket("localhost", Integer.parseInt(proc[2]));
                    iterate++;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Sup");
        }
	while(connected <= 9){
		int peernum = 0;
		for(Peers peer : peers){
			if(!peer.getConnected()){
				try{
					Socket psock = new Socket("localhost", 
				}
			}
			peer

		}
	}



    }
}
