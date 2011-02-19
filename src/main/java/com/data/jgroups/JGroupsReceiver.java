package com.data.jgroups;

import org.apache.log4j.Logger;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.util.List;
import java.util.LinkedList;

public class JGroupsReceiver extends ReceiverAdapter implements Runnable {
	Logger logger = Logger.getLogger(JGroupsReceiver.class);
	int messageCount=0;
    JChannel channel;
    final List<String> state=new LinkedList<String>();

    public void viewAccepted(View new_view) {
//        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
        String line=msg.getSrc() + ": " + msg.getObject();
        logger.info("Message "+ messageCount +" received: " + System.currentTimeMillis());
        messageCount = messageCount+1;
        synchronized(state) {
            state.add(line);
        }
    }

    public byte[] getState() {
        synchronized(state) {
            try {
                return Util.objectToByteBuffer(state);
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void setState(byte[] new_state) {
        try {
            @SuppressWarnings("unchecked")
			List<String> list=(List<String>)Util.objectFromByteBuffer(new_state);
            synchronized(state) {
                state.clear();
                state.addAll(list);
            }
            //System.out.println("received state (" + list.size() + " messages in chat history):");
            for(String str: list) {
                System.out.println(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        channel=new JChannel();
        channel.setReceiver(this);
        channel.connect("jGroupsCluster");
        channel.getState(null, 10000);
        logger.info("Receiver Channel stats: " + channel.getProperties());
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        while(true) {
        	
        }
    }

	public void run() {
		JGroupsReceiver jReceiver = new JGroupsReceiver();
		try {
			logger.info("Receiver starting");
			jReceiver.start();
			logger.info("Receiver started");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}