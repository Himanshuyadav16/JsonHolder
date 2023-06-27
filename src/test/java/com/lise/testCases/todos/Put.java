package com.lise.testCases.todos;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.todos.TodoPostBody;
import com.lise.models.todos.TodoPostResponse;
import com.lise.models.todos.TodoPutBody;
import com.lise.models.todos.TodoPutResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Put extends BaseClass {
    @Test
    public void updateTodoById() {
        Faker faker = new Faker();
        UserPostBody userPostBody =new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());

        UserPostResponse userPostResponse=createUser(userPostBody);

        assertThat(userPostResponse.getName(),is(userPostBody.name));
        assertThat(userPostResponse.getEmail(),is(userPostBody.email));
        assertThat(userPostResponse.getId(),notNullValue());

        int userId = userPostResponse.getId();

        TodoPostBody todoPostBody=new TodoPostBody();
        todoPostBody.setUserId(userId);
        todoPostBody.setTitle("enderit");
        todoPostBody.setCompleted(false);

        TodoPostResponse todoPostResponse=createTodo(todoPostBody);

        assertThat(todoPostResponse.getId(),notNullValue());
        assertThat(todoPostResponse.getTitle(),is(todoPostBody.title));
        assertThat(todoPostResponse.getUserId(),is(userId));
        assertThat(todoPostResponse.isCompleted(),is(todoPostBody.completed));

        int todoId = 1;

        TodoPutBody todoPutBody=new TodoPutBody();
        todoPutBody.setUserId(userId);
        todoPutBody.setTitle("enderit");
        todoPutBody.setCompleted(true);

        TodoPutResponse todoPutResponse=updateTodo(todoPutBody,todoId);

        assertThat(todoPutResponse.getId(),is(todoId));
        assertThat(todoPutResponse.getUserId(),is(userId));
        assertThat(todoPutResponse.getTitle(),is(todoPostBody.title));
        assertThat(todoPutResponse.isCompleted(),is(todoPutBody.completed));

        Response deleteTodoResponse=deleteTodoById(todoId);

        Response deleteUserResponse=deleteTodoById(userId);
    }

    // update Todos By Id
    public TodoPutResponse updateTodo(TodoPutBody body, int id) {
        TodoPutResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/todos/" + id)
                .as(TodoPutResponse.class);
        return response;
    }
}