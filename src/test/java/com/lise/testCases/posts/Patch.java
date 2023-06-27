package com.lise.testCases.posts;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.posts.*;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Patch extends BaseClass {
    @Test
    public void patchPostById() {
        Faker faker = new Faker();

        UserPostBody userPostBody = new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());

        UserPostResponse userPostResponse = createUser(userPostBody);

        assertThat(userPostResponse.getName(), is(userPostBody.name));
        assertThat(userPostResponse.getEmail(), is(userPostBody.email));
        assertThat(userPostResponse.getId(), notNullValue());

        int userId = userPostResponse.getId();

        PostPostBody postPostBody = new PostPostBody();

        postPostBody.setTitle("foo");
        postPostBody.setBody("bar");
        postPostBody.setUserId(userId);

        PostPostResponse postPostResponse=createPost(postPostBody);

        assertThat(postPostResponse.getTitle(),is(postPostBody.title));
        assertThat(postPostResponse.getBody(),is(postPostBody.body));
        assertThat(postPostResponse.getId(),notNullValue());
        assertThat(postPostResponse.getUserId(),is(userId));

        int postId =userPostResponse.getId();

        PostPatchBody postPatchBody=new PostPatchBody();
        postPatchBody.setTitle("foos");
        postPatchBody.setBody("bars");
        postPatchBody.setUserId(userId);

        PostPatchResponse postPatchResponse=patchPostById(postPatchBody,postId);

        assertThat(postPatchResponse.getTitle(),is(postPatchBody.title));
        assertThat(postPatchResponse.getBody(),is(postPatchBody.body));
        assertThat(postPatchResponse.getUserId(),is(postPatchBody.userId));
        assertThat(postPatchResponse.getId(),is(postId));
    }

    //  Create User
    public UserPostResponse createUser(UserPostBody userPostBody) {
        UserPostResponse response = given()
                .contentType(JSON)
                .body(userPostBody)
                .when()
                .request(Method.POST, "/users")
                .as(UserPostResponse.class);
        return response;
    }

    // Create  Posts
    public PostPostResponse createPost(PostPostBody body) {
        PostPostResponse response = given()
                .contentType(JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts")
                .as(PostPostResponse.class);
        return response;
    }


    // patch A Posts
    public PostPatchResponse patchPostById(PostPatchBody body, int id) {
        PostPatchResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PATCH, "/posts/" + id)
                .as(PostPatchResponse.class);
        return response;
    }
}