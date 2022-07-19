package com.cydeo.day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrestTest extends SpartanTestBase {
    @Test
    public void test1() {
        List<String> names = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .and().body("totalElement", is(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names);
    }
}
