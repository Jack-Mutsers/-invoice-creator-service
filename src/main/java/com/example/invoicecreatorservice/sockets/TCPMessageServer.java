package com.example.invoicecreatorservice.sockets;

import java.io.IOException;

/**
 *
 * @author dr.ir. Michael Franssen
 */
public class TCPMessageServer extends TCPServer {

    public static final int CHATSERVER=1501;
    
    public TCPMessageServer() throws IOException {
        super(CHATSERVER);
    }
    
    @Override
    public TCPService buildService() {
        return new TCPMessageService();
    }

    public static void main(String smurf[]) {
        try {
            TCPServer server=new TCPMessageServer();
            server.run();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
