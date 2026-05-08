package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

public class Main {
    public static void main(String[] args) {
        final String queueName = "withdrawal-operation";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        Connection connection;

        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            Consumer consumer = new MyConsumer(channel);
            channel.basicConsume(queueName, true, consumer);

            channel.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}