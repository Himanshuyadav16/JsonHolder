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

public class Patch extends BaseClass {

    @Test
    public void patchAlbum() {
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
        String albumPatchTitle = "quidem moleste patched";
        String albumPatchBody = " {\n" +
                "        \"userId\": " + userId + ",\n" +
                "        \"title\": \"" + albumPatchTitle + "\"\n" +
                "    }";

        Response albumPatchResponse = patchAlbum(albumPatchBody, albumId);

        assertThat(albumPatchResponse.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonObjectPatchAlbum = new JSONObject(albumPatchResponse.asString());

        assertThat(jsonObjectPatchAlbum.getString("title"), is(albumPatchTitle));
        assertThat(jsonObjectPatchAlbum.getInt("userId"), is(userId));
        assertThat(jsonObjectPatchAlbum.getInt("id"), notNullValue());
    }
    // Patch Album
    public Response patchAlbum(String body, int id) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PATCH, "/albums/" + id);
        return response;
    }
}