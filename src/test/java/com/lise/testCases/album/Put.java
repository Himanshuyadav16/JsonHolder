package com.lise.testCases.album;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.albums.AlbumPostBody;
import com.lise.models.albums.AlbumPostResponse;
import com.lise.models.albums.AlbumPutBody;
import com.lise.models.albums.AlbumPutResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Put extends BaseClass {
    @Test
    public void updateAlbum() {
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

        AlbumPutBody albumPutBody=new AlbumPutBody();
        albumPutBody.setUserId(userId);
        albumPutBody.setTitle("enim quidem molestiae");

        AlbumPutResponse albumPutResponse = updateAlbum(albumPutBody, albumId);

        assertThat(albumPutResponse.getTitle(), is(albumPutBody.title));
        assertThat(albumPutResponse.getUserId(), is(userId));
        assertThat(albumPutResponse.getId(), notNullValue());

        Response albumDeleteResponse=deleteAlbumById(albumId);

        Response userDeleteResponse=deleteAlbumById(userId);
    }

    // update Album
    public AlbumPutResponse updateAlbum(AlbumPutBody body, int id) {
        AlbumPutResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/albums/" + id)
                .as(AlbumPutResponse.class);
        return response;
    }
}