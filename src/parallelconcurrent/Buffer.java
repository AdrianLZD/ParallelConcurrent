
package parallelconcurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Buffer {
    
    private Queue<SchemeOperation> buffer;
    private int size;
    private DefaultTableModel modelProducer;
    private DefaultTableModel modelConsumer;
    
    Buffer(int size, DefaultTableModel modelProducer, DefaultTableModel modelConsumer) {
        this.buffer = new LinkedList<>();
        this.size = size;
        this.modelConsumer=modelConsumer;
        this.modelProducer=modelProducer;
    }
    
    synchronized SchemeOperation consume(){
        while(this.buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SchemeOperation product = this.buffer.poll();
        this.modelConsumer.addRow(new Object[]{ "C: ", product.getOperation(), product.solve() });
        this.modelProducer.removeRow(0);
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
        this.modelProducer.addRow(new Object[]{ "P: ", product.getOperation()});
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
