package org.example.solution_with_monitors;

public class Consumer extends Thread{

    private final SharedBuffer sharedBuffer;
    private final Integer itemsLimit;

    public Consumer(SharedBuffer sharedBuffer, Integer itemsLimit) {
        this.sharedBuffer = sharedBuffer;
        this.itemsLimit = itemsLimit;
    }

    @Override
    public void run() {
        try{
            for(int i = 0; i<itemsLimit; i++){
                /*
                    Access the get method of the shared buffer.
                    The get method is the critical region.
                 */
                sharedBuffer.get();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finished consumed consumer");
    }
}
