package pl.coderslab.taskscheduler.dao;

import pl.coderslab.taskscheduler.model.User;

import java.util.List;

public interface UserDao {

    User findById(long id);

    void saveUser(User user);

    void deleteUserbyId(long id);

    List<User> findByUserName(String name);

    List<User> findByUserSurname(String surname);

    List<User> findByUserNameAndSurname(String name,String surname);
}
