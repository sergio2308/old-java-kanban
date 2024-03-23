import Manager.TaskManager;//Добрый день. Если это не слишком нагло, вы немогли бы зачесть работу, чтобы открылся 5
import Tasks.Epic;         //спринт? В этом случае мне разрешат пройти его на каникулах. Я отстал от группы на пару
import Tasks.Status;       //недель, не хотелось бы менять наставника и группу. Если нет больших косяков, конечно.
import Tasks.SubTask;      //Я поправлю все по вашим замечаниям. Проблема с лишними id эпиков в сабтасках уйдет, после
import Tasks.Task;         //удаления проверок из Main, насколько я понял. Но на функционал это не влияет, вроде бы.
                           //Вообще, такая проверка печатью немного мешает нормально посторить логику программы, мне
public class Main {        //кажется. Интуитивно кажется, что можно все сделать проще чем в ТЗ, а то немного запутался.
    public static void main(String[] args) {
    TaskManager taskManager = new TaskManager();

    Task task1 = new Task("Покупка продуктов", "Помидоры, молоко, хлеб, чай", Status.NEW);
    Task task2 = new Task("Оправить посылку", "Сходить на почту и отправить посылку", Status.NEW);
    Epic epic1 = new Epic("Выучить испанский", "Выучить испанский язык", Status.NEW);
    SubTask subTask1 = new SubTask("Словарь", "Купить словарь испанского языка", epic1.getId(),
            Status.NEW);
    SubTask subTask2 = new SubTask("Преподаватель", "Найти преподавателя испанского языка",
            epic1.getId(), Status.NEW);
    Epic epic2 = new Epic("Отпуск", "Подготовиться к отпуску", Status.NEW);
    SubTask subTask3 = new SubTask("Билет", "Купить билет", epic2.getId(), Status.NEW);

    taskManager.createNewTask(task1);
    taskManager.createNewTask(task2);
    taskManager.createNewEpic(epic1);
    taskManager.createNewSubTask(subTask1);
    taskManager.addNewSubTask(subTask1.getId(), epic1.getId(), subTask1);
    taskManager.createNewSubTask(subTask2);
    taskManager.addNewSubTask(subTask2.getId(), epic1.getId(), subTask2);
    taskManager.createNewEpic(epic2);
    taskManager.createNewSubTask(subTask3);
    taskManager.addNewSubTask(subTask3.getId(), epic2.getId(), subTask3);

    System.out.println("Список обычных задач: " + taskManager.getTasks().toString());
    System.out.println("Список эпиков: " + taskManager.getEpics().toString());
    System.out.println("Список подзадач: " + taskManager.getSubTasks().toString());

    Task task3 = new Task("Покупка продуктов", "Помидоры, молоко, хлеб, чай", Status.DONE);
    task3.setId(task1.getId());
    taskManager.updateTask(task3);
    Task task4 = new Task("Оправить посылку", "Сходить на почту и отправить посылку",
            Status.IN_PROGRESS);
    task4.setId(task2.getId());
    taskManager.updateTask(task4);

    SubTask subTask4 = new SubTask("Словарь", "Купить словарь испанского языка", epic1.getId(),
                Status.IN_PROGRESS);
    subTask4.setId(subTask1.getId());
    taskManager.addNewSubTask(subTask4.getId(), epic1.getId(), subTask4);
    taskManager.updateSubTask(subTask4);

    SubTask subTask5 = new SubTask("Билет", "Купить билет", epic2.getId(), Status.DONE);
    subTask5.setId(subTask3.getId());
    taskManager.addNewSubTask(subTask5.getId(), epic2.getId(), subTask5);
    taskManager.updateSubTask(subTask5);

    System.out.println();
    System.out.println("Список обычных задач: " + taskManager.getTasks().toString());
    System.out.println("Список эпиков: " + taskManager.getEpics().toString());
    System.out.println("Список подзадач: " + taskManager.getSubTasks().toString());

    taskManager.removeTaskById(task1.getId());
    taskManager.removeEpicById(epic1.getId());

    System.out.println();
    System.out.println("Список обычных задач: " + taskManager.getTasks().toString());
    System.out.println("Список эпиков: " + taskManager.getEpics().toString());
    System.out.println("Список подзадач: " + taskManager.getSubTasks().toString());
    }
}