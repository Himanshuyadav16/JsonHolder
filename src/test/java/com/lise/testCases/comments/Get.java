package com.lise.testCases.comments;

import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class Get extends BaseClass {
    @Test
    public void getCommentsTest() {
        Response response = getComments();

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("id"), notNullValue());
        assertThat(jsonObject.getInt("postId"), notNullValue());
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }
    @Test
    public void getCommentIdTest(){
        Response response=getCommentId();

        JSONObject jsonObject=new JSONObject(response.asString());

        assertThat(jsonObject.getInt("id"), notNullValue());
        assertThat(jsonObject.getInt("postId"), notNullValue());
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    //Get Comments Methods
    public Response getComments() {
        Response response = given()
                .request(Method.GET, "/comments");
        return response;
    }

    //Get Comment Id Method
    public Response getCommentId(){
        Response response=given()
                .request(Method.GET,"/comments/1");
        return response;
    }
    //Get Comment Id


}