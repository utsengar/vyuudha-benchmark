package com.data.dataObject;

import java.io.Serializable;

public class CreateData implements Serializable {
	private static final long serialVersionUID = 1L;
	int id = 0;
	String key = null;
	String value = null;

	public CreateData() {
		this.id = 1;
		this.key = "key1";
		this.value = "some huge value lies here!!!";
	}
}
