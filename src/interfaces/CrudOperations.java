package interfaces;

import java.util.List;

// Generic crud interface where T is type of object being stored
// and k is type of key used to access that object

public interface CrudOperations<T, K> {
    boolean create(T item);
    T read(K key);
    boolean update(K key, T updatedItem);
    boolean delete(K key);
    List<T> getAll();
    int size();
}
