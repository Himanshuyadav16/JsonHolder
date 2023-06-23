package com.lise.testCases.comments;

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
    public void getAllComments() {
        Response response = getAllComment();

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("id"), notNullValue());
        assertThat(jsonObject.getInt("postId"), notNullValue());
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }
    @Test
    public void getCommentById(){
        int id=1;
        Response response=getCommentById(id);

        JSONObject jsonObject=new JSONObject(response.asString());

        assertThat(jsonObject.getInt("id"), notNullValue());
        assertThat(jsonObject.getInt("postId"), notNullValue());
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }
    @Test
    public void getAllCommentsByPostId() {
        int id=1;
        Response response = getAllCommentByPostId(id);

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("postId"), is(1));
        assertThat(jsonObject.getInt("id"), is(1));
        assertThat(jsonObject.getString("name"), is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"), is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"), notNullValue());
    }



    @Test
    public void getAlbumByCommentId(){
        int id=1;
        Response response=getAlbumByCommentId(id);

        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONArray jsonArray=new JSONArray(response.asString());

        JSONObject jsonObject=jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("title"),is("quidem molestiae enim"));
    }

    @Test
    public void getTodosByCommentId() {
        int id = 1;
        Response response = getTodosByCommentId(id);

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("userId"), is(1));
        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("title"), notNullValue());
        assertThat(jsonObjectData.getBoolean("completed"), is(false));
    }


    //Get Comments
    public Response getAllComment() {
        Response response = given()
                .request(Method.GET, "/comments");
        return response;
    }

    //Get Comment By Id
    public Response getCommentById(int id){
        Response response=given()
                .request(Method.GET,"/comments/"+id);
        return response;
    }

    //Get All Comments By postId
    public Response getAllCommentByPostId(int id) {
        Response response = given()
                .param("postId", id)
                .when()
                .request(Method.GET, "/comments");
        return response;
    }

    //Get Albums By CommentId
    public Response getAlbumByCommentId(int id){
        Response response=given()
                .request(Method.GET,"/comments/"+id+"/albums");
        return response;
    }
    //Get todos By CommentId
    public Response getTodosByCommentId(int id) {
        Response response = given()
                .request(Method.GET, "/comments/" + id + "/todos");
        return response;
    }
}