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

public class Patch extends BaseClass {
    @Test
    public void patchUserById(){
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
        String userPatchName = faker.name().name();
        String userPatchEmail = faker.internet().emailAddress();

        String userPatchBody = " {\n" +
                "            \"name\": \"" + userPatchName + "\",\n" +
                "        \"email\": \"" + userPatchEmail + "\"\n" +
                "    }";

        Response userPatchResponse = patchUserById(userPatchBody, userId);

        assertThat(userPatchResponse.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonObjectPutUser = new JSONObject(userPatchResponse.asString());

        assertThat(jsonObjectPutUser.getInt("id"), is(1));
        assertThat(jsonObjectPutUser.getString("name"), is(userPatchName));
        assertThat(jsonObjectPutUser.getString("username"), is("Bret"));
        assertThat(jsonObjectPutUser.getString("email"), is(userPatchEmail));
        assertThat(jsonObjectPutUser.getString("phone"), is("1-770-736-8031 x56442"));
        assertThat(jsonObjectPutUser.getString("website"), is("hildegard.org"));

        JSONObject jsonObjectAddress = jsonObjectPutUser.getJSONObject("address");

        assertThat(jsonObjectAddress.getString("street"), is("Kulas Light"));
        assertThat(jsonObjectAddress.getString("suite"), is("Apt. 556"));
        assertThat(jsonObjectAddress.getString("city"), is("Gwenborough"));
        assertThat(jsonObjectAddress.getString("zipcode"), is("92998-3874"));

        JSONObject jsonObjectGeo = jsonObjectAddress.getJSONObject("geo");
        assertThat(jsonObjectGeo.getString("lat"), is("-37.3159"));
        assertThat(jsonObjectGeo.getString("lng"), is("81.1496"));

        JSONObject jsonObjectCompany = jsonObjectPutUser.getJSONObject("company");
        assertThat(jsonObjectCompany.getString("name"), is("Romaguera-Crona"));
        assertThat(jsonObjectCompany.getString("catchPhrase"), is("Multi-layered client-server neural-net"));
        assertThat(jsonObjectCompany.getString("bs"), is("harness real-time e-markets"));
    }
//Patch User By Id
public Response patchUserById(String body, int id) {
    Response response = given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .request(Method.PATCH, "/users/" + id);
    return response;
}
}