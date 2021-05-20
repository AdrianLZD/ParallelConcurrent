
package parallelconcurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Buffer {
    
    private Queue<SchemeOperation> buffer;
    private int size;
    private DefaultTableModel modelProducer;
    private DefaultTableModel modelConsumer;
    private JProgressBar progressBar;
    private JTextField counter;
    
    Buffer(int size, DefaultTableModel modelProducer, DefaultTableModel modelConsumer, JProgressBar progressBar, JTextField counter) {
        this.buffer = new LinkedList<>();
        this.size = size;
        this.modelConsumer=modelConsumer;
        this.modelProducer=modelProducer;
        this.progressBar = progressBar;
        this.counter = counter;
    }
    
    synchronized SchemeOperation consume(int id){
        while(this.buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SchemeOperation product = this.buffer.poll();
        this.progressBar.setValue(this.buffer.size());
        int nextCount = Integer.parseInt(this.counter.getText())+1;
        this.counter.setText(Integer.toString(nextCount));
        this.modelConsumer.addRow(new Object[]{ "C: "+id, product.getOperation(), product.solve() });
        this.modelProducer.removeRow(0);
        notify();
        
        return product;
    }
    
    synchronized void produce(int id, SchemeOperation product) {
        while(this.buffer.size() >= this.size) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(product);
        this.progressBar.setValue(this.buffer.size());
        this.modelProducer.addRow(new Object[]{ "P: "+id, product.getOperation()});
        notify();
    }
}
