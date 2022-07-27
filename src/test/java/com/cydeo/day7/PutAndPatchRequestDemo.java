package com.cydeo.day7;

import com.cydeo.Utilities.SpartanTestBase;
import com.cydeo.pojo.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {
    @Test
    public void putRequest() {
        /*
        Spartan spartan = new Spartan();
        spartan.setGender("Male");
        spartan.setName("Severus");
        spartan.setPhone(1234567896);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON).body(spartan).log().all()
                .when().post("/api/spartans");

        response.prettyPrint();

         */

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Bruce");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1234567897);
        int id = 114;

        RestAssured.given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON).body(putRequestMap)
                .and().pathParam("id", id)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and().contentType("application/json")
                .extract().response();

        response.prettyPrint();

        RestAssured.given().pathParam("id", id)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404).and().contentType("application/json")
                .extract().response();

        response.prettyPrint();
    }
}