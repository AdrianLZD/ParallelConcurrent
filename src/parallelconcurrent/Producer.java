
package parallelconcurrent;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int sleepTime;
    double minNum;
    double maxNum;
    
    Producer(Buffer buffer, int sleepTime, double minNum, double maxNum) {
        this.buffer = buffer;
        this.sleepTime = sleepTime;
        this.minNum = minNum;
        this.maxNum = maxNum;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        
        Random r = new Random(System.currentTimeMillis());
        SchemeOperation product;
        
        while(true) {
            product = new SchemeOperation(Math.floor((r.nextDouble() * (maxNum - minNum) + minNum) * 100 ) / 100, Math.floor((r.nextDouble() * (maxNum - minNum) + minNum) * 100 )/ 100 );
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product.getOperation());
            
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
