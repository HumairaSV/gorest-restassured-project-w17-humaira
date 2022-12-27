package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }


    //1. Extract the All Ids
    @Test
    public void extract1() {
        List<Integer> idsAll = response.extract().path("id");
        System.out.println("_____________________________________________");
        System.out.println("Extract of all Ids : " + idsAll);
        System.out.println("_____________________________________________");
    }

    //2. Extract the all Names
    @Test
    public void extract2() {
        List<Integer> namesAll = response.extract().path("name");
        System.out.println("_____________________________________________");
        System.out.println("Extract of all names : " + namesAll);
        System.out.println("_____________________________________________");
    }

    //3. Extract the name of 5th object
    @Test
    public void extract3() {
        String name = response.extract().path("[4].name");
        System.out.println("_____________________________________________");
        System.out.println("Name of 5th Object : " + name);
        System.out.println("_____________________________________________");
    }


    //4. Extract the names of all object whose status = inactive
    @Test
    public void extract4() {
        List<String> status = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("_____________________________________________");
        System.out.println("Names of all objects whose status is inactive : " + status);
        System.out.println("_____________________________________________");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void extract5() {
        List<String> status = response.extract().path("findAll{it.status == 'active'}.name");
        System.out.println("_____________________________________________");
        System.out.println("Names of all objects whose status is active : " + status);
        System.out.println("_____________________________________________");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void extract6() {
        List<String> gender = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("_____________________________________________");
        System.out.println("Names of all objects whose gender is female : " + gender);
        System.out.println("_____________________________________________");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void extract7() {
        List<String> emails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("_____________________________________________");
        System.out.println("Names of all objects whose email is inactive : " + emails);
        System.out.println("_____________________________________________");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void extract8() {
        List<String> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("_____________________________________________");
        System.out.println("Ids of all objects whose gender is male : " + ids);
        System.out.println("_____________________________________________");
    }

    //9. Get all the status
    @Test
    public void extract9() {
        List<String> status = response.extract().path("status");
        System.out.println("_____________________________________________");
        System.out.println("List of all the statuses : " + status);
        System.out.println("_____________________________________________");
    }

    //10. Get email of the object where name = Gajadhar Bhat
    @Test
    public void extract10() {
        String email = response.extract().path("find{it.name == 'Gajadhar Bhat'}.email");
        System.out.println("_____________________________________________");
        System.out.println("email of the object where name is Gajadhar Bhat : " + email);
        System.out.println("_____________________________________________");
    }

    //11. Get gender of id = 5265
    @Test
    public void extract11() {
        String email = response.extract().path("find{it.id == 5265}.email");
        System.out.println("_____________________________________________");
        System.out.println("email of the object where name is Gajadhar Bhat : " + email);
        System.out.println("_____________________________________________");
    }

}
