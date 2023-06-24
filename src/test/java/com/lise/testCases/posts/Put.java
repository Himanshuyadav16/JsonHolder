package com.lise.testCases.posts;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.posts.PostPostBody;
import com.lise.models.posts.PostPostResponse;
import com.lise.models.posts.PostPutBody;
import com.lise.models.posts.PostPutResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.http.Method;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Put extends BaseClass {

    @Test
    public void updatePostById() {
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

        PostPutBody postPutBody=new PostPutBody();
        postPutBody.setTitle("foos");
        postPutBody.setBody("bars");
        postPutBody.setUserId(userId);

        PostPutResponse postPutResponse=updatePostById(postPutBody,postId);

        assertThat(postPutResponse.getTitle(),is(postPutBody.title));
        assertThat(postPutResponse.getBody(),is(postPutBody.body));
        assertThat(postPutResponse.getUserId(),is(postPutBody.userId));
        assertThat(postPutResponse.getId(),is(postId));
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

    //Post Update Method
    public PostPutResponse updatePostById(PostPutBody body, int id) {
        PostPutResponse response = given()
                .contentType(JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/posts/" + id)
                .as(PostPutResponse.class);
        return response;
    }

}