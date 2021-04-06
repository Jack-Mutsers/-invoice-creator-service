package com.example.invoicecreatorservice.sockets;

import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author micha
 */
public interface TCPService extends Runnable {

    public void setSocket(Socket clientconnection);
    public void setServer(TCPServer server);
    public void sendMessage(String message);
    
}
