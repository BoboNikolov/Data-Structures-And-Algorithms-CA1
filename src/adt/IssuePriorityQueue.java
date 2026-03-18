package adt;

import interfaces.QueueOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.DockIssue;

// Priority queue for DockIssue objects.Lower severity number means higher priority.

public class IssuePriorityQueue implements QueueOperations<DockIssue> {

    private ArrayList<DockIssue> queue;

    public IssuePriorityQueue() {
        queue = new ArrayList<DockIssue>();
    }

    @Override
    public void enqueue(DockIssue item) {
        if (item != null) {
            queue.add(item);
            sortQueue();
        }
    }

    @Override
    public DockIssue dequeue() {
        if (isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }

    @Override
    public DockIssue peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.get(0);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public List<DockIssue> getAll() {
        return new ArrayList<DockIssue>(queue);
    }

    private void sortQueue() {
        Collections.sort(queue, new Comparator<DockIssue>() {
            @Override
            public int compare(DockIssue a, DockIssue b) {
                return Integer.compare(a.getSeverity(), b.getSeverity());
            }
        });
    }

    public List<DockIssue> getOpenIssues() {
        List<DockIssue> open = new ArrayList<DockIssue>();
        for (DockIssue issue : queue) {
            if ("Open".equalsIgnoreCase(issue.getStatus())) {
                open.add(issue);
            }
        }
        return open;
    }

    public List<DockIssue> getIssuesByLocation(String locationId) {
        List<DockIssue> matches = new ArrayList<DockIssue>();
        for (DockIssue issue : queue) {
            if (issue.getLocationId().equalsIgnoreCase(locationId)) {
                matches.add(issue);
            }
        }
        return matches;
    }

    public boolean markPeekedIssueResolved() {
        if (isEmpty()) {
            return false;
        }

        DockIssue issue = queue.get(0);
        issue.setStatus("Resolved");
        return true;
    }
}
