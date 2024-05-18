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

    private static int idGenerator = 1;

    public TaskManager() {
        this.tasks = new HashMap<Integer, Task>();
        this.epics = new HashMap<Integer, Epic>();
        this.subTasks = new HashMap<Integer, SubTask>();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
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

    public Epic getEpicById (int id) {
        return epics.get(id);
    }

    public SubTask getSubTaskById (int id) {
        return subTasks.get(id);
    }

    public Integer createNewTask(Task task) {
        int id = idGenerator++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public Integer createNewEpic(Epic epic) {
        int id = idGenerator++;
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    public Integer createNewSubTask(SubTask subTask, int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        int id = idGenerator++;
        subTask.setId(id);
        subTasks.put(id, subTask);
        epic.addSubTaskId(id);
        updateEpic(epics.get(epicId));
        return id;
    }

    public void updateTask (Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic (Epic newEpic) {
        Epic epicToUpdate = getEpicById(newEpic.getId());

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

    public void updateSubTask (SubTask newSubTask, int epicId) {
        SubTask subTaskToUpdate = getSubTaskById(newSubTask.getId());

        subTaskToUpdate.setStatus(newSubTask.getStatus());
        Epic epicToUpdate = getEpicById(epicId);
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
}