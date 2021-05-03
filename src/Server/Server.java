package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class implements a server, which offers service to the clients according to the strategy it works.
 */
public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private ExecutorService threadPool; // Thread pool
    private volatile boolean stop=false;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        // initialize a new fixed thread pool with 2 threads:
        Configurations conf = Configurations.getInstance();
        int num = conf.getThreadPoolSize();
        this.threadPool = Executors.newFixedThreadPool(num);
    }

    public void start(){
        new Thread(() ->{runServer();}).start();
    }

    /**
     * start the servers work
     */
    private void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    // Insert the new task into the thread pool:
                    threadPool.submit(() -> {
                        handleClient(clientSocket);
                    });

                    // From previous lab:
                    // This thread will handle the new Client
                    //new Thread(() -> {
                    //    handleClient(clientSocket);
                    //}).start();

                } catch (SocketTimeoutException e){
                    //System.out.println(e.getMessage());
                }
            }
            serverSocket.close();
            //threadPool.shutdown(); // do not allow any new tasks into the thread pool (not doing anything to the current tasks and running threads)
            threadPool.shutdownNow(); // do not allow any new tasks into the thread pool, and also interrupts all running threads (do not terminate the threads, so if they do not handle interrupts properly, they could never stop...)
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * handle the clients request
     * @param clientSocket connection to the client.
     */
    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            //System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void stop(){
        //System.out.println("Stopping server...");
        stop = true;
    }
}
