package com.example.grupplabb3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Grupplabb3EndpointSmokeTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should access /todos endpoint and return 200 OK")
    void todosEndpointReturnsOk() {
        RestAssured
            .given()
            .when()
                .get("/todos")
            .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Should access /todos endpoint and return JSON array")
    void createTodoEndpointAcceptsPost() {

        // Skapar ett nytt item att testa mot description.
        TodoItem item = new TodoItem("Item 1");

        RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(item)
            .when()
                .post("/todos")
            .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("description", equalTo("Item 1"));
    }
}

