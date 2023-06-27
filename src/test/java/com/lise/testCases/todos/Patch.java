package com.lise.testCases.todos;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.todos.*;
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

public class Patch extends BaseClass {
    @Test
    public void patchTodoById() {
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

        TodoPatchBody todoPatchBody=new TodoPatchBody();
        todoPatchBody.setUserId(userId);
        todoPatchBody.setTitle("enderit");
        todoPatchBody.setCompleted(true);

        TodoPatchResponse todoPatchResponse=patchTodo(todoPatchBody,todoId);

        assertThat(todoPatchResponse.getId(),is(todoId));
        assertThat(todoPatchResponse.getUserId(),is(userId));
        assertThat(todoPatchResponse.getTitle(),is(todoPatchBody.title));
        assertThat(todoPatchResponse.isCompleted(),is(todoPatchBody.completed));

        Response deleteTodoResponse=deleteTodoById(todoId);

        Response deleteUserResponse=deleteTodoById(userId);
    }

    // Patch Todos By Id
    public TodoPatchResponse patchTodo(TodoPatchBody body, int id) {
        TodoPatchResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PATCH, "/todos/" + id)
                .as(TodoPatchResponse.class);
        return response;
    }
}