package org.example;

import org.example.solution_with_monitors.Consumer;
import org.example.solution_with_monitors.Producer;
import org.example.solution_with_monitors.SharedBuffer;

public class Main {
    public static void main(String[] args) {
        // Defines the buffer size and the amount of items to be produced/consumed
        int bufferCapacity = 5;
        int itemsTotal = 20;

        SharedBuffer sharedBuffer = new SharedBuffer(bufferCapacity);

        Producer producer = new Producer(sharedBuffer, itemsTotal);
        Consumer consumer = new Consumer(sharedBuffer, itemsTotal);

        producer.start();
        consumer.start();

        try{
            // Wait for both threads to finish their executions.
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Program closed");
    }
}