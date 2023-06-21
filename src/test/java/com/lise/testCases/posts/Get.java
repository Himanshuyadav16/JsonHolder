package com.lise.testCases.posts;

import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class Get extends BaseClass {
    @Test
    public void getPostTest() {
        Response response = getPost();

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), notNullValue());
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getPostIdTest() {
        Response response = getPostId();

        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), notNullValue());
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getPostIdComment() {
        Response response = getPostIdComments();

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("postId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getCommentByPostIdTest() {
        Response response = getPostIdOnComment();

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("postId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    // Get Post Method
    public Response getPost() {
        Response response = given()
                .request(Method.GET, "/posts");
        return response;
    }

    //Get Post Id Method
    public Response getPostId() {
        Response response = given()
                .request(Method.GET, "/posts/1");
        return response;
    }

    //Get Post Id Comments Method
    public Response getPostIdComments() {
        Response response = given()
                .request(Method.GET, "/posts/1/comments");
        return response;
    }

    //Get  PostId on Comment
    public Response getPostIdOnComment() {
        Response response = given()
                .param("postId", 1)
                .when()
                .request(Method.GET, "/comments");
        return response;
    }
}