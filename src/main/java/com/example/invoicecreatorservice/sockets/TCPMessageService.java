package com.example.invoicecreatorservice.sockets;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author dr.ir. Michael Franssen
 */
public class TCPMessageService implements TCPService {

    TCPServer server;
    BufferedWriter dst;
    BufferedReader src;

    @Override
    public void setSocket(Socket clientconnection) {
        try {
            dst = new BufferedWriter(new OutputStreamWriter(clientconnection.getOutputStream()));
            src = new BufferedReader(new InputStreamReader(clientconnection.getInputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void setServer(TCPServer server) {
        this.server=server;
    }

    @Override
    public void sendMessage(String message) {
        try {
            dst.write(message);
            dst.newLine();
            dst.flush();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message=src.readLine();
                server.broadcast(this, message);
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
