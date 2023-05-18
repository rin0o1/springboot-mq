package com.example.demo.services;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	private final JmsTemplate jmsTemplate;	

	public MessageService(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String send(String message) {		
		try {						
			jmsTemplate.convertAndSend("DEV.QUEUE.1", message);						
			return "{ \"message\" : \"Message Sent: "  + message +"\" }";
		} catch (JmsException ex) {
			ex.printStackTrace();
			return "{ \"message\" : \"Some errors occured on sending the message: "+ message+ "\" }";			
		}
	}

	public String recv() {
		try {
			String msg = jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
			return "{ \"message\" : \"Message Received: "  + msg + "\" }";
		} catch (JmsException ex) {
			ex.printStackTrace();
			return "{ \"message\" : \"Error on receiving the message\" }";
		}
	}
}
