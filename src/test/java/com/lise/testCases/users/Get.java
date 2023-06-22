package com.lise.testCases.users;

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
    public void getAllUsers() {
        Response response = getAllUser();

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("name"), is("Leanne Graham"));
        assertThat(jsonObjectData.getString("username"), is("Bret"));
        assertThat(jsonObjectData.getString("email"), is("Sincere@april.biz"));
        assertThat(jsonObjectData.getString("phone"), is("1-770-736-8031 x56442"));
        assertThat(jsonObjectData.getString("website"), is("hildegard.org"));

        JSONObject jsonObjectAddress = jsonObjectData.getJSONObject("address");

        assertThat(jsonObjectAddress.getString("street"), is("Kulas Light"));
        assertThat(jsonObjectAddress.getString("suite"), is("Apt. 556"));
        assertThat(jsonObjectAddress.getString("city"), is("Gwenborough"));
        assertThat(jsonObjectAddress.getString("zipcode"), is("92998-3874"));

        JSONObject jsonObjectGeo = jsonObjectAddress.getJSONObject("geo");
        assertThat(jsonObjectGeo.getString("lat"), is("-37.3159"));
        assertThat(jsonObjectGeo.getString("lng"), is("81.1496"));

        JSONObject jsonObjectCompany = jsonObjectData.getJSONObject("company");
        assertThat(jsonObjectCompany.getString("name"), is("Romaguera-Crona"));
        assertThat(jsonObjectCompany.getString("catchPhrase"), is("Multi-layered client-server neural-net"));
        assertThat(jsonObjectCompany.getString("bs"), is("harness real-time e-markets"));
    }

    @Test
    public void getUserById() {
        int id = 1;
        Response response = getUserById(id);

        JSONObject jsonObjectData = new JSONObject(response.asString());

        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("name"), is("Leanne Graham"));
        assertThat(jsonObjectData.getString("username"), is("Bret"));
        assertThat(jsonObjectData.getString("email"), is("Sincere@april.biz"));
        assertThat(jsonObjectData.getString("phone"), is("1-770-736-8031 x56442"));
        assertThat(jsonObjectData.getString("website"), is("hildegard.org"));

        JSONObject jsonObjectAddress = jsonObjectData.getJSONObject("address");

        assertThat(jsonObjectAddress.getString("street"), is("Kulas Light"));
        assertThat(jsonObjectAddress.getString("suite"), is("Apt. 556"));
        assertThat(jsonObjectAddress.getString("city"), is("Gwenborough"));
        assertThat(jsonObjectAddress.getString("zipcode"), is("92998-3874"));

        JSONObject jsonObjectGeo = jsonObjectAddress.getJSONObject("geo");
        assertThat(jsonObjectGeo.getString("lat"), is("-37.3159"));
        assertThat(jsonObjectGeo.getString("lng"), is("81.1496"));

        JSONObject jsonObjectCompany = jsonObjectData.getJSONObject("company");
        assertThat(jsonObjectCompany.getString("name"), is("Romaguera-Crona"));
        assertThat(jsonObjectCompany.getString("catchPhrase"), is("Multi-layered client-server neural-net"));
        assertThat(jsonObjectCompany.getString("bs"), is("harness real-time e-markets"));
    }

    @Test
    public void getPostByUserId() {
        int id = 1;
        Response response = getPostByUserId(id);

        JSONArray jsonArray = new JSONArray(response.asString());

        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("userId"), is(1));
        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("title"), notNullValue());
        assertThat(jsonObjectData.getString("body"), notNullValue());
    }

    @Test
    public void getAlbumByUserId() {
        int id = 1;
        Response response = getAlbumByUserId(id);

        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("userId"), is(1));
        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("title"), is("quidem molestiae enim"));
    }

    @Test
    public void getTodosByUserId() {
        int id = 1;
        Response response = getTodosByUserId(id);

        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObjectData = jsonArray.getJSONObject(0);

        assertThat(jsonObjectData.getInt("userId"), is(1));
        assertThat(jsonObjectData.getInt("id"), is(1));
        assertThat(jsonObjectData.getString("title"), is("delectus aut autem"));
        assertThat(jsonObjectData.getBoolean("completed"), is(false));
    }

    //Get All Users
    public Response getAllUser() {
        Response response = given()
                .request(Method.GET, "/users");
        return response;
    }

    //Get User By Id
    public Response getUserById(int id) {
        Response response = given()
                .request(Method.GET, "/users/" + id);
        return response;
    }

    //Get Posts by userId
    public Response getPostByUserId(int id) {
        Response response = given()
                .request(Method.GET, "/users/" + id + "/posts");
        return response;
    }

    //Get Album By UserId
    public Response getAlbumByUserId(int id) {
        Response response = given()
                .request(Method.GET, "/users/" + id + "/albums");
        return response;
    }

    //Get todos By UserId
    public Response getTodosByUserId(int id) {
        Response response = given()
                .request(Method.GET, "/users/" + id + "/todos");
        return response;
    }

}