package com.lise.testCases.todos;

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
    public void getAllTodos(){
        Response response=getAllTodo();

        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONArray jsonArray=new JSONArray(response.asString());

        JSONObject jsonObject=jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("title"),is("delectus aut autem"));
        assertThat(jsonObject.getBoolean("completed"),is(false));
    }
    @Test
    public void getTodoById(){
        int id=1;
        Response response=getTodoById(id);

        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONObject jsonObject=new JSONObject(response.asString());

        assertThat(jsonObject.getInt("userId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("title"),is("delectus aut autem"));
        assertThat(jsonObject.getBoolean("completed"),is(false));
    }
    @Test
    public void getPostByTodoId(){
        int id=1;
        Response response=getPostByTodosId(id);
        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONArray jsonArray=new JSONArray(response.asString());

        JSONObject jsonObject=jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("title"),is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        assertThat(jsonObject.getString("body"),notNullValue());
    }

    @Test
    public void getCommentsByTodoId(){
        int id=1;
        Response response=getCommentsByTodosId(id);

        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONArray jsonArray=new JSONArray(response.asString());

        JSONObject jsonObject=jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("postId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("name"),is("id labore ex et quam laborum"));
        assertThat(jsonObject.getString("email"),is("Eliseo@gardner.biz"));
        assertThat(jsonObject.getString("body"),notNullValue());
    }

    @Test
    public void getAlbumByTodoId(){
        int id=1;
        Response response=getAlbumByTodosId(id);

        assertThat(response.getStatusCode(),is(HttpStatus.SC_OK));

        JSONArray jsonArray=new JSONArray(response.asString());

        JSONObject jsonObject=jsonArray.getJSONObject(0);

        assertThat(jsonObject.getInt("userId"),is(1));
        assertThat(jsonObject.getInt("id"),is(1));
        assertThat(jsonObject.getString("title"),is("quidem molestiae enim"));
    }

 //Get All todos
    public Response getAllTodo(){
        Response response =given()
                .request(Method.GET,"/todos");
        return response;
    }
//Get todos By Id
    public Response getTodoById(int id){
        Response response=given()
                .request(Method.GET,"/todos/"+id);
        return response;
    }
 //Get posts By todos Id
    public Response getPostByTodosId(int id){
        Response response=given()
                .request(Method.GET,"/todos/"+id+"/posts");
        return response;
    }

    //Get comments By todos Id
    public Response getCommentsByTodosId(int id){
        Response response=given()
                .request(Method.GET,"/todos/"+id+"/comments");
        return response;
    }
    //Get Albums By todos Id
    public Response getAlbumByTodosId(int id){
        Response response=given()
                .request(Method.GET,"/todos/"+id+"/albums");
        return response;
    }
}