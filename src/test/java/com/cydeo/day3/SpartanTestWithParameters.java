package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithParameters {
    String baseUrl = "http://54.204.212.30:8000";

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParams("id",5).
                when().get(baseUrl+"/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Blythe"));
    }
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParams("id",500)
                .when().get(baseUrl+"/api/spartans/{id}");
        assertEquals(response.contentType(),"application/json");
        assertEquals(response.statusCode(),404);

    }
    @Test
    public void test3(){
        Response response = RestAssured.given().log().all().accept(ContentType.JSON).
                and().queryParam("nameContains","ea").and().queryParam("gender","Female")
                .when().get(baseUrl+"/api/spartans/search");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
    }
    @Test
    public void test4(){
        Map<String,Object> queryMap= new HashMap<>();
        queryMap.put("nameContains","ea");
        queryMap.put("gender","female");

        Response response = RestAssured.given().log().all().accept(ContentType.JSON).
                and().queryParams(queryMap).
                when().get(baseUrl+"/api/spartans/search");
        response.prettyPrint();
        assertEquals(response.contentType(),"application/json");
        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("Jeanelle"));
    }
}