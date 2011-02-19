package com.data.benchmark;

import org.apache.log4j.Logger;

import com.data.dataObject.CreateData;
import com.data.jgroups.JGroupsReceiver;
import com.data.jgroups.JGroupsSender;
import com.data.util.Helper;

public class JGroupsBenchmarkDriver {
	static Logger logger = Logger.getLogger(JGroupsBenchmarkDriver.class);
	static Helper util = new Helper();
	static CreateData dataObj = new CreateData();
	
	public static void main(String[] args) {
		try {
			System.out.println("Jgroups benchmark starting");
			logger.info("Size of object: " + util.sizeOfObject(dataObj));
			long startTime = System.currentTimeMillis();

			// Initialize the receiver thread
			JGroupsReceiver jReceiver = new JGroupsReceiver();
			Thread receiverThread = new Thread(jReceiver);
			receiverThread.start();

			// Start to transmit data
			JGroupsSender jSender = new JGroupsSender();
			jSender.start(1000, dataObj);
			long endTime = System.currentTimeMillis();
			logger.info("Total execution time with 1000 values: " + (endTime - startTime) + "ms");
		
		} catch (Exception e) {
			
		}
		finally{
			System.out.println("Jgroups benchmarking complete.");
			System.exit(1);
		}
	}
}