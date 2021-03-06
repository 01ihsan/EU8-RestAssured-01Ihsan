package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {
    String url = "http://54.204.212.30:8000";

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans");

        System.out.println(response.statusCode());

        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");
    }

    @DisplayName("GET spartans/3 and verify")
    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans/3");

        Assertions.assertEquals(response.contentType(), "application/json");
        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
        response.prettyPrint();
    }

    @Test
    public void test3() {
        Response response = RestAssured.get(url + "/api/hello");
        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.contentType(), "text/plain;charset=UTF-8");
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
    }
}
