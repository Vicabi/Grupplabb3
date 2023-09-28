package com.example.grupplabb3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoControllerUnitTest {

  @InjectMocks
  private TodoController todoController;

  @Mock
  private TodoRepository todoRepository;

  @Test
  @DisplayName("Should load context and assert that dependencies are not null")
  void contextLoads() {
    Assertions.assertNotNull(todoRepository);
    Assertions.assertNotNull(todoController);
  }

  @Test
  @DisplayName("Should return empty array if no todos found")
  void testGetEmptyTodoList() {
    List<TodoItem> emptyTodoList = new ArrayList<>();
    Mockito.when(todoRepository.findAll()).thenReturn(emptyTodoList);
    List<TodoItem> result = todoController.getAllTodoItems();
    assertTrue(result.isEmpty());
    verify(todoRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should return array with one todo if one todo found")
  void testGetTodosWithOneAdded() {
    List<TodoItem> todoList = new ArrayList<>();
    TodoItem item = new TodoItem();
    item.setDescription("Item 1");
    item.setCompleted(false);
    todoList.add(item);
    when(todoRepository.findAll()).thenReturn(todoList);
    List<TodoItem> result = todoController.getAllTodoItems();
    assertEquals(1, result.size());
    verify(todoRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should create new todo")
  void testCreateNewTodo() {
    TodoItem item = new TodoItem();
    item.setDescription("Item 1");
    item.setCompleted(false);
    when(todoRepository.save(item)).thenReturn(item);
    TodoItem result = todoController.createNewTodoItem(item);
    assertEquals(item, result);
    verify(todoRepository, times(1)).save(item);
  }
}

// https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockito-junit-example/