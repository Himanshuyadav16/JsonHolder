package com.lise.testCases.photos;

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

public class Get extends BaseClass {
    @Test
    public void getAllPhotos() {
        Response response = getAllPhoto();

        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("albumId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("accusamus beatae ad facilis cum similique qui sunt"));
        assertThat(jsonObject.getString("url"), is("https://via.placeholder.com/600/92c952"));
        assertThat(jsonObject.getString("thumbnailUrl"), is("https://via.placeholder.com/150/92c952"));
    }

    @Test
    public void getPhotoById() {
        int id = 1;
        Response response = getPhotoById(id);

        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getInt("albumId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("accusamus beatae ad facilis cum similique qui sunt"));
        assertThat(jsonObject.getString("url"), is("https://via.placeholder.com/600/92c952"));
        assertThat(jsonObject.getString("thumbnailUrl"), is("https://via.placeholder.com/150/92c952"));
    }

    @Test
    public void getAllPostByPhotoId() {
        int id = 1;
        Response response = getAllPostByPhotoId(id);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getAllCommentsByPhotoId() {
        int id = 1;
        Response response = getCommentsByPhotoId(id);

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
    public void getAllTodosByPhotoId() {
        int id = 1;
        Response response = getTodosByPhotoId(id);

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("userId"), is(1));
        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("title"), notNullValue());
        assertThat(jsonObjectData.getBoolean("completed"), is(false));
    }

    @Test
    public void getAllAlbumByPhotoId() {
        int id = 1;
        Response response = getAlbumByPhotoId(id);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), is("quidem molestiae enim"));
    }

    // Get  all photos
    public Response getAllPhoto() {
        Response response = given().request(Method.GET, "/photos");
        return response;
    }

    //Get photo By Id
    public Response getPhotoById(int id) {
        Response response = given()
                .request(Method.GET, "/photos/" + id);
        return response;
    }

    //Get posts By  PhotoId
    public Response getAllPostByPhotoId(int id) {
        Response response = given()
                .request(Method.GET, "/photos/" + id + "/posts");
        return response;
    }

    //Get comments By  PhotoId
    public Response getCommentsByPhotoId(int id) {
        Response response = given()
                .request(Method.GET, "/photos/" + id + "/comments");
        return response;
    }

    //Get todos By photoId
    public Response getTodosByPhotoId(int id) {
        Response response = given()
                .request(Method.GET, "/photos/" + id + "/todos");
        return response;
    }

    //Get Albums By  PhotoId
    public Response getAlbumByPhotoId(int id) {
        Response response = given()
                .request(Method.GET, "/photos/" + id + "/albums");
        return response;
    }
}