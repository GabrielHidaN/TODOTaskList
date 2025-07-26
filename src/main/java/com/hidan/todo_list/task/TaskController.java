package com.hidan.todo_list.task;


import com.hidan.todo_list.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;


    //create new task

    @PostMapping("/create")
    public TaskModel createTask(@RequestBody TaskModel taskModel){
        var task = taskRepository.save(taskModel);
        return task;
    }
}
