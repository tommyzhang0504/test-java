package com.market.springActiveMQ;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class TestSpringActiveMQ {

    /**
     * 使用jmsTemplate发送消息
     */
    @Test
    public void testSpringActiveMQ1() throws Exception {

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        JmsTemplate jmsTemplate = ac.getBean(JmsTemplate.class);
        Destination destination = (Destination) ac.getBean("test-queue");
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring test-queue");
                return textMessage;
            }
        });

    }
}
