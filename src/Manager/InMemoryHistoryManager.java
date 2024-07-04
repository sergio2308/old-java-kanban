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

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(history);
    }
}
