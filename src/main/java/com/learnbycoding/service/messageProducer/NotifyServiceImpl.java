package com.learnbycoding.service.messageProducer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.learnbycoding.model.Employee;

@Service(value ="notifyService")
public class NotifyServiceImpl implements NotifyService {


	@Autowired
	private JmsOperations jmsOperations;
	

	@Override
	public void notifyString(String message) throws JMSException {
		jmsOperations.send("notification.queue", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	@Override
	public void notifyEmployee(Employee employee) throws JMSException {
		jmsOperations.send("notification.queue", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(employee);
			}
		});
	}

}
