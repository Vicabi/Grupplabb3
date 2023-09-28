package com.example.grupplabb3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;

    @Mock
    private TodoRepository todoRepository;

    @MockBean
    private MockMvc mockMvc;

    @Autowired
    private TodoController todoController;

    @BeforeEach
    void setUp() {
        todoController = new TodoController();
    }

    // Get all posts
    @Test
    void getAllTodoItemsListShouldReturnFalseIfNoItemsFound() {

    }

    @Test
    void getAllTodoItemsListShouldReturnTrueIfItemsAreFound() {

        // Preppa med ett item
        TodoItem item = new TodoItem();
        item.setDescription("Item 1");
        item.setCompleted(false);
        todoRepository.save(item);

        assertEquals(1, todoController.getAllTodoItems().size());
    }

    @AfterEach
    void tearDown() {
    }
}