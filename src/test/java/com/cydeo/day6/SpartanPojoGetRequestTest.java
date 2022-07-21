package com.cydeo.day6;

import com.cydeo.Utilities.SpartanTestBase;
import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SpartanPojoGetRequestTest extends SpartanTestBase {
    @Test
    public void oneSpartanPojo() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();

        Spartan spartan15 = response.as(Spartan.class); //with the jackson we convert response to spartan and this is so good!
        System.out.println(spartan15);
    }

    @Test
    public void spartanSearchWithPojo() {
        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();
        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("s1 = " + s1);
    }

    @Test
    public void test3() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();
        Search searchResult = response.as(Search.class);
        System.out.println(searchResult.getContent().get(0).getName());
    }

    @Test
    public void test4() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Spartan> spartanList = jsonPath.getList("content", Spartan.class);
        System.out.println(spartanList.get(1).getName());
    }
}