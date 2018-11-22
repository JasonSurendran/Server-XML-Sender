package testing;

import java.io.IOException;	
import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args) {
	    
	    //Creating Main Server On LocalHost 5000
	    try(ServerSocket serverSocket = new ServerSocket(5000)) {
            while(true) {
                new ServerEchoer(serverSocket.accept()).start();}
        } catch(IOException e) {
	//Print error with message    
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
