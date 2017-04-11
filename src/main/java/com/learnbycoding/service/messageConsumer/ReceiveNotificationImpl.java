package com.learnbycoding.service.messageConsumer;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import com.learnbycoding.model.Employee;

@Service(value = "receiveNotification")
public class ReceiveNotificationImpl implements ReceiveNotification {

	@Autowired
	private JmsOperations jmsOperations;

	@Override
	public void receiveNotification() {
		try {
			TextMessage receivedMessage = (TextMessage) jmsOperations.receive();
			System.out.println("Received Message :" + receivedMessage.getText());
		} catch (JMSException jmsException) {
			throw JmsUtils.convertJmsAccessException(jmsException);
		}
	}

	@Override
	public void receiveNotificationEmployee() {
		try {
			ObjectMessage receivedMessage = (ObjectMessage) jmsOperations
					.receive();
			Employee empployee = (Employee) receivedMessage.getObject();
			System.out.println("Received Employee  : " + (empployee));
		} catch (JMSException jmsException) {
			throw JmsUtils.convertJmsAccessException(jmsException);
		}
	}
}