package com.lise.testCases.posts;

import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Get extends BaseClass {
    @Test
    public void getAllPosts() {

        Response response = getAllPost();

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), notNullValue());
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getPostById() {
        int id=1;
        Response response = getPostById(id);

        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("title"), notNullValue());
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getAllCommentByPostsId() {
        int id=1;
        Response response = getCommentByPostId(id);

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("postId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }

    @Test
    public void getAllPostByUserId() {
        int id=1;
        Response response = getAllPostByUserId(id);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getInt("userId"), is(1));
        assertThat(jsonObject.getString("title"), notNullValue());
        assertThat(jsonObject.getString("body"), notNullValue());
    }
    @Test
    public void getAllAlbumByPostId(){
        int id=1;
        Response response=getAlbumByPostId(id);

        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONArray jsonArray=new JSONArray(response.asString());

        JSONObject jsonObject=jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("title"),is("quidem molestiae enim"));
    }

    @Test
    public void getAllTodosByPostId() {
        int id = 1;
        Response response = getTodosByPostId(id);

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("userId"), is(1));
        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("title"), notNullValue());
        assertThat(jsonObjectData.getBoolean("completed"), is(false));
    }

    @Test
    public void getAllPhotoByPostId(){
        int id=1;
        Response response=getPhotoByPostId(id);

        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONArray jsonArray=new JSONArray(response.asString());

        JSONObject jsonObject=jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("albumId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("title"),is("accusamus beatae ad facilis cum similique qui sunt"));
        assertThat(jsonObject.getString("url"),is("https://via.placeholder.com/600/92c952"));
        assertThat(jsonObject.getString("thumbnailUrl"),is("https://via.placeholder.com/150/92c952"));
    }
    // Get  all Posts
    public Response getAllPost() {
        Response response = given().request(Method.GET, "/posts");
        return response;
    }

    // Get a Post By Id
    public Response getPostById(int id) {
        Response response = given().request(Method.GET, "/posts/"+id);
        return response;
    }

    //Get All Comments By PostId
    public Response getCommentByPostId(int id) {
        Response response = given().request(Method.GET, "/posts/"+id+"/comments");
        return response;
    }

    //Get All posts by User Id
    public Response getAllPostByUserId(int id) {
        Response response = given()
                .param("userId", id)
                .when()
                .request(Method.GET, "/posts");
        return response;
    }
    //Get All Albums By posts Id
    public Response getAlbumByPostId(int id){
        Response response=given()
                .request(Method.GET,"/posts/"+id+"/albums");
        return response;
    }
    //Get All todos By postId
    public Response getTodosByPostId(int id) {
        Response response = given()
                .request(Method.GET, "/posts/" + id + "/todos");
        return response;
    }
    //Get All Photos by  PostId
    public Response getPhotoByPostId(int id){
        Response response=given()
                .request(Method.GET,"/posts/"+id+"/photos");
        return response;
    }
}