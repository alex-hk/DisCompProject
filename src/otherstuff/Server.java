import java.io.*;
import java.net.*;
import java.net.Socket;

class Server {
    public static void main(String argv[]) throws Exception {
        String clientSentence;      //message from client
        String capitalizedSentence; //message returned to client
        ServerSocket welcomeSocket = new ServerSocket(6789);    //creating socket

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();   //accepts socket from client (1)
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); //setup reader
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); //setup writer
            clientSentence = inFromClient.readLine();   //read from client
            System.out.println("Received: " + clientSentence);  //sout recieved
            capitalizedSentence = clientSentence.toUpperCase() + '\n';  //capitalizes whatever was sent
            outToClient.writeBytes(capitalizedSentence);    //returns new message
        }
    }
}

