package intranet;

import java.lang.*;
import java.util.*;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	private static final long serialVersionUID = 924541240054761988L;
	private Employee messageFrom;
	private Employee messageTo;
	private String title;
	private String text;
	private Date messageDate;

	public Message(Employee messageFrom, Employee messageTo, String title, String text) {
		this.messageFrom = messageFrom;
		this.messageTo = messageTo;
		this.title = title;
		this.text = text;
		this.messageDate = new Date();
	}

	public Employee getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(Employee messageFrom) {
		this.messageFrom = messageFrom;
	}

	public Employee getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(Employee messageTo) {
		this.messageTo = messageTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public String toString() {
		return "messageFrom = " + messageFrom + " , messageTo = " + messageTo + " , title = " + title + " , text = "
				+ text + " , messageDate = " + messageDate;
	}

}
 
