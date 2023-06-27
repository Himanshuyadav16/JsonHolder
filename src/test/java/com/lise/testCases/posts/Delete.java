package com.lise.testCases.posts;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.posts.PostPostBody;
import com.lise.models.posts.PostPostResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Delete extends BaseClass {
    @Test
    public void deletePostById() {
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

        int postId = postPostResponse.getUserId();

        Response  postDeleteResponse=deletePostById(postId);
        assertThat(postDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));

        Response userDeleteResponse=deletePostById(userId);
        assertThat(userDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));
    }

}