package pl.coderslab.taskscheduler.dao;

import pl.coderslab.taskscheduler.model.Task;
import pl.coderslab.taskscheduler.model.TaskStatusOptions;
import pl.coderslab.taskscheduler.model.TaskType;

import java.util.Date;
import java.util.List;

public interface TaskDao {

    Task findById(long id);

    void saveTask(Task task);

    void deleteTaskbyId(long id);

    List<Task> findAllTasks();

    List<Task> findTaskByUserId(long user_id);

    List<Task> findTasksbyDate(Date after, Date before);

    List<Task> findTaskByType(TaskType type);

    List<Task> findTaskByStatus(TaskStatusOptions status);

}
