
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
    Random r;
    int productionMode;
    
    
    Producer(int ID,Buffer buffer, int sleepTime, double minNum, double maxNum, Random r, int productionMode) {
        this.ID=ID;
        this.buffer = buffer;
        this.sleepTime = sleepTime;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.alive = true;
        this.r = r;
        this.productionMode = productionMode;
    }
    
    @Override
    public void run() {
        SchemeOperation product;
        while(this.alive){
            if(GUIFrame.running) {
                product = new SchemeOperation(Math.floor((this.r.nextDouble() * (maxNum - minNum) + minNum) * 100 ) / 100, Math.floor((this.r.nextDouble() * (maxNum - minNum) + minNum) * 100 )/ 100, this.productionMode );
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
