package org.example.question1;


public class MessageSender implements Runnable{
    private  MessageQueue messageQueue;

    public MessageSender(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }


    @Override
    public void run() {
        // Produce messages and add them to the MessageQueue
        try {
            for (int i = 0; i < 5; i++) {
                String message = "Message from Sender " + Thread.currentThread().getId() + ": " + i;
                messageQueue.addMessage(message);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
