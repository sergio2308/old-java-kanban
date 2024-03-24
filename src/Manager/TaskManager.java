package Manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.SubTask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap <Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;

    private static int id = 0;

    public TaskManager() {
        this.tasks = new HashMap<Integer, Task>();
        this.epics = new HashMap<Integer, Epic>();
        this.subTasks = new HashMap<Integer, SubTask>();
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return subTasks;
    }

    public void clearAllTasks() {
        tasks.clear();
    }

    public void clearAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    public void clearAllSubTasks() {
        subTasks.clear();
        for (Integer epicId : epics.keySet()) {
            Epic epic = (Epic) getEpicById(epicId);
            epic.getSubTasksIds().clear();
            updateEpic(epic);
        }
    }

    public Task getTaskById (int id) {
        return tasks.get(id);
    }

    public Task getEpicById (int id) {
        return epics.get(id);
    }

    public Task getSubTaskById (int id) {
        return subTasks.get(id);
    }

    public void createNewTask(Task task) {
        task.setId(idIncrease());
        tasks.put(task.getId(), task);
    }

    public Epic createNewEpic(Epic epic) {
        epic.setId(idIncrease());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void createNewSubTask(SubTask subTask) {
        subTask.setId(idIncrease());
        subTasks.put(subTask.getId(), subTask);
    }

    public void addNewSubTask(int id, int epicId, SubTask newSubTack) {
        Epic epic = epics.get(epicId);
        epic.getSubTasksIds().add(newSubTack.getId());
        epics.put(epicId, epic);
        newSubTack.setEpicId(epicId);
    }

    public void updateTask (Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic (Epic newEpic) {
        Epic epicToUpdate = (Epic) getEpicById(newEpic.getId());

        if (epicToUpdate.getSubTasksIds().isEmpty()) {
            epicToUpdate.setStatus(Status.NEW);
            epics.put(epicToUpdate.getId(), epicToUpdate);
        }
        int newStatusCounter = 0;
        int doneStatusCounter = 0;

        for (int i = 0; i < epicToUpdate.getSubTasksIds().size(); i++) {
            SubTask subTask = (SubTask) getSubTaskById(epicToUpdate.getSubTasksIds().get(i));
            if (subTask.getStatus() == Status.NEW) {
                newStatusCounter++;
            } else if (subTask.getStatus().equals(Status.DONE)) {
                doneStatusCounter++;
            }
        }
        if (newStatusCounter == epicToUpdate.getSubTasksIds().size()) {
            epicToUpdate.setStatus(Status.NEW);
        } else if (doneStatusCounter == epicToUpdate.getSubTasksIds().size()) {
            epicToUpdate.setStatus(Status.DONE);
        } else {
            epicToUpdate.setStatus(Status.IN_PROGRESS);
        }
        epics.put(epicToUpdate.getId(), epicToUpdate);
    }

    public void updateSubTask (SubTask newSubTask) {
    SubTask subTaskToUpdate = (SubTask) getSubTaskById(newSubTask.getId());

    subTaskToUpdate.setStatus(newSubTask.getStatus());
    addNewSubTask(subTaskToUpdate.getId(), subTaskToUpdate.getEpicId(), subTaskToUpdate);
    Epic epicToUpdate = (Epic) getEpicById(subTaskToUpdate.getEpicId());

    updateEpic(epicToUpdate);
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeEpicById(int id) {
        Epic epicToRemove = epics.get(id);
        ArrayList<Integer> subTasksToRemove = epicToRemove.getSubTasksIds();
        for (Integer subTaskid : subTasksToRemove) {
            subTasks.remove(subTaskid);
        }
        epics.remove(id);
    }

    public void removeSubTaskById(int id) {
        Epic epic = (Epic) getEpicById(subTasks.get(id).getEpicId());
        epic.getSubTasksIds().clear();
        updateEpic(epic);
        subTasks.remove(id);
    }

    public void getEpicSubTasks (int id) {
        Epic epic = epics.get(id);
        ArrayList<Integer> subTasksIds = epic.getSubTasksIds();
        for (Integer subTaskId : subTasksIds) {
            subTasks.get(subTaskId);
        }
    }

    public int idIncrease() {
        id = id + 1;
        return id;
    }

    public static int getId() {
        return id;
    }
}