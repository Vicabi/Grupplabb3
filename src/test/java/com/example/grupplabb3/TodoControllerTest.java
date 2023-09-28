package com.example.grupplabb3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;

    private MockMvc mvc;
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    // Get all posts
    @Test
    void getAllTodoItemsListShouldReturnTrueIfNoItemsFound() {

        assertTrue(todoController.getAllTodoItems().isEmpty());
    }

    @Test
    void getAllTodoItemsListShouldReturnTrueIfItemsAreFound() {

        // Lägga till en lista med items
        List<TodoItem> todoItemList = new ArrayList<>();
        todoItemList.add(new TodoItem("Item 1", true));
        todoItemList.add(new TodoItem("Item 2", false));

        // När todoRepository.findAll() anropas returnera todoItemList istället.
        when(todoRepository.findAll()).thenReturn(todoItemList);
        assertEquals(2, todoController.getAllTodoItems().size());
    }

    @AfterEach
    void tearDown() {
    }
}