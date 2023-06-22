package com.lise.testCases.posts;

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
    public void deletePostById() {
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

        Response  postDeleteResponse=deletePostById(postId);
        assertThat(postDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));
        Response userDeleteResponse=deletePostById(userId);
        assertThat(userDeleteResponse.getStatusCode(),is(HttpStatus.SC_OK));
    }
//Delete Post By Id
    public Response deletePostById(int id){
        Response response=given()
                .request(Method.DELETE,"/posts/"+id);
        return response;
    }
}