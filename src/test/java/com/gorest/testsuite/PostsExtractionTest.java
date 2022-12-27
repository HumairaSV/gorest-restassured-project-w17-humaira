package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "public/v2";
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }


    //1. Extract the title
    @Test
    public void postExtract1(){
        List<String> title = response.extract().path("title");
        System.out.println("_____________________________________________");
        System.out.println("Extract of all titles : " + title);
        System.out.println("_____________________________________________");
    }

    //2. Extract the total number of record
    @Test
    public void postExtract2(){
        int totalNoOfRecord = response.extract().path("data.size()");
        System.out.println("_____________________________________________");
        System.out.println("Total number of record : " + totalNoOfRecord);
        System.out.println("_____________________________________________");

    }

    //3. Extract the body of 15th record
    @Test
    public void postExtract3(){
        String body = response.extract().path("body[14]");
        System.out.println("_____________________________________________");
        System.out.println("Body of 15th record : " + body);
        System.out.println("_____________________________________________");
    }

    //4. Extract the user_id of all the records
    @Test
    public void postExtract4(){
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("_____________________________________________");
        System.out.println("User id of all the records : " + userId);
        System.out.println("_____________________________________________");
    }

    //5. Extract the title of all the records
    @Test
    public void postExtract5(){
        List<String> title = response.extract().path("title");
        System.out.println("_____________________________________________");
        System.out.println("Title of all records : " + title);
        System.out.println("_____________________________________________");
    }

    //6. Extract the title of all records whose user_id = 5431
    @Test
    public void postExtract6(){
        String title = response.extract().path("find{it.user_id == 5431}.title");
        System.out.println("_____________________________________________");
        System.out.println("Title of all records whose user_id is 5431 : " + title);
        System.out.println("_____________________________________________");
    }

    //7. Extract the body of all records whose id = 2670
    @Test
    public void postExtract7(){
        String body = response.extract().path("find{it.id == 2670}.body");
        System.out.println("_____________________________________________");
        System.out.println("Body of all records whose id is 2670 : " + body);
        System.out.println("_____________________________________________");
    }
}
