package com.lise.testCases.photos;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.albums.AlbumPostBody;
import com.lise.models.albums.AlbumPostResponse;
import com.lise.models.photos.PhotoPostBody;
import com.lise.models.photos.PhotoPostResponse;
import com.lise.models.photos.PhotoPutBody;
import com.lise.models.photos.PhotoPutResponse;
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
    public void updatePhoto() {
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

        int photoId = 1;
        PhotoPutBody photoPutBody=new PhotoPutBody();

        photoPutBody.setAlbumId(albumId);
        photoPutBody.setTitle("reprehenderit est deserunt velit ipsam");
        photoPutBody.setUrl("https://via.placeholder.com/600/771796");
        photoPutBody.setThumbnailUrl("https://via.placeholder.com/150/92c952");

        PhotoPutResponse photoPutResponse = updatePhoto(photoPutBody, photoId);

        assertThat(photoPutResponse.getAlbumId(), is(albumId));
        assertThat(photoPutResponse.getId(), notNullValue());
        assertThat(photoPutResponse.getTitle(), is(photoPutBody.title));
        assertThat(photoPutResponse.getUrl(), is(photoPutBody.url));
        assertThat(photoPutResponse.getThumbnailUrl(), is(photoPutBody.thumbnailUrl));

        Response deletePhotoResponse=deletePhotoById(photoId);

        Response deleteAlbumResponse=deletePhotoById(albumId);

        Response deleteUserResponse=deletePhotoById(userId);
    }

    //Update Photos
    public PhotoPutResponse updatePhoto(PhotoPutBody body, int id) {
        PhotoPutResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/photos/" + id)
                .then()
                .extract()
                .as(PhotoPutResponse.class);
        return response;
    }
}