package com.lise.testCases.posts;


import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class FindUser extends BaseClass {
    @Test
    public void findUserTest() {
        Response response = findUserPost();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getString("title"), notNullValue());
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    //Get  User on postId
    public Response findUserPost() {
        Response response = given()
                .param("userId", 1)
                .when()
                .request(Method.GET, "/posts");
        return response;
    }
}