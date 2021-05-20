
package parallelconcurrent;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Consumer extends Thread {
    Buffer buffer;
    int sleepTime;
    int ID;
    
    Consumer(int ID, Buffer buffer, int sleepTime) {
        this.buffer = buffer;
        this.sleepTime = sleepTime;
        this.ID=ID;
        
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        SchemeOperation product;
        
        while(true) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            
            
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
