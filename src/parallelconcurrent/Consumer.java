
package parallelconcurrent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int sleepTime;
    int ID;
    boolean alive;
    
    Consumer(int ID, Buffer buffer, int sleepTime) {
        this.buffer = buffer;
        this.sleepTime = sleepTime;
        this.ID=ID;
        this.alive = true;
    }
    
    @Override
    public void run() {
        while(this.alive){
            if(GUIFrame.running) {
                this.buffer.consume(this.ID);
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
