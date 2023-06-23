package com.lise.testCases.todos;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Post extends BaseClass {
    @Test
    public void createTodo() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userBody = " {\n" +
                "            \"name\": \"" + userName + "\",\n" +
                "        \"email\": \"" + userEmail + "\"\n" +
                "    }";
        Response userResponse = createUser(userBody);

        assertThat(userResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject jsonObjectUser = new JSONObject(userResponse.asString());

        assertThat(jsonObjectUser.getString("name"), is(userName));
        assertThat(jsonObjectUser.getString("email"), is(userEmail));
        assertThat(jsonObjectUser.getInt("id"), notNullValue());

        int userId = jsonObjectUser.getInt("id");

        String todoTitle = "enderit";
        boolean todoCompleted =false ;
        String todoBody = "{ \n" +
                "    \"userId\":" + userId + ",\n" +
                "    \"title\": \"" + todoTitle + "\",\n" +
                "    \"completed\": \"" + todoCompleted + "\"\n" +
                "  }";
        Response todoResponse = createTodo(todoBody);

        JSONObject jsonObjectTodo = new JSONObject(todoResponse.asString());

        assertThat(jsonObjectTodo.getInt("userId"), is(userId));
        assertThat(jsonObjectTodo.getInt("id"), notNullValue());
        assertThat(jsonObjectTodo.getString("title"), is(todoTitle));
        assertThat(jsonObjectTodo.getBoolean("completed"), is(todoCompleted));
    }

}