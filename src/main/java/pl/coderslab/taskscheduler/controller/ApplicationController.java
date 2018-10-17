package pl.coderslab.taskscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.taskscheduler.model.*;
import pl.coderslab.taskscheduler.service.TaskService;
import pl.coderslab.taskscheduler.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
@Configuration
@ComponentScan("pl.coderslab.taskscheduler")
public class ApplicationController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @RequestMapping(value = { "/", "/board"}, method = RequestMethod.GET)
    public String board(ModelMap model){
        List<Task> newTasks = taskService.findTaskByStatus(TaskStatusOptions.NEW);
        model.addAttribute("newTasks", newTasks);
        System.out.println(newTasks);
        List<Task> activeTasks = taskService.findTaskByStatus(TaskStatusOptions.ACTIVE);
        model.addAttribute("activeTasks", activeTasks);
        List<Task> resolvedTasks = taskService.findTaskByStatus(TaskStatusOptions.RESOLVED);
        model.addAttribute("resolvedTasks", resolvedTasks);
        List<Task> closedTasks = taskService.findTaskByStatus(TaskStatusOptions.CLOSED);
        model.addAttribute("closedTasks", closedTasks);
        return "board";
    }

    @RequestMapping(value = {"/newTask"}, method = RequestMethod.GET)
    public String newTask(ModelMap model){
        Task task = new Task();
        List<TaskType> types = new ArrayList<>( Arrays.asList(TaskType.values()));
        model.addAttribute("types", types);
        model.addAttribute("task", task);
        model.addAttribute("edit", false);
        return "taskCreator";
    }

    @RequestMapping(value = {"/newTask"}, method = RequestMethod.POST)
    public String saveTask(@Valid @ModelAttribute Task task, BindingResult result, ModelMap model){

        if(result.hasErrors()){
            return "taskCreator";
        }

        taskService.saveTask(task);

        model.addAttribute("success", "New task: " + task.getTitle() + " created successfully");
        return "success";
    }

    @RequestMapping(value = { "/edit-{id}-task" }, method = RequestMethod.GET)
    public String editTask(@PathVariable String id, ModelMap model) {
        Task task = taskService.findById(Long.parseLong(id));
        model.addAttribute("task", task);
        model.addAttribute("edit", true);
        return "taskCreator";
    }

    @RequestMapping(value = { "/edit-{id}-task" }, method = RequestMethod.POST)
    public String updateTask(@Valid @ModelAttribute Task task, BindingResult result,
                                 ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "taskCreator";
        }
        taskService.updateTask(task);
        return  "redirect:/board";
    }

    @RequestMapping(value = { "/delete-{id}-task" }, method = RequestMethod.GET)
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTaskbyId(Long.parseLong(id));
        return "redirect:/board";
    }

    @RequestMapping(value = {"/newUser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = {"/newUser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model){

        if(result.hasErrors()){
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "New user: " + user.getName() +" " + user.getSurname() + " created successfully");
        return "success";
    }

    @RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String id, ModelMap model) {
        User user = userService.findById(Long.parseLong(id));
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    @RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                                 ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User: " + user.getName() +" " + user.getSurname() + " updated successfully");
        return "success";
    }

    @RequestMapping(value = { "/delete-{id}-user" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id) {
        userService.deleteUserbyId(Long.parseLong(id));
        return "redirect:/board";
    }

    @RequestMapping(value = {"/assign-{id}-task"}, method = RequestMethod.GET)
    public String assignPanel(@PathVariable String id, ModelMap model) {
        Task task = taskService.findById(Long.parseLong(id));
        model.addAttribute("task", task);
        AssignModel aModel = new AssignModel();
        model.addAttribute("aModel", aModel);
        return "assign";
    }

    @RequestMapping(value = {"/assign-{id}-task"}, method = RequestMethod.POST)
    public String assignUserToTask(@Valid @ModelAttribute("aModel") AssignModel aModel, BindingResult result,
                                   ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "assign";
        }

        List<User> mayBeUser = userService.findByUserNameAndSurname(aModel.getName(),aModel.getSurname());
        if(mayBeUser.isEmpty()){
            ObjectError error = new ObjectError("mayBeUser","No such user in Database");
            result.addError(error);
            return "assign";
        }
        User user = mayBeUser.get(0);
        long parsedId = Long.parseLong(id);
        Task task = taskService.findById(parsedId);
        task.setUser(user);
        task.setStatus(TaskStatusOptions.ACTIVE);
        taskService.updateTask(task);

        model.addAttribute("success", "Task: " + task.getTitle() + " updated successfully");
        return "success";
    }

    @RequestMapping(value = "/view-{id}-task", method = RequestMethod.GET )
    public String taskview(@PathVariable String id, ModelMap model) {
        Task task = taskService.findById(Long.parseLong(id));
        model.addAttribute("task", task);
        return "taskView";
    }

    @RequestMapping(value = { "/set-{id}-taskN" }, method = RequestMethod.GET)
    public String setTaskStatusToNew(@PathVariable String id) {
        Task task = taskService.findById(Long.parseLong(id));
        task.setStatus(TaskStatusOptions.NEW);
        taskService.updateTask(task);
        return "redirect:/board";
    }

    @RequestMapping(value = { "/set-{id}-taskA" }, method = RequestMethod.GET)
    public String setTaskStatusToActive(@PathVariable String id) {
        Task task = taskService.findById(Long.parseLong(id));
        task.setStatus(TaskStatusOptions.ACTIVE);
        task.setActivated(task.getActivated());
        taskService.updateTask(task);
        return "redirect:/board";
    }
    @RequestMapping(value = { "/set-{id}-taskR" }, method = RequestMethod.GET)
    public String setTaskStatusToResolved(@PathVariable String id) {
        Task task = taskService.findById(Long.parseLong(id));
        task.setStatus(TaskStatusOptions.RESOLVED);
        task.setResolved(task.getResolved());
        taskService.updateTask(task);
        return "redirect:/board";
    }
    @RequestMapping(value = { "/set-{id}-taskC" }, method = RequestMethod.GET)
    public String setTaskStatusToClosed(@PathVariable String id) {
        Task task = taskService.findById(Long.parseLong(id));
        task.setStatus(TaskStatusOptions.CLOSED);
        task.setClosed(task.getClosed());
        taskService.updateTask(task);
        return "redirect:/board";
    }

}
