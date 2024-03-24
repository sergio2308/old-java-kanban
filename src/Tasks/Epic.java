package Tasks;

import java.util.ArrayList;

public class Epic extends Task{
    protected ArrayList<Integer> subTasksIds;
    public Epic(String name, String description) {
        super(name, description);
        status = Status.NEW;
        subTasksIds = new ArrayList<>();
    }

    public void addSubTaskId(int id) {
        subTasksIds.add(id);
    }

    public ArrayList<Integer> getSubTasksIds() {
        return subTasksIds;
    }

    @Override
    public String toString() {
        return  "Epic{" +
                "name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", subTasksIds='" + getSubTasksIds() +
                "'}'";
    }
}
