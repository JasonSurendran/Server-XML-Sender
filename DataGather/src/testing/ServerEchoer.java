package testing;

import java.io.BufferedReader;	
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;


public class ServerEchoer extends Thread {
    private Socket socket;
    public ServerEchoer(Socket socket) {
        this.socket = socket;}

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            while(true) {
                String echoString = input.readLine();
                if(echoString.equals("exit")) {
                    break;}
            try {
                URL url = new URL("https://api.flickr.com/services/feeds/photos_public.gne?tags="+echoString);
                System.out.println(url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "Chrome");
                connection.setReadTimeout(15000);
                int responseCode = connection.getResponseCode();
                if(responseCode != 200) {
                    System.out.println("Error! Response code: " + responseCode);
                    System.out.println(connection.getResponseMessage());
                    return;}
                BufferedReader inputReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String xml = null;
                String liner = null;
                while((liner = inputReader.readLine()) != null) {
                    xml = xml + liner;}
                System.out.println("Server recieved xml from: " +url);
                output.println(xml);
                inputReader.close();
            } catch(MalformedURLException e) {
                System.out.println("MalformedURLException: " + e.getMessage());
            } catch(IOException e) {
                System.out.println("IOException: " + e.getMessage());}}
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch(IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }

    }

}