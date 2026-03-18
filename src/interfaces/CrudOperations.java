package interfaces;

import java.util.List;

public interface CrudOperations<T, K> {
    boolean create(T item);
    T read(K key);
    boolean update(K key, T updatedItem);
    boolean delete(K key);
    List<T> getAll();
    int size();
}
