package com.data.jgroups;

import org.apache.log4j.Logger;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import com.data.dataObject.CreateData;

public class JGroupsSender extends ReceiverAdapter {
	
	Logger logger = Logger.getLogger(JGroupsSender.class);
    JChannel channel;
    public void start(int loopCount, CreateData dataObj) throws Exception {
		logger.info("Sender starting");
        channel=new JChannel();
        channel.setReceiver(this);
        channel.connect("jGroupsCluster");
        channel.getState(null, 10000);
		logger.info("Sender started");
        logger.info("Sender Channel stats: " + channel.getProperties());
        eventLoop(loopCount, dataObj);
        channel.close();
    }

    private void eventLoop(int loopCount, CreateData dataObj) {
    	int messageCount=0;
        while(messageCount<loopCount) {
            try {
                Message msg=new Message(null, null, dataObj);
                logger.info("Message "+ messageCount +" sent: " + System.currentTimeMillis());
                channel.send(msg);
                messageCount = messageCount+1;
            }
            catch(Exception e) {
            }
        }
    }

}