package pl.coderslab.taskscheduler.service;

import pl.coderslab.taskscheduler.model.User;

import java.util.List;


public interface UserService {

    User findById(long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserbyId(long id);

    List<User> findByUserName(String name);

    List<User> findByUserSurname(String surname);

    List<User> findByUserNameAndSurname(String name, String surname);
}
