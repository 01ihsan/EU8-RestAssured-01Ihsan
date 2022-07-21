package com.cydeo.day6;

import com.cydeo.Utilities.HRTestBase;
import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {
    @Test
    public void test1() {
        JsonPath jsonPath = RestAssured.get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();
        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println(region1);
        System.out.println(region1.getLinks().get(0).getHref());
        Link link1 = jsonPath.getObject("items[0].links[0]", Link.class);
        System.out.println(link1.getHref());
        String href1 = jsonPath.getObject("items[0].links[0].href", String.class);
        System.out.println(href1);
        System.out.println(region1.getRegionID());
    }

    @Test
    public void test2() {
        JsonPath jsonPath = RestAssured.get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();
        Employee employee = jsonPath.getObject("items[0]", Employee.class);
        System.out.println(employee);
    }

    @Test
    public void test3() {
        Regions regions = RestAssured.get("/regions")
                .then().statusCode(200)
                .extract().as(Regions.class);
        System.out.println("regions.getCount() = " + regions.getCount());
        Assertions.assertEquals(regions.getCount(), 4);
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();
        List<Region> regionsItems = regions.getItems();
        for (Region region : regionsItems) {
            regionIds.add(region.getRegionID());
            regionNames.add(region.getRegion_name());
        }
        List<Integer> expectedRegionIds = Arrays.asList(1, 2, 3, 4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(expectedRegionNames));
    }
}