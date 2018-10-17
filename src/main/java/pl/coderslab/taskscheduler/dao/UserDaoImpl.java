package pl.coderslab.taskscheduler.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.coderslab.taskscheduler.model.User;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
    public User findById(long id) {
        return getByKey(id);
    }

    public void saveUser(User user) {
        persist(user);
    }

    public void deleteUserbyId(long id) {
        Query query = getSession().createSQLQuery("delete from users where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    public List<User> findByUserName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (List<User>) criteria.list();
    }

    public List<User> findByUserSurname(String surname) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("surname", surname));
        return (List<User>) criteria.list();
    }

    public List<User> findByUserNameAndSurname(String name,String surname) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("surname", surname));
        criteria.add(Restrictions.eq("name", name));
        return (List<User>) criteria.list();
    }

}
