package com.cydeo.day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ORDSAPIWithJsonPath extends HRTestBase {
    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON).
                queryParam("limit", 107).
                when().get("/employees");
        JsonPath jsonPath = response.jsonPath();
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);
        List<String> employeeNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(employeeNames);
        String maxIncomeName = jsonPath.getString("items.max {it.salary}.first_name");
        String maxIncomeName2 = response.path("items.max {it.salary}.first_name");
        System.out.println(maxIncomeName +" -- "+maxIncomeName2);
    }
}
