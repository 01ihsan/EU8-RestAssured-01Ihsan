package com.cydeo.day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest extends SpartanTestBase {
    @Test
    public void test1() {
        RestAssured.given().accept(ContentType.JSON).and().pathParam("id", 15).
                when().get("http://54.204.212.30:8000/api/spartans/{id}")
                .then().statusCode(200).and().assertThat()
                .contentType("application/json").and().
                body("id", is(15), "name", equalTo("Meta"), "gender", is("Female")).log().all();
    }

    /*
    @Test
    public void test2(){
        RestAssured.given().accept(ContentType.JSON).and().pathParam("id",10423).
                when().get("http://api.cybertektraining.com/teacher/{id}").
                then().statusCode(200).and().assertThat()
                .contentType("application/json").and()
                .header("Content-Length",is("236"))
                .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is("Alexander"))
                .body("teacher[0].gender",equalTo("male"));
    }
     */
    @Test
    public void test3() {
        RestAssured.given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("http://54.204.212.30:1000/ords/hr/employees")
                .then().statusCode(200).
                body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInAnyOrder("Alexander", "Bruce", "David", "Valli", "Diana"))
                .body("items.first_name", hasItem("Alexander"));
    }

    @Test
    public void test4() {
        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("http://54.204.212.30:1000/ords/hr/employees")
                .then().statusCode(200).
                body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().jsonPath();

        assertThat(jsonPath.getList("items.first_name"), hasSize(5));

        assertThat(jsonPath.getList("items.first_name"),hasItem("Alexander"));
    }
}
