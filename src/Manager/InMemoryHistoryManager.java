package Manager;

import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements  HistoryManager{
    private Node<Task> head;
    private Node<Task> tail;
    private final Map<Integer, Node> history = new HashMap<>();

    @Override
    public void addHistory(Task task) {
       if (task == null) {
            return;
        }
        if (history.containsKey(task.getId())) {
            remove(task.getId());
        }
        linkLast(task);
    }

    private void linkLast(Task task) {
        Node<Task> newNode = new Node<>(task, null, tail);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        history.put(task.getId(), newNode);
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node<Task> currentNode = head;
        while (currentNode != null) {
            tasks.add(currentNode.task);
            currentNode = currentNode.next;
        }
        return tasks;
    }

    @Override
    public void remove(int id) {
        Node nodeToRemove = history.remove(id);
        if (nodeToRemove == null) {
            return;
        }
        if (nodeToRemove.prev == null && nodeToRemove.next == null) {
            head = null;
            tail = null;
        } else if (nodeToRemove.prev == null) {
            head = nodeToRemove.next;
            nodeToRemove.next.prev = null;
        } else if (nodeToRemove.next == null) {
            tail = nodeToRemove.prev;
            nodeToRemove.prev.next = null;
        } else {
            nodeToRemove.next.prev = nodeToRemove.prev;
            nodeToRemove.prev.next = nodeToRemove.next;
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}
