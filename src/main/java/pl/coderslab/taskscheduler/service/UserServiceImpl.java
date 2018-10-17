package pl.coderslab.taskscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.taskscheduler.dao.UserDao;
import pl.coderslab.taskscheduler.model.User;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;

    public User findById(long id) {
        return dao.findById(id);
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setName(user.getName());
            entity.setSurname(user.getSurname());
            entity.setAvatar_url(user.getAvatar_url());
        }
    }

    public void deleteUserbyId(long id) {
        dao.deleteUserbyId(id);
    }

    public List<User> findByUserName(String name) {
        return dao.findByUserName(name);
    }

    public List<User> findByUserSurname(String surname) {
        return dao.findByUserSurname(surname);
    }

    public List<User> findByUserNameAndSurname(String name, String surname){return dao.findByUserNameAndSurname(name, surname);}
}
