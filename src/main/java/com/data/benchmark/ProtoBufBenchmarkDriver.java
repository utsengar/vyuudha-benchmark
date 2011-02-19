package com.data.benchmark;

import org.apache.log4j.Logger;

import com.data.dataObject.CreateProtoBufData;
import com.data.protobuf.ProtobufSender;
import com.data.protobuf.ProtobufReceiver;
import com.data.util.Helper;

public class ProtoBufBenchmarkDriver {
	static Logger logger = Logger.getLogger(ProtoBufBenchmarkDriver.class);
	static Helper util = new Helper();
	static CreateProtoBufData dataObj = new CreateProtoBufData();
	
	public static void main(String[] args) {
		try {
			System.out.println("ProtocolBuffer over socketIO benchmark starting.");
			logger.info("Size of object: " + util.sizeOfProtoBuf(dataObj.getPersonObj()));
			long startTime = System.currentTimeMillis();
			
			// Initialize the receiver thread
			ProtobufReceiver pReceiver = new ProtobufReceiver();
			Thread receiverThread = new Thread(pReceiver);
			receiverThread.start();
			
			// Start to transmit data
			ProtobufSender pSender = new ProtobufSender();
			pSender.startSender(1000, dataObj.getPersonObj());
			
			long endTime = System.currentTimeMillis();
			logger.info("Total execution time with 1000 values: " + (endTime - startTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			System.out.println("ProtocolBuffer over socketIO benchmarking complete.");
			System.exit(1);
		}
	}
}
