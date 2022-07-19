package com.cydeo.Utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "http://54.204.212.30:8000";
        String dbUrl = "jdbc:oracle:thin:@54.204.212.30:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
    }
}