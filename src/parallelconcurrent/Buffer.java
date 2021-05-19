
package parallelconcurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private Queue<Character> buffer;
    private int size;
    
    Buffer(int size) {
        this.buffer = new LinkedList<>();
        this.size = size;
    }
    
    synchronized char consume() {
        if(this.buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        char product = this.buffer.poll();
        notifyAll();
        
        return product;
    }
    
    synchronized void produce(char product) {
        if(this.buffer.size() >= this.size) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(product);
        notifyAll();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
