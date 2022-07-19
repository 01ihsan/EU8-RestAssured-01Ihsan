package com.cydeo.day5;

import com.cydeo.Utilities.DBUtils;
import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanAPIvsDB extends SpartanTestBase {
    @Test
    public void test1() {
        String query = "select spartan_id,name,gender,phone from spartans where spartan_id = 15";
        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println(dbMap);

        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and().contentType("application/json")
                .extract().response();

        Map<String,Object> apiMap = response.as(Map.class);
        System.out.println(apiMap);
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
    }
}