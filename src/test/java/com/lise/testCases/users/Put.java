package com.lise.testCases.users;

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
    public void updateUserById() {
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

        int userId = 1;
        String userUpdateName = faker.name().name();
        String userUpdateEmail = faker.internet().emailAddress();

        String userUpdateBody = " {\n" +
                "            \"name\": \"" + userUpdateName + "\",\n" +
                "        \"email\": \"" + userUpdateEmail + "\"\n" +
                "    }";

        Response userPutResponse = updateUserById(userUpdateBody, userId);

        assertThat(userPutResponse.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonObjectPutUser = new JSONObject(userPutResponse.asString());

        assertThat(jsonObjectPutUser.getInt("id"), is(1));
        assertThat(jsonObjectPutUser.getString("name"), is(userUpdateName));
        assertThat(jsonObjectPutUser.getString("email"), is(userUpdateEmail));
    }

    //Put User By Id
    public Response updateUserById(String body, int id) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/users/" + id);
        return response;
    }
}