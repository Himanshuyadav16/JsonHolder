package com.lise.testCases.users;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import com.lise.models.users.UserPutBody;
import com.lise.models.users.UserPutResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Put extends BaseClass {
    @Test
    public void updateUserById() {
        Faker faker = new Faker();
        UserPostBody userPostBody =new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());

        UserPostResponse userPostResponse=createUser(userPostBody);

        assertThat(userPostResponse.getName(),is(userPostBody.name));
        assertThat(userPostResponse.getEmail(),is(userPostBody.email));
        assertThat(userPostResponse.getId(),notNullValue());

        int userId = 1;
         UserPutBody userPutBody=new UserPutBody();
         userPutBody.setName(faker.name().name());
         userPutBody.setEmail(faker.internet().emailAddress());

         UserPutResponse userPutResponse=updateUserById(userPutBody,userId);

         assertThat(userPutResponse.getName(),is(userPutBody.name));
         assertThat(userPutResponse.getEmail(),is(userPutBody.email));
         assertThat(userPutResponse.getId(),notNullValue());
    }

    //  Create User
    public UserPostResponse createUser(UserPostBody userPostBody) {
        UserPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(userPostBody)
                .when()
                .request(Method.POST, "/users")
                .as(UserPostResponse.class);
        return response;
    }

    //Put User By Id
    public UserPutResponse updateUserById(UserPutBody body, int id) {
        UserPutResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/users/" + id)
                .as(UserPutResponse.class);
        return response;
    }
}