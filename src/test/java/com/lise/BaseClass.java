package com.lise;

import com.lise.models.albums.AlbumPostBody;
import com.lise.models.albums.AlbumPostResponse;
import com.lise.models.comments.CommentPostBody;
import com.lise.models.comments.CommentPostResponse;
import com.lise.models.photos.PhotoPostBody;
import com.lise.models.photos.PhotoPostResponse;
import com.lise.models.posts.PostPostBody;
import com.lise.models.posts.PostPostResponse;
import com.lise.models.todos.TodoPostBody;
import com.lise.models.todos.TodoPostResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
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

    //  Create Users
    public UserPostResponse createUser(UserPostBody userPostBody) {
        UserPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(userPostBody)
                .when()
                .request(Method.POST, "/users")
                .as(UserPostResponse.class);
        return response;
    }

    // Create  Posts
    public PostPostResponse createPost(PostPostBody body) {
        PostPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts")
                .as(PostPostResponse.class);
        return response;
    }

    // Create Comments
    public CommentPostResponse createComment(CommentPostBody body) {
        CommentPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/comments")
                .as(CommentPostResponse.class);
        return response;
    }

    // create Todos
    public TodoPostResponse createTodo(TodoPostBody body) {
        TodoPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/todos")
                .as(TodoPostResponse.class);
        return response;
    }

    //Create Albums
    public AlbumPostResponse createAlbum(AlbumPostBody body) {
        AlbumPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts")
                .as(AlbumPostResponse.class);
        return response;
    }

    //create Photos
    public PhotoPostResponse createPhoto(PhotoPostBody body) {
        PhotoPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/photos")
                .then()
                .extract()
                .as(PhotoPostResponse.class);
        return response;
    }

    // Delete User By Id
    public Response deleteUserById(int id) {
        Response response = given()
                .request(Method.DELETE, "/users/" + id);
        return response;
    }

    //Delete Post By Id
    public Response deletePostById(int id) {
        Response response = given()
                .request(Method.DELETE, "/posts/" + id);
        return response;
    }

    //Delete A Comments
    public Response deleteCommentById(int id) {
        Response response = given()
                .request(Method.DELETE, "/comments/" + id);
        return response;
    }

    //delete todos By Id
    public Response deleteTodoById(int id) {
        Response response = given()
                .request(Method.DELETE, "/todos/" + id);
        return response;
    }

    //delete Album By Id
    public Response deleteAlbumById(int id) {
        Response response = given()
                .request(Method.DELETE, "/albums/" + id);
        return response;
    }

    //Delete Photos By Id
    public Response deletePhotoById(int id) {
        Response response = given()
                .request(Method.DELETE, "/photos/" + id);
        return response;
    }

}
