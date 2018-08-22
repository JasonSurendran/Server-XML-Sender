package testing;

import java.io.IOException;	
import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args) {
	    try(ServerSocket serverSocket = new ServerSocket(5000)) {
            while(true) {
                new ServerEchoer(serverSocket.accept()).start();}
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}