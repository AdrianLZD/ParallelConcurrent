
package parallelconcurrent;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    int ID;
    Buffer buffer;
    int sleepTime;
    double minNum;
    double maxNum;
    boolean alive;
    
    
    Producer(int ID,Buffer buffer, int sleepTime, double minNum, double maxNum) {
        this.ID=ID;
        this.buffer = buffer;
        this.sleepTime = sleepTime;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.alive = true;
    }
    
    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        SchemeOperation product;
        while(this.alive){
            if(GUIFrame.running) {
                product = new SchemeOperation(Math.floor((r.nextDouble() * (maxNum - minNum) + minNum) * 100 ) / 100, Math.floor((r.nextDouble() * (maxNum - minNum) + minNum) * 100 )/ 100 );
                this.buffer.produce(this.ID, product);
            }
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void kill(){
        this.alive = false;
    }
    
}
