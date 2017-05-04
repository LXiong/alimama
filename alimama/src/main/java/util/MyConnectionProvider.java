package util;

import java.io.IOException;
import java.net.Socket;

import javax.net.SocketFactory;

import jodd.http.net.SocketHttpConnectionProvider;
 public class MyConnectionProvider extends SocketHttpConnectionProvider {
        protected Socket createSocket(
                SocketFactory socketFactory, String host, int port)
                throws IOException {
            Socket socket = createSocket(socketFactory, host, port);
            socket.setSoTimeout(10000);
            return socket;
        }
    }