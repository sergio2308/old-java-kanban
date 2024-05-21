package Manager;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements  HistoryManager{
    private final ArrayList<Task> history = new ArrayList<>();
    private final static int MAX_HISTORY_SIZE = 10;

    @Override
    public void addHistory(Task task) {
        if (task == null) {
            return;
        }
        if (history.size() >= MAX_HISTORY_SIZE) {
            history.removeFirst();
        }
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(history);
    }
}
