package com.lise.testCases.comments;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Delete extends BaseClass {
 @Test
 public void deleteCommentsById(){
     Faker faker = new Faker();
     String userName = faker.name().name();
     String userEmail = faker.internet().emailAddress();
     String userBody = "{\n" +
             "    \"name\": \"" + userName + "\",\n" +
             "    \"email\": \"" + userEmail + "\"\n" +
             "  }";
     Response userResponse = createUser(userBody);

     assertThat(userResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

     JSONObject postData = new JSONObject(userResponse.asString());

     assertThat(postData.getString("name"), is(userName));
     assertThat(postData.getString("email"), is(userEmail));

     int userId = postData.getInt("id");
     String postTitle = "foo";
     String postPostsBody = "bar";

     String postBody = "{ \"userId\": \"" + userId + "\",\n" +
             "    \"title\": \"" + postTitle + "\",\n" +
             "    \"body\": \"" + postPostsBody + "\"\n" +
             "  }";

     Response postResponse = createPost(postBody);

     assertThat(postResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

     JSONObject jsonPost = new JSONObject(postResponse.asString());

     assertThat(jsonPost.getInt("userId"), is(userId));
     assertThat(jsonPost.getInt("id"), notNullValue());
     assertThat(jsonPost.getString("title"), is(postTitle));
     assertThat(jsonPost.getString("body"), is(postPostsBody));

     int postId = jsonPost.getInt("id");

     String commentName = faker.name().name();
     String commentEmail = faker.internet().emailAddress();
     String commentPostBody = "laudantium enim quasi est quidem magnam voluptate ipsam eosntempora quo";

     String commentBody = "{\n" +
             "    \"postId\": " + postId + ",\n" +
             "    \"name\": \"" + commentName + "\",\n" +
             "    \"email\": \"" + commentEmail + "\",\n" +
             "    \"body\": \"" + commentPostBody + "\"\n" +
             "  }";

     Response commentResponse = createComment(commentBody);

     assertThat(commentResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

     JSONObject jsonObjectComment = new JSONObject(commentResponse.asString());

     assertThat(jsonObjectComment.getInt("id"), notNullValue());
     assertThat(jsonObjectComment.getInt("postId"), is(postId));
     assertThat(jsonObjectComment.getString("name"), is(commentName));
     assertThat(jsonObjectComment.getString("email"), is(commentEmail));
     assertThat(jsonObjectComment.getString("body"), is(commentPostBody));

     int commentId = jsonObjectComment.getInt("id");

     Response  commentDeleteResponse=deleteCommentById(commentId);
     assertThat(commentDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));

     Response  postDeleteResponse=deleteCommentById(postId);
     assertThat(postDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));

     Response  userDeleteResponse=deleteCommentById(userId);
     assertThat(userDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));
 }
 //Delete A Comments
    public Response deleteCommentById( int id){
     Response response=given()
             .request(Method.DELETE,"/comments/"+id);
             return  response;
    }
}