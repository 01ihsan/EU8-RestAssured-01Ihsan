package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {
    String url="http://54.204.212.30:8000/api/spartans";

    @Test
    public void test1(){
        Response response = RestAssured.get(url);
        //response status code
        System.out.println(response.statusCode());
        //response body
        System.out.println(response.prettyPrint());
    }
}
