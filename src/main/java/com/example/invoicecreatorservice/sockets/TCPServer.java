package com.example.invoicecreatorservice.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author dr.ir. Michael Franssen
 */
abstract public class TCPServer implements Runnable {

    private ServerSocket serversocket;
    private ArrayList<TCPService> clients;
    
    public TCPServer(int port) {
        try {
        serversocket=new ServerSocket(port);
        clients=new ArrayList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void run() {
        while (true) {
            try {
                Socket s=serversocket.accept();
                TCPService service=buildService();
                service.setSocket(s);
                service.setServer(this);
                Thread t=new Thread(service);
                t.start();
                clients.add(service);
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void broadcast(TCPService sender,String msg) {
        for(TCPService service:clients) {
            if (sender!=service) service.sendMessage(msg);
        }
    }
    
    abstract public TCPService buildService();
    
}
