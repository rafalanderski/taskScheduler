package pl.coderslab.taskscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.taskscheduler.dao.TaskDao;
import pl.coderslab.taskscheduler.model.Task;
import pl.coderslab.taskscheduler.model.TaskStatusOptions;
import pl.coderslab.taskscheduler.model.TaskType;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDao dao;

    public Task findById(long id) {
        return dao.findById(id);
    }

    public void saveTask(Task task) {
        dao.saveTask(task);
    }

    public void updateTask(Task task) {
        Task entity = dao.findById(task.getId());
        System.out.println("Entity **********"+ entity);
        System.out.println("Given task **********" + task);
        if(entity!=null){
            entity.setTitle(task.getTitle());
            entity.setContent(task.getContent());
            entity.setType(task.getType());
            entity.setPriority(task.getPriority());
            entity.setSeverity(task.getSeverity());
            if(task.getUser()!=null) {
                entity.setUser(task.getUser());
            }
            entity.setStatus(task.getStatus());
            entity.setCreated(task.getCreated());
            entity.setActivated(new Date());
            entity.setResolved(new Date());
            entity.setClosed(new Date());
            entity.setModified(task.getModified());
        }
    }

    public void deleteTaskbyId(long id) {
        dao.deleteTaskbyId(id);
    }

    public List<Task> findAllTasks() {
        return dao.findAllTasks();
    }

    public List<Task> findTaskByUserId(long user_id) {
        return dao.findTaskByUserId(user_id);
    }

    public List<Task> findTasksbyDate(Date after, Date before) {
        return dao.findTasksbyDate(after, before);
    }

    public List<Task> findTaskByType(TaskType type) {
        return dao.findTaskByType(type);
    }

    public List<Task> findTaskByStatus(TaskStatusOptions status) {
        return dao.findTaskByStatus(status);
    }
}
