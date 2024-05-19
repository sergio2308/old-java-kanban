package Manager;

import Tasks.Epic;
import Tasks.SubTask;
import Tasks.Task;

import java.util.ArrayList;

public interface TaskManager {
    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<SubTask> getSubTasks();

    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubTasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubTaskById(int id);

    Integer createNewTask(Task task);

    Integer createNewEpic(Epic epic);

    Integer createNewSubTask(SubTask subTask, int epicId);

    void updateTask(Task task);

    void updateEpic(Epic newEpic);

    void updateSubTask(SubTask newSubTask, int epicId);

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubTaskById(int id);

    void getEpicSubTasks(int id);
}