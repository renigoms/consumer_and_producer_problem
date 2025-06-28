package org.example.solution_with_monitors;

import java.util.LinkedList;
import java.util.Queue;

/**
 * CR (Critical Region)
 */
public class SharedBuffer {
    private final Queue<Integer> buffer;
    private final Integer maxCapacity;

    public SharedBuffer(Integer maxCapacity) {
        this.buffer = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    // Method for the producer to add an item
    public void put(int item) throws InterruptedException {
        /**
         * The synchronized block ensures that only one thread
         * can access this block of code at a time.
         */
        synchronized (this) {
            /**
             * if the buffer is full, the producer must wait.
             * The while is used to avoid spurious wakeup (false awakening)
             */
            while (buffer.size() == maxCapacity) {
                System.out.println("Buffer is full. Producer waiting...");
                wait(); // Releases the lock and puts the thread on hold
            }
            // Critical Region: add item to buffer
            buffer.add(item);
            System.out.printf("Producer produced: %d. Buffer size: %d%n", item, buffer.size());
            /**
             * Notifies all threads that are waiting on the same lock.
             * In this, the consumer that may be waiting.
             */
            notifyAll();
        }
    }

    // Method for the consumer to remove an item
    public int get() throws InterruptedException {
        // In this, the synchronized block ensures mutual exclusion.
        synchronized (this) {
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty. Consumer waiting...");
                wait();
            }
            // Critical Region: remove item from buffer
            int item = buffer.remove();
            System.out.printf("Consumer consumed: %d. Buffer size: %d%n", item, buffer.size());
            /*
             * Notifies all threads that are on the same lock.
             * In this, the producer that may be waiting.
             */
            notifyAll();
            return item;
        }
    }
}
