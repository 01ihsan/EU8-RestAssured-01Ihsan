package com.cydeo.day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {
    @Test
    public void oneSpartanToMap() {
        Response response = RestAssured.given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();
        Map<String, Object> jsonMap = response.as(Map.class);
        System.out.println(jsonMap.toString());
        assertThat(jsonMap.get("name").toString(),equalTo("Meta"));
    }
    @Test
    public void getAllSpartans(){
        Response response = RestAssured.get("/api/spartans")
                .then().statusCode(200).extract().response();
        List<Map<String,Object>> jsonList = response.as(List.class);
        System.out.println(jsonList.get(14).get("name"));
    }
}