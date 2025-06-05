package com.sanjayp.busservice;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendToQueue(String message) {
        jmsTemplate.convertAndSend("BOOKING_EVENT", message);
        System.out.println("Message sent to queue: " + message);
    }
}
