package com.cydeo.day8;

import com.cydeo.Utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class SpartanWithAuthTest extends SpartanAuthTestBase {
    @Test
    public void userTest(){
        RestAssured.get("/api/spartans").then().log().all();
    }
    @Test
    public void adminTest(){
        RestAssured.given().auth().basic("admin","admin")
                .get("/api/spartans").then().log().all();
    }
}
