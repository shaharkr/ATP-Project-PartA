package Client;

import IO.MyCompressorOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * this is a class representing client object which works according to its client strategy.
 */
public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    /**
     * communicate with the server, according to the strategy.
     */
    public void communicateWithServer() {
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            //System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
