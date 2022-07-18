package com.cydeo.day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ORDSAPITestWithPath extends HRTestBase {

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON).
                queryParam("q", "{\"region_id\":2}")
                .when().get("/countries/");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json");
        System.out.println(response.path("items[0].country_id").toString());
        System.out.println(response.path("items[2].links[0].href").toString());
        List<String> countryNames = response.path("items.country_name");
        System.out.println(countryNames);
        List<Integer> regionIDs = response.path(("items.region_id"));
        System.out.println(regionIDs);
        for (int i : regionIDs) {
            assertEquals(2, i);
        }
    }
}
