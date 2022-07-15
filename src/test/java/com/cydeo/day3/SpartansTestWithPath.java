package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpartansTestWithPath {
    String baseUrl = "http://54.204.212.30:8000";

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON).log().all().
                and().pathParam("id", 10).
                when().get(baseUrl + "/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        System.out.println(response.path("id").toString());
        assertEquals((Integer) response.path("id"), 10);
        assertEquals(response.path("name"), "Lorenza");
        assertEquals((Long) response.path("phone"), 3312820936L);
    }

    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).log().all().
                when().get(baseUrl + "/api/spartans");
        String lastFirstName = response.path("name[-1]");
        System.out.println(lastFirstName);
        System.out.println((Integer) response.path("id[-1]"));
        List<String> names = response.path("name");
        for (String n : names)
            System.out.println(n);
    }
}