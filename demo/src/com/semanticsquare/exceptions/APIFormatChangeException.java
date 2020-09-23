package com.semanticsquare.exceptions;

// By Extending checked exception we are forced to handle this exception `APIFormatChangeException` in the calling method.
// This is a custom checked exception created as per Item 58
public class APIFormatChangeException extends Exception {
	private	String response;
	private String elementName;
	private String partner;
	/*
	public APIFormatChangeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	*/
	public APIFormatChangeException(String response, String elementName, String partner, Throwable cause ) {
		super("Response: " + response + ", Element: code, Partner:" + partner, cause);
		this.response = response;
		this.elementName = elementName;
		this.partner = partner;
	}
	public APIFormatChangeException(String response, String elementName, String partner) {
		super("Response: " + response + ", Element: code, Partner:" + partner);
		this.response = response;
		this.elementName = elementName;
		this.partner = partner;
	}
	public String getResponse() {
		return response;
	}
	public String getElementName() {
		return elementName;
	}
	public String getPartner() {
		return partner;
	}	
}
