package com.cydeo.day6;

import com.cydeo.Utilities.HRTestBase;
import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

public class ORDSPojoGetRequestTest extends HRTestBase {
    @Test
    public void test1(){
        JsonPath jsonPath= RestAssured.get("/regions")
                .then().statusCode(200).extract().jsonPath();
        Region region1=jsonPath.getObject("items[0]", Region.class);
        System.out.println(region1);
        System.out.println(region1.getLinks().get(0).getHref());
        Link link1 = jsonPath.getObject("items[0].links[0]", Link.class);
        System.out.println(link1.getHref());
        String href1= jsonPath.getObject("items[0].links[0].href",String.class);
        System.out.println(href1);
    }
}