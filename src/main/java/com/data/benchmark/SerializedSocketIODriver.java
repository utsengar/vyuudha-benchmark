package com.data.benchmark;

import org.apache.log4j.Logger;

import com.data.dataObject.CreateData;
import com.data.sockets.SocketReceiver;
import com.data.sockets.SocketSender;
import com.data.util.Helper;

public class SerializedSocketIODriver {
	static Logger logger = Logger.getLogger(SerializedSocketIODriver.class);
	static Helper util = new Helper();
	static CreateData dataObj = new CreateData();
	
	public static void main(String[] args) {
		try {
			System.out.println("SocketIO benchmark starting.");
			logger.info("Size of object: " + util.sizeOfObject(dataObj));
			long startTime = System.currentTimeMillis();
			
			// Initialize the receiver thread
			SocketReceiver sReceiver = new SocketReceiver();
			Thread receiverThread = new Thread(sReceiver);
			receiverThread.start();
			
			// Start to transmit data
			SocketSender sSender = new SocketSender();
			sSender.startSender(1000, dataObj);
			
			long endTime = System.currentTimeMillis();
			logger.info("Total execution time with 1000 values: " + (endTime - startTime) + "ms");
		} catch (Exception e) {
			
		}
		finally{
			System.out.println("SocketIO benchmarking complete.");
			System.exit(1);
		}
	}
}
