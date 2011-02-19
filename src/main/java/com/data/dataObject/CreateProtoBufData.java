package com.data.dataObject;
import com.data.dataObject.DataExchangeInternal.Person;
public class CreateProtoBufData {
	public Person cpdb = null;
	public CreateProtoBufData()
	{
		
	    Person.Builder person = Person.newBuilder();
	    person.setId(0);
	    person.setName("key1");
	    person.setEmail("some huge value lies here!!!");
	    
	    cpdb = person.build();
	}
	
	public Person getPersonObj()
	{
		return this.cpdb;
	}
}
