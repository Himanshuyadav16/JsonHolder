package com.lise.testCases.album;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Delete extends BaseClass {
    @Test
    public void deleteAlbumById(){
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userBody = " {\n" +
                "            \"name\": \"" + userName + "\",\n" +
                "        \"email\": \"" + userEmail + "\"\n" +
                "    }";
        Response userResponse = createUser(userBody);

        assertThat(userResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject jsonObjectUser = new JSONObject(userResponse.asString());

        assertThat(jsonObjectUser.getString("name"), is(userName));
        assertThat(jsonObjectUser.getString("email"), is(userEmail));
        assertThat(jsonObjectUser.getInt("id"), notNullValue());

        int userId = jsonObjectUser.getInt("id");

        String albumTitle = "quidem molestiae enim";
        String albumBody = " {\n" +
                "        \"userId\": " + userId + ",\n" +
                "        \"title\": \"" + albumTitle + "\"\n" +
                "    }";

        Response albumResponse = createAlbum(albumBody);

        assertThat(albumResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject jsonObjectAlbum = new JSONObject(albumResponse.asString());

        assertThat(jsonObjectAlbum.getString("title"), is(albumTitle));
        assertThat(jsonObjectAlbum.getInt("userId"), is(userId));
        assertThat(jsonObjectAlbum.getInt("id"), notNullValue());

        int albumId=jsonObjectAlbum.getInt("id");

        Response albumDeleteResponse=deleteAlbumById(albumId);
        assertThat(albumDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));

        Response userDeleteResponse=deleteAlbumById(userId);
        assertThat(userDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));

    }
    //delete Album By Id
    public Response deleteAlbumById(int id){
        Response response=given()
                .request(Method.DELETE,"/albums/"+id);
        return response;
    }
}