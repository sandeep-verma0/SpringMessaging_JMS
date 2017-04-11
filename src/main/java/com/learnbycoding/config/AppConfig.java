package com.learnbycoding.config;


import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;


@Configuration
@ComponentScan(basePackages="com.learnbycoding.*")
public class AppConfig {


	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL("tcp://127.0.0.1:61616");
		return factory;
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("notification.queue");
	}

	@Bean
	public JmsOperations jmsTemplate(ConnectionFactory connectionFactory, Queue queue) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(queue);
		return jmsTemplate;
	}
}
