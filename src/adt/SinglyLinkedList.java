package adt;

import interfaces.CrudOperations;
import java.util.ArrayList;
import java.util.List;
import model.RouteStep;

// Generic singly linked list implementation

public class SinglyLinkedList<T> implements CrudOperations<T, Integer> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public boolean create(T item) {
        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        return true;
    }

    @Override
    public T read(Integer index) {
        if (index == null || index < 0 || index >= size) {
            return null;
        }

        Node current = head;
        int counter = 0;

        while (current != null) {
            if (counter == index) {
                return current.data;
            }
            current = current.next;
            counter++;
        }

        return null;
    }

    @Override
    public boolean update(Integer index, T updatedItem) {
        if (index == null || index < 0 || index >= size) {
            return false;
        }

        Node current = head;
        int counter = 0;

        while (current != null) {
            if (counter == index) {
                current.data = updatedItem;
                return true;
            }
            current = current.next;
            counter++;
        }

        return false;
    }

    @Override
    public boolean delete(Integer index) {
        if (index == null || index < 0 || index >= size || head == null) {
            return false;
        }

        if (index == 0) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        int counter = 0;

        while (current.next != null) {
            if (counter == index - 1) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
            counter++;
        }

        return false;
    }

    @Override
    public List<T> getAll() {
        List<T> items = new ArrayList<T>();
        Node current = head;

        while (current != null) {
            items.add(current.data);
            current = current.next;
        }

        return items;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean insertAt(int index, T item) {
        if (index < 0 || index > size) {
            return false;
        }

        Node newNode = new Node(item);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return true;
        }

        Node current = head;
        int counter = 0;

        while (current != null) {
            if (counter == index - 1) {
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return true;
            }
            current = current.next;
            counter++;
        }

        return false;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int getTotalEstimatedMinutes() {
        int total = 0;
        Node current = head;

        while (current != null) {
            if (current.data instanceof RouteStep) {
                RouteStep step = (RouteStep) current.data;
                total += step.getEstimatedMinutes();
            }
            current = current.next;
        }

        return total;
    }

    public int countAccessibleSteps() {
        int count = 0;
        Node current = head;

        while (current != null) {
            if (current.data instanceof RouteStep) {
                RouteStep step = (RouteStep) current.data;
                if (step.isAccessible()) {
                    count++;
                }
            }
            current = current.next;
        }

        return count;
    }
}
