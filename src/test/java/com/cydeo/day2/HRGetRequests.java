package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; //with static import we don't need to write Assertions.

public class HRGetRequests {
    String baseUrl = "http://54.204.212.30:1000/ords/hr";

    @DisplayName("Get request to /regions")
    @Test
    public void test() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/regions/2");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        response.prettyPrint();
        assertTrue(response.body().asString().contains("Americas"));
    }

}
