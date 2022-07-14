package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {
    String url="http://54.204.212.30:8000";
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url+"/api/spartans");

        System.out.println(response.statusCode());

        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(),200);

        Assertions.assertEquals(response.contentType(),"application/json");
    }
}
