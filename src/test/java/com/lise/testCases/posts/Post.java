package com.lise.testCases.posts;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class Post extends BaseClass {
    @Test
    public void createPost() {
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
    }
}