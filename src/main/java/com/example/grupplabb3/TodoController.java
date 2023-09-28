package com.example.grupplabb3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    // Get list of all entries

    @GetMapping("/items")
    public List<TodoItem> getAllTodoItems() {
        return todoRepository.findAll();
    }

    // Post new entry
}
