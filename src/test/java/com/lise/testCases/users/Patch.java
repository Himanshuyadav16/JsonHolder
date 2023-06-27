package com.lise.testCases.users;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.models.users.*;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Patch extends BaseClass {
    @Test
    public void patchUserById(){
        Faker faker = new Faker();
        UserPostBody userPostBody =new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());

        UserPostResponse userPostResponse=createUser(userPostBody);

        assertThat(userPostResponse.getName(),is(userPostBody.name));
        assertThat(userPostResponse.getEmail(),is(userPostBody.email));
        assertThat(userPostResponse.getId(),notNullValue());

        int userId = 1;

        UserPatchBody userPatchBody=new UserPatchBody();
        userPatchBody.setName(faker.name().name());
        userPatchBody.setEmail(faker.internet().emailAddress());
        userPatchBody.setPhone("1-770-736-8031 x56442");
        userPatchBody.setWebsite("hildegard.org");
        userPatchBody.setUsername("Bret");

        Address address=new Address();

        address.setCity("Gwenborough");
        address.setStreet("Apt. 556");
        address.setSuite("Kulas Light");
        address.setZipcode("92998-3874");

        Geo geo=new Geo();

        geo.setLat("-37.3159");
        geo.setLng("81.1496");

        Company company=new Company();

        company.setCatchPhrase("Multi-layered client-server neural-net");
        company.setName("Romaguera-Crona");
        company.setBs("harness real-time e-markets");

        userPatchBody.setAddress(address);
        address.setGeo(geo);
        userPatchBody.setCompany(company);

        UserPatchResponse userPatchResponse=patchUserById(userPatchBody,userId);

        assertThat(userPatchResponse.getName(),is(userPatchBody.name));
        assertThat(userPatchResponse.getEmail(),is(userPatchBody.email));
        assertThat(userPatchResponse.getId(),notNullValue());
        assertThat(userPatchResponse.getUsername(),is(userPatchBody.username));
        assertThat(userPatchResponse.getPhone(),is(userPatchBody.phone));
        assertThat(userPatchResponse.getWebsite(),is(userPatchBody.website));

        assertThat(address.getCity(),is(address.city));
        assertThat(address.getSuite(),is(address.suite));
        assertThat(address.getStreet(),is(address.street));
        assertThat(address.getZipcode(),is(address.zipcode));

        assertThat(geo.getLat(),is(geo.lat));
        assertThat(geo.getLng(),is(geo.lng));

        assertThat(company.getName(),is(company.name));
        assertThat(company.getCatchPhrase(),is(company.catchPhrase));
        assertThat(company.getBs(),is(company.bs));

        Response userResponseDelete =deleteUserById(userId);

    }
//Patch User By Id
public UserPatchResponse patchUserById(UserPatchBody body, int id) {
    UserPatchResponse response = given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .request(Method.PATCH, "/users/" + id)
            .as(UserPatchResponse.class);
    return response;
}
}