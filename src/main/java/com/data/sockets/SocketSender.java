package com.data.sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.data.dataObject.CreateData;

public class SocketSender {
	static Logger logger = Logger.getLogger(SocketSender.class);
    public void startSender(int messageCount, CreateData obj) {
        try {
            InetAddress host = InetAddress.getLocalHost();
            for(int i=0; i<messageCount; i++)
            {
                Socket socket = new Socket(host.getHostName(), 7777);
            	logger.info("Sending message: " + i + " sent: " + System.currentTimeMillis());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(obj);	
                oos.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}