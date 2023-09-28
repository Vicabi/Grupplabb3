package com.example.grupplabb3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class TodoController {

    private static final Logger logger = Logger.getLogger(TodoController.class.getName());
    @Autowired
    private TodoRepository todoRepository;


    // Endpoint för att hämta alla todoitems till en lista.
    @GetMapping("/items")
    public List<TodoItem> getAllTodoItems() {
        List<TodoItem> items = todoRepository.findAll();
        logger.info("No of items: " + items.size()); // Logger för att se hur många items som finns i listan.
        return items;
    }

    // Post new entry
}
