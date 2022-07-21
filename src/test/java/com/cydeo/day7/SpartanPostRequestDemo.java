package com.cydeo.day7;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {
    @Test
    public void test1() {
        String requestJsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Severus\",\n" +
                "  \"phone\": 1234567894\n" +
                "}";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON).body(requestJsonBody)
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.phone"), is(1234567894));
        System.out.println("Phone: " + response.path("data.phone"));
    }

    @Test
    public void test2() {
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("name", "Severus");
        requestJsonMap.put("phone", 1234567895);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON).body(requestJsonMap).log().all()
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.phone"), is(1234567895));
        response.prettyPrint();
    }
}