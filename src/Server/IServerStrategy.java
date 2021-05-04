package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * servers handle clients using this interface, must implement the applyStrategy method.
 * Strategy design pattern.
 */
public interface IServerStrategy {
    void applyStrategy(InputStream inFromClient, OutputStream outToClient);

}
