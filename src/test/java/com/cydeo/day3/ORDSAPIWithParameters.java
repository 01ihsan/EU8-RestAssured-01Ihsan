package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ORDSAPIWithParameters {
    String baseUrl = "http://54.204.212.30:1000/ords/hr";

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON).log().all().
                and().queryParam("q", "{\"region_id\":2}").
                when().get(baseUrl + "/countries");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.header("Content-Type"), "application/json");
        response.prettyPrint();

    }

    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).log().all().
                and().queryParam("q", "{\"job_id\": \"IT_PROG\"}").
                when().get(baseUrl + "/employees");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.header("Content-Type"), "application/json");
        response.prettyPrint();
    }
}
