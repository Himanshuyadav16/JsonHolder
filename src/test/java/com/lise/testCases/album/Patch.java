package com.lise.testCases.album;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.albums.AlbumPatchBody;
import com.lise.models.albums.AlbumPatchResponse;
import com.lise.models.albums.AlbumPostBody;
import com.lise.models.albums.AlbumPostResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Patch extends BaseClass {

    @Test
    public void patchAlbum() {
        Faker faker = new Faker();
        UserPostBody userPostBody =new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());

        UserPostResponse userPostResponse=createUser(userPostBody);

        assertThat(userPostResponse.getName(),is(userPostBody.name));
        assertThat(userPostResponse.getEmail(),is(userPostBody.email));
        assertThat(userPostResponse.getId(),notNullValue());

        int userId = userPostResponse.getId();

        AlbumPostBody albumPostBody=new AlbumPostBody();
        albumPostBody.setTitle("quidem molestiae enim");
        albumPostBody.setUserId(userId);

        AlbumPostResponse albumPostResponse=createAlbum(albumPostBody);
        assertThat(albumPostResponse.getTitle(),is(albumPostBody.title));
        assertThat(albumPostResponse.getUserId(),is(userId));
        assertThat(albumPostResponse.getId(),notNullValue());

        int albumId = 1;

        AlbumPatchBody albumPatchBody=new AlbumPatchBody();
        albumPatchBody.setUserId(userId);
        albumPatchBody.setTitle(albumPatchBody.title);


        AlbumPatchResponse albumPatchResponse = patchAlbum(albumPatchBody, albumId);

        assertThat(albumPatchResponse.getTitle(), is(albumPatchBody.title));
        assertThat(albumPatchResponse.getUserId(), is(userId));
        assertThat(albumPatchResponse.getId(), notNullValue());
    }

    //  Create User
    public UserPostResponse createUser(UserPostBody userPostBody) {
        UserPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(userPostBody)
                .when()
                .request(Method.POST, "/users")
                .as(UserPostResponse.class);
        return response;
    }

    //Create Album
    public AlbumPostResponse createAlbum(AlbumPostBody body) {
        AlbumPostResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts")
                .as(AlbumPostResponse.class);
        return response;
    }

    // Patch Album
    public AlbumPatchResponse patchAlbum(AlbumPatchBody body, int id) {
        AlbumPatchResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PATCH, "/albums/" + id)
                .as(AlbumPatchResponse.class);
        return response;
    }
}