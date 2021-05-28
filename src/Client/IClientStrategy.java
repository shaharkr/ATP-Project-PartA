package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * client sends requests to server using this interface.
 * strategy design pattern.
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
