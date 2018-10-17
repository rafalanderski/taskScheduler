package pl.coderslab.taskscheduler.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.coderslab.taskscheduler.model.Task;
import pl.coderslab.taskscheduler.model.TaskStatusOptions;
import pl.coderslab.taskscheduler.model.TaskType;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends AbstractDao <Long, Task> implements TaskDao {


    public Task findById(long id) {
        return getByKey(id);
    }

    public void saveTask(Task task) {
        persist(task);
    }

    public void deleteTaskbyId(long id) {
        Query query = getSession().createSQLQuery("delete from tasks where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    public List<Task> findAllTasks() {
        Criteria criteria = createEntityCriteria();
        return (List<Task>) criteria.list();
    }

    public List<Task> findTaskByUserId(long user_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user_id", user_id));
        return (List<Task>) criteria.list();
    }

    public List<Task> findTasksbyDate(Date after, Date before) {
        Criteria criteria= createEntityCriteria();
        criteria.add(Restrictions.between("created", after , before));
        return (List<Task>) criteria.list();
    }

    public List<Task> findTaskByType(TaskType type) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("type", type));
        return (List<Task>) criteria.list();
    }

    public List<Task> findTaskByStatus(TaskStatusOptions status){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("status", status));
        return (List<Task>) criteria.list();
    }
}
