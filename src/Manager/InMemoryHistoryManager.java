package Manager;

import Tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements  HistoryManager{
    private final ArrayList<Task> history = new ArrayList<>();
    private final static int MAX_HISTORY_SIZE = 10;

    @Override
    public void addHistory(Task task) {
        if (history.size() >= MAX_HISTORY_SIZE) {
            history.removeFirst();
        }
        history.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }
}
