package com.gorest.testbase;

import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;

public class TestBase extends TestUtils {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {


        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.port = 3030;

    }
}


