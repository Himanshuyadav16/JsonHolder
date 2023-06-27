package com.lise.testCases.photos;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.albums.AlbumPostBody;
import com.lise.models.albums.AlbumPostResponse;
import com.lise.models.photos.PhotoPostBody;
import com.lise.models.photos.PhotoPostResponse;
import com.lise.models.users.UserPostBody;
import com.lise.models.users.UserPostResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Post extends BaseClass {
    @Test
    public void createPhoto() {
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

        int albumId = albumPostResponse.getId();

        PhotoPostBody photoPostBody=new PhotoPostBody();

        photoPostBody.setUrl("https://via.placeholder.com/600/92c952");
        photoPostBody.setAlbumId(albumId);
        photoPostBody.setThumbnailUrl("https://via.placeholder.com/150/92c952");
        photoPostBody.setTitle("accusamus beatae ad facilis cum similique qui sunt");

        PhotoPostResponse photoPostResponse = createPhoto(photoPostBody);

        assertThat(photoPostResponse.getAlbumId(), is(albumId));
        assertThat(photoPostResponse.getId(), notNullValue());
        assertThat(photoPostResponse.getTitle(), is(photoPostBody.title));

        assertThat(photoPostResponse.getUrl(), is(photoPostBody.getUrl()));
        assertThat(photoPostResponse.getThumbnailUrl(), is(photoPostBody.thumbnailUrl));
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
    //create Photos
    public PhotoPostResponse createPhoto(PhotoPostBody body){
        PhotoPostResponse response=given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST,"/photos")
                .then()
                .extract()
                .as(PhotoPostResponse.class);
        return response;
    }
}