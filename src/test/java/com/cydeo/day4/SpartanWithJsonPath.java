package com.cydeo.day4;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJsonPath extends SpartanTestBase {
    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParams("id", 5).
                when().get("/api/spartans/{id}");
        JsonPath jsonPath=response.jsonPath(); //jsonPath use
        System.out.println(jsonPath.getString("name"));     //with JsonPath
        System.out.println(response.path("name").toString()); //without JsonPath
    }
}
