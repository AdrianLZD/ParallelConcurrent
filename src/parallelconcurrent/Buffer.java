
package parallelconcurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private Queue<SchemeOperation> buffer;
    private int size;
    
    Buffer(int size) {
        this.buffer = new LinkedList<>();
        this.size = size;
    }
    
    synchronized String consume(){
        while(this.buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String product = this.buffer.poll().solve();
        notify();
        
        return product;
    }
    
    synchronized void produce(SchemeOperation product) {
        while(this.buffer.size() >= this.size) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(product);
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
