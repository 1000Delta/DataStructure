package queue;

import java.security.spec.ECField;
import java.util.Objects;

public class Queue<T> extends linkedList.LinkedList<T> {

    public void in(T data) {
        
        Node<T> newNode = new Node<T>(data);
        if (!Objects.isNull(this.tail)) {
    
            this.tail.next = newNode;
        } else {
            
            this.head = newNode;
        }
        this.tail = newNode;
    }
    
    public T out() throws Exception {
        
        if (!Objects.isNull(this.head)) {
    
            T data = this.head.getData();
            this.head = this.head.next;
            return data;
        } else {
            
            throw new Exception("Empty!");
        }
    }
    
    public T read() throws Exception {
        
        if (!Objects.isNull(this.head)){
    
            return this.getHead().getData();
        } else {
            
            throw new Exception("Empty!");
        }
    }
}
