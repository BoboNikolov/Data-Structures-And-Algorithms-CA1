package interfaces;

import java.util.List;

public interface QueueOperations<T> {
    void enqueue(T item);
    T dequeue();
    T peek();
    boolean isEmpty();
    int size();
    List<T> getAll();
}
