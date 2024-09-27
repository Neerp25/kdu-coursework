package org.example.question2;

import org.example.question1.MessageQueue;
import org.example.question1.MessageReceiver;
import org.example.question1.MessageSender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();


        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);


        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);


        for (int i = 0; i < 3; i++) {
            senderThreadPool.submit(new MessageSender(messageQueue));
        }

        for (int i = 0; i < 3; i++) {
            receiverThreadPool.submit(new MessageReceiver(messageQueue));
        }


        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }

}
