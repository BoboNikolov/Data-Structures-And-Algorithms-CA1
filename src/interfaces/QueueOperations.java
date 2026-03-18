package interfaces;

import java.util.List;

// Generic queue interface, used for the issue of priority queue
public interface QueueOperations<T> {
    void enqueue(T item);
    T dequeue();
    T peek();
    boolean isEmpty();
    int size();
    List<T> getAll();
}
