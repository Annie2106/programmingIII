package com.uptc.prg3.dinner.server.entity;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private Object message;

	public Message() {
	}

	public Message(String type) {
		this.type = type;
	}

	public Message(String type, Object message) {
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
