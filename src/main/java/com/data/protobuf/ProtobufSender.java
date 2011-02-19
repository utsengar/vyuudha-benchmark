package com.data.protobuf;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import com.data.dataObject.DataExchangeInternal.Person;
import com.google.protobuf.CodedOutputStream;

public class ProtobufSender {
	static Logger logger = Logger.getLogger(ProtobufSender.class);
    public void startSender(int messageCount, Person obj) {
        try {
            InetAddress host = InetAddress.getLocalHost();
            for(int i=0; i<messageCount; i++)
            {
                Socket socket = new Socket(host.getHostName(), 7777);
            	logger.info("Sending protobuf message: " + i + " sent: " + System.currentTimeMillis());
            	CodedOutputStream out = CodedOutputStream.newInstance(socket.getOutputStream());
            	//out.writeRawVarint32(obj.getSerializedSize());
            	obj.writeTo(out);
            	
            	//Flush makes a BIG difference!!
            	//out.flush();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}