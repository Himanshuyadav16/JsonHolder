package com.lise.testCases.users;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Post extends BaseClass {
    @Test
    public void createUser() {
        Faker faker = new Faker();
        UserPostBody userPostBody =new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());

        UserPostResponse userPostResponse=createUser(userPostBody);

        assertThat(userPostResponse.getName(),is(userPostBody.name));
        assertThat(userPostResponse.getEmail(),is(userPostBody.email));
        assertThat(userPostResponse.getId(),notNullValue());

        int userId=userPostResponse.getId();

        Response userResponseDelete =deleteUserById(userId);

    }

}