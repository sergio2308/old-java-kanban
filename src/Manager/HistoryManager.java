package Manager;

import Tasks.Task;

import java.util.List;

public interface HistoryManager {
    void addHistory(Task task);
    List<Task> getHistory();
}
