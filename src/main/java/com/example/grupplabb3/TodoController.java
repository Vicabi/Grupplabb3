package com.example.grupplabb3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public List<TodoItem> getAllTodoItems() {
        return todoRepository.findAll();
    }

    @PostMapping("/todos")
    public TodoItem createNewTodoItem(@RequestBody TodoItem item) {
        return todoRepository.save(item);
    }
}
