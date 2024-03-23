package Tasks;

public class SubTask extends Task{
    protected int epicId;
    public SubTask(String name, String description, int epicId, Status status) {
        super(name, description, status);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int newEpicId) {
        epicId = newEpicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", id=" + this.getId() +
                ", status=" + this.getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
