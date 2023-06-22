package com.lise.testCases.posts;

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
    public void patchPostById() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userBody = "{\n" +
                "    \"name\": \"" + userName + "\",\n" +
                "    \"email\": \"" + userEmail + "\"\n" +
                "  }";
        Response userResponse = createUser(userBody);

        assertThat(userResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject postData = new JSONObject(userResponse.asString());

        assertThat(postData.getString("name"), is(userName));
        assertThat(postData.getString("email"), is(userEmail));

        int userId = postData.getInt("id");
        String postTitle = "foo";
        String postPostsBody = "bar";

        String postBody = "{ \"userId\": \"" + userId + "\",\n" +
                "    \"title\": \"" + postTitle + "\",\n" +
                "    \"body\": \"" + postPostsBody + "\"\n" +
                "  }";

        Response postResponse = createPost(postBody);

        assertThat(postResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject jsonPost = new JSONObject(postResponse.asString());
        assertThat(jsonPost.getInt("userId"), is(userId));
        assertThat(jsonPost.getInt("id"), notNullValue());
        assertThat(jsonPost.getString("title"), is(postTitle));
        assertThat(jsonPost.getString("body"), is(postPostsBody));
        int postId = postData.getInt("id");
        String pacthTitle = "foo";
        String pacthPostsBody = "bar";

        String patchBody = "{ \"userId\": \"" + userId + "\",\n" +
                "    \"title\": \"" + pacthTitle + "\",\n" +
                "    \"body\": \"" + pacthPostsBody + "\"\n" +
                "  }";

        Response patchResponse = patchPostById(patchBody, postId);
        assertThat(patchResponse.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonPatch = new JSONObject(patchResponse.asString());
        assertThat(jsonPatch.getInt("userId"), is(userId));
        assertThat(jsonPatch.getInt("id"), is(postId));
        assertThat(jsonPatch.getString("title"), is(pacthTitle));
        assertThat(jsonPatch.getString("body"), is(pacthPostsBody));
    }

    // patch A Posts
    public Response patchPostById(String body, int id) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PATCH, "/posts/" + id);
        return response;
    }
}