package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; //with static import we don't need to write Assertions.

public class SpartanNegativeGetTest {
    String baseUrl = "http://54.204.212.30:8000";
    @Test
    public void test(){
        Response response = RestAssured.given().accept(ContentType.XML).
                when().get(baseUrl+"/api/spartans/10");
        assertEquals(response.statusCode(),406);
        assertEquals(response.contentType(),"application/xml;charset=UTF-8");
    }
}
