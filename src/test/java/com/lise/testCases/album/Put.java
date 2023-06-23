package com.lise.testCases.album;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Put extends BaseClass {
    @Test
    public void updateAlbum() {
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

        int albumId = 1;
        String albumPutTitle = "quidem moleste enim";
        String albumPutBody = " {\n" +
                "        \"userId\": " + userId + ",\n" +
                "        \"title\": \"" + albumPutTitle + "\"\n" +
                "    }";

        Response albumPutResponse = updateAlbum(albumPutBody, albumId);

        assertThat(albumPutResponse.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonObjectPutAlbum = new JSONObject(albumPutResponse.asString());

        assertThat(jsonObjectPutAlbum.getString("title"), is(albumPutTitle));
        assertThat(jsonObjectPutAlbum.getInt("userId"), is(userId));
        assertThat(jsonObjectPutAlbum.getInt("id"), notNullValue());
    }

    // update Album
    public Response updateAlbum(String body, int id) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/albums/" + id);
        return response;
    }

}