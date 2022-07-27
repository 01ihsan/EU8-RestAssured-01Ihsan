package com.cydeo.Utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanAuthTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "http://54.204.212.30:7000";
        String dbUrl = "jdbc:oracle:thin:@54.204.212.30:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }
    @AfterAll
    public static void teardown(){
        DBUtils.destroy();
    }
}
