package com.lise.testCases.photos;

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
    public void patchPhoto() {
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

        int albumId = jsonObjectAlbum.getInt("id");

        String photoTitle = "accusamus beatae ad facilis cum similique qui sunt";
        String photoUrl = "https://via.placeholder.com/600/92c952";
        String photoThumbnailUrl = "https://via.placeholder.com/150/92c952";
        String photoBody = " {\n" +
                "        \"albumId\": " + albumId + ",\n" +
                "        \"title\": \"" + photoTitle + "\",\n" +
                "        \"url\": \"" + photoUrl + "\",\n" +
                "        \"thumbnailUrl\": \"" + photoThumbnailUrl + "\"\n" +
                "    }";
        Response photoResponse = createPhoto(photoBody);

        JSONObject jsonObjectPhoto = new JSONObject(photoResponse.asString());

        assertThat(jsonObjectPhoto.getInt("albumId"), is(albumId));
        assertThat(jsonObjectPhoto.getInt("id"), notNullValue());
        assertThat(jsonObjectPhoto.getString("title"), is(photoTitle));
        assertThat(jsonObjectPhoto.getString("url"), is(photoUrl));
        assertThat(jsonObjectPhoto.getString("thumbnailUrl"), is(photoThumbnailUrl));

        int photoId = 1;

        String photoPatchTitle = "reprehenderit est deserunt velit ipsam";
        String photoPatchUrl = "https://via.placeholder.com/600/771796";
        String photoPatchThumbnailUrl = "https://via.placeholder.com/150/771796";
        String photoPatchBody = " {\n" +
                "        \"albumId\": " + albumId + ",\n" +
                "        \"title\": \"" + photoPatchTitle + "\",\n" +
                "        \"url\": \"" + photoPatchUrl + "\",\n" +
                "        \"thumbnailUrl\": \"" + photoPatchThumbnailUrl + "\"\n" +
                "    }";
        Response photoPatchResponse = patchPhoto(photoPatchBody, photoId);

        JSONObject jsonObjectPatchPhoto = new JSONObject(photoPatchResponse.asString());

        assertThat(jsonObjectPatchPhoto.getInt("albumId"), is(albumId));
        assertThat(jsonObjectPatchPhoto.getInt("id"), notNullValue());
        assertThat(jsonObjectPatchPhoto.getString("title"), is(photoPatchTitle));
        assertThat(jsonObjectPatchPhoto.getString("url"), is(photoPatchUrl));
        assertThat(jsonObjectPatchPhoto.getString("thumbnailUrl"), is(photoPatchThumbnailUrl));
    }

    //Patch Photos
    public Response patchPhoto(String body, int id) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PATCH, "/photos/" + id);
        return response;
    }
}