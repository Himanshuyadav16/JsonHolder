package com.lise.testCases.album;

import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Get extends BaseClass {
    @Test
    public void getAllAlbums() {
        Response response = getAllAlbum();

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("quidem molestiae enim"));
    }

    @Test
    public void getAlbumById() {
        int id = 1;
        Response response = getAlbumById(id);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("quidem molestiae enim"));
    }

    @Test
    public void getAllAlbumByPostId() {
        int id = 1;
        Response response = getAllAlbumByPostId(id);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("quidem molestiae enim"));
    }

    @Test
    public void getAllPostsByAlbumId() {
        int id = 1;
        Response response = getAllPostsByAlbumId(id);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getAllCommentsByAlbumId() {
        int id = 1;
        Response response = getAllCommentByAlbumId(id);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("postId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getAllTodosByAlbumId() {
        int id = 1;
        Response response = getAllTodosByAlbumId(id);

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("userId"), is(1));
        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("title"), notNullValue());
        assertThat(jsonObjectData.getBoolean("completed"), is(false));
    }

    @Test
    public void getAllPhotoByAlbumId() {
        int id = 1;
        Response response = getAllPhotoByAlbumId(id);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("albumId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("accusamus beatae ad facilis cum similique qui sunt"));
        assertThat(jsonObject.getString("url"), is("https://via.placeholder.com/600/92c952"));
        assertThat(jsonObject.getString("thumbnailUrl"), is("https://via.placeholder.com/150/92c952"));
    }

    //Get All Albums
    public Response getAllAlbum() {
        Response response = given()
                .request(Method.GET, "/albums");
        return response;
    }

    //Get Album By Id
    public Response getAlbumById(int id) {
        Response response = given()
                .request(Method.GET, "/albums/" + id);
        return response;
    }

    //Get Album By PostId
    public Response getAllAlbumByPostId(int id) {
        Response response = given()
                .param("postId", id)
                .when()
                .request(Method.GET, "/albums");
        return response;
    }

    //Get Posts by AlbumId
    public Response getAllPostsByAlbumId(int id) {
        Response response = given()
                .request(Method.GET, "/albums/" + id + "/posts");
        return response;
    }

    //Get All comments by AlbumId
    public Response getAllCommentByAlbumId(int id) {
        Response response = given()
                .request(Method.GET, "/albums/" + id + "/comments");
        return response;
    }

    //Get All todos By AlbumId
    public Response getAllTodosByAlbumId(int id) {
        Response response = given()
                .request(Method.GET, "/albums/" + id + "/todos");
        return response;
    }

    //Get All Photos by AlbumId
    public Response getAllPhotoByAlbumId(int id) {
        Response response = given()
                .request(Method.GET, "/albums/" + id + "/photos");
        return response;
    }

}