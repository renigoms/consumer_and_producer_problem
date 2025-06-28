package org.example.solution_with_monitors;

public class Producer extends Thread {

    private final SharedBuffer sharedBuffer;
    private final Integer ItemsLimit;

    public Producer(SharedBuffer sharedBuffer, Integer itemsLimit) {
        this.sharedBuffer = sharedBuffer;
        ItemsLimit = itemsLimit;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < ItemsLimit; i++) {
                // Access the put method of the shared buffer
                sharedBuffer.put(i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finished product producer");
    }
}
