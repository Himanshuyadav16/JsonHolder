package com.lise;

import com.lise.utils.ApplicationProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class BaseClass {

    @BeforeSuite
    public void BeforeSuiteTest() {
        RestAssured.baseURI = ApplicationProperties.INSTANCE.getUrl();
    }
    // Create posts Method
    public Response createPost(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts");
        return response;
    }

    //User Post Method
    public Response createUsers(String userBody) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(userBody)
                .request(Method.POST, "/users");
        return response;
    }
    // Comments Post Method
    public Response commentPost(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/comments");
        return response;
    }
}
