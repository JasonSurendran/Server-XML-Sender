package testing;

import java.io.BufferedReader;		
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ClientMain {
	public static void main(String[] args) {
		Scanner scanner = null;
		try(Socket socket = new Socket("localhost",5000)){
			socket.setSoTimeout(5000);
			BufferedReader echoes = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);
			scanner = new Scanner(System.in);
			String echoString;
			String response;
			do {
				System.out.println("Enter Tag To Be Searched: ");
				echoString = scanner.nextLine();
				stringToEcho.println(echoString);
				if(!echoString.equals("exit")) {
					response = echoes.readLine();
					System.out.println(response);}
			}while (!echoString.equals("exit"));
		}catch(SocketTimeoutException e) {
			System.out.println("SocketTimeoutException: " + e.getMessage());
		}catch(IOException e) {
			System.out.println("IOException: " + e.getMessage());}
		finally {
		    if(scanner!=null)
		        scanner.close();}}}
