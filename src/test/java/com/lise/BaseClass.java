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

    // Create  Posts
    public Response createPost(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts");
        return response;
    }

    //  Create User
    public Response createUser(String userBody) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(userBody)
                .when()
                .request(Method.POST, "/users");
        return response;
    }

    // Comments A Post Method
    public Response createComment(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/comments");
        return response;
    }

    // create Todos
    public Response createTodo(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/todos");
        return response;
    }

    //Create Album
    public Response createAlbum(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts");
        return response;
    }
    //create Photos
    public Response createPhoto(String body){
        Response response=given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST,"/photos");
        return response;
    }
}
