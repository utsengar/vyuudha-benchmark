package com.data.sockets;

import java.io.IOException;
import java.lang.Runnable;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class SocketReceiver implements Runnable {
	static Logger logger = Logger.getLogger(SocketReceiver.class);
	private ServerSocket server;
	private int port = 7777;

	public void run() {
		logger.info("Receiver starting");
		try {
			server = new ServerSocket(port);
			int messageCount = 0;
			while (true) {
				Socket socket = server.accept();
				new ConnectionHandler(socket);
				messageCount = messageCount + 1;
			}
		} catch (IOException e) {

		}
	}
}

class ConnectionHandler {
	static Logger logger = Logger.getLogger(ConnectionHandler.class);
	public ConnectionHandler(Socket socket) {
		logger.info("Message received at: " + System.currentTimeMillis());
	}
}
