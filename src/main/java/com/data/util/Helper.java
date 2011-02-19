package com.data.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import com.data.dataObject.DataExchangeInternal.Person;

public class Helper {

	public int sizeOfObject(Object obj) throws java.io.IOException {
		//http://devblog.streamy.com/2009/07/24/determine-size-of-java-object-class/
		ByteArrayOutputStream byteObject = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteObject);
		objectOutputStream.writeObject(obj);
		objectOutputStream.flush();
		objectOutputStream.close();
		byteObject.close();

		return byteObject.size();
	}
	

	public int sizeOfProtoBuf(Person obj) throws java.io.IOException {
	
		return obj.getSerializedSize();
	}
}
