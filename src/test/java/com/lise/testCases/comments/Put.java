package com.lise.testCases.comments;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.comments.CommentPostBody;
import com.lise.models.comments.CommentPostResponse;
import com.lise.models.comments.CommentPutBody;
import com.lise.models.comments.CommentPutResponse;
import com.lise.models.posts.PostPostBody;
import com.lise.models.posts.PostPostResponse;
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
    public void updateCommentById() {
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
        int postId = postPostResponse.getId();

        CommentPostBody commentPostBody=new CommentPostBody();
        commentPostBody.setPostId(postId);
        commentPostBody.setName(faker.name().name());
        commentPostBody.setEmail(faker.internet().emailAddress());
        commentPostBody.setBody("laudantium enim quasi est quidem magnam voluptate ipsam eosntempora quo");

        CommentPostResponse commentPostResponse=createComment(commentPostBody);

        assertThat(commentPostResponse.getId(),notNullValue());
        assertThat(commentPostResponse.getPostId(),is(commentPostBody.postId));
        assertThat(commentPostResponse.getName(),is(commentPostBody.name));
        assertThat(commentPostResponse.getEmail(),is(commentPostBody.email));
        assertThat(commentPostResponse.getBody(),is(commentPostBody.body));

        int commentId = 1;

        CommentPutBody commentPutBody=new CommentPutBody();
        commentPutBody.setPostId(postId);
        commentPutBody.setName(faker.name().name());
        commentPutBody.setEmail(faker.internet().emailAddress());
        commentPutBody.setBody("laudantium enim quasi est quidem magnam voluptate ipsam eosntempora quo");

        CommentPutResponse commentPutResponse=updateCommentbyId(commentPutBody,commentId);

        assertThat(commentPutResponse.getId(),notNullValue());
        assertThat(commentPutResponse.getPostId(),is(commentPutBody.postId));
        assertThat(commentPutResponse.getName(),is(commentPutBody.name));
        assertThat(commentPutResponse.getEmail(),is(commentPutBody.email));
        assertThat(commentPutResponse.getBody(),is(commentPutBody.body));

        Response commentDeleteResponse=deleteCommentById(commentId);

        Response  postDeleteResponse=deleteCommentById(postId);

        Response  userDeleteResponse=deleteCommentById(userId);

    }
    //Update  Comments Method
    public CommentPutResponse updateCommentbyId(CommentPutBody body, int id) {
        CommentPutResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/comments/" + id)
                .as(CommentPutResponse.class);
        return response;
    }
}