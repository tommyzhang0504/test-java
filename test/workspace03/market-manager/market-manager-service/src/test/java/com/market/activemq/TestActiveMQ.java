package com.market.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class TestActiveMQ {
    //queue,producer
    @Test
    public void testQueueProducer() throws Exception {
        //1.创建一个连接工厂ConnectionFactory对象：构造参数里指定ip与端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        //2.创建一个connection连接对象，调用start()方法，使用connection创建一个session对象
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//自动应答
        //3.开启连接，然后使用session创建一个destination对象，queue或是topic
        Queue queue = session.createQueue("test-queue");
        //4.使用session创建一个producer对象
        MessageProducer producer = session.createProducer(queue);
        //5.创建TextMessage，发送消息
        TextMessage textMessage = session.createTextMessage("这是第二条queue的testMessage");
        producer.send(textMessage);
        //6.关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    //queue, consumer
    @Test
    public void testQueueConsumer() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//自动应答
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);

        //创建一个messagelistener
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                //取消息内容
                printMessage(message);
            }
        });
        //等待接收消息
        /*while (true){
            Thread.sleep(100);
        }*/
        System.in.read();   //等待键盘输入

        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicProducer() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("111hello my activeMQ topic message");
        producer.send(textMessage);

        //关闭资源
        producer.close();
        session.close();
        connection.close();

    }

    @Test
    public void testTopicConsumer() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                printMessage(message);
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    public void printMessage(Message message){
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text1 = textMessage.getText();
                System.out.println(text1);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
