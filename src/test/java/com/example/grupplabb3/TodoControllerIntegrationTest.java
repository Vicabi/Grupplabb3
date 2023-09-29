package com.example.grupplabb3;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TodoRepository todoRepository;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @DisplayName("Should load context and assert that dependencies are not null")
  void contextLoads() {
    Assertions.assertNotNull(todoRepository);
    Assertions.assertNotNull(mockMvc);
  }

  @Test
  void testGetTodos() throws Exception {

    TodoItem temp = todoRepository.save(new TodoItem("Item 1"));

    // Perform a GET request to /todos. Expect a 200 OK response and a JSON array
    mockMvc.perform(get("/todos")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[-1].id").value(temp.getId()))
        .andExpect(jsonPath("$[-1].description").value("Item 1"))
        .andExpect(jsonPath("$[-1].completed").value(false));

    // Clean up
    todoRepository.deleteById(temp.getId());

  }

  @Test
  void testCreateNewTodo() throws Exception {

    // Serialize a TodoItem object to JSON
    String jsonTodo = objectMapper.writeValueAsString(new TodoItem("Item 2"));

    // Perform a POST request with the JSON data. Expect a 200 OK response and a
    // JSON object
    MvcResult result = mockMvc.perform(post("/todos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonTodo))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.description").value("Item 2"))
        .andExpect(jsonPath("$.completed").value(false))
        .andReturn();

    // Clean up
    MockHttpServletResponse response = result.getResponse();
    String json = response.getContentAsString();
    TodoItem todo = objectMapper.readValue(json, TodoItem.class);
    todoRepository.deleteById(todo.getId());
  }

}
