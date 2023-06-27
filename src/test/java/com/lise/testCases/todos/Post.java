package com.lise.testCases.todos;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.todos.TodoPostBody;
import com.lise.models.todos.TodoPostResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Post extends BaseClass {
    @Test
    public void createTodo() {
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

        int todoId=todoPostResponse.getId();

        Response deleteTodoResponse=deleteTodoById(todoId);

        Response deleteUserResponse=deleteTodoById(userId);
    }

}