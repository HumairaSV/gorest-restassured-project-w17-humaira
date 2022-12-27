package com.gorest.testsuite;

import com.gorest.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class UserAssertionTest {

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

    //1. Verify the if the total record is 20
    @Test
    public void test001(){
        response.body("data.size()", equalTo(20));
    }

    //2. Verify the if the name of id = 5286 is equal to ”Dr. Dhanalakshmi Khatri”
    @Test
    public void test002(){
        response.body("find{it.id == 5286}.name", equalTo("Dr. Dhanalakshmi Khatri"));
    }

    //3. Check the single ‘Name’ in the Array list (Chapala Gupta VM)
    @Test
    public void test003(){
        response.body("name", hasItem("Chapala Gupta VM"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mahendra Reddy, Dandapaani Verma,Atreyi Prajapat DC)
    @Test
    public void test004(){
        response.body("name", hasItems("Mahendra Reddy","Dandapaani Verma", "Atreyi Prajapat DC"));
    }

    //5. Verify the email of userid = 5257 is equal “akshaj_kapoor@kutch.org”
    @Test
    public void test005(){
        response.body("find{it.id ==5257}.email", equalTo("akshaj_kapoor@kutch.org"));
    }

    //6. Verify the status is “Active” of username is “Dinkar Chattopadhyay Esq.”
    @Test
    public void test006(){
        response.body("find{it.name == 'Dinkar Chattopadhyay Esq.'}.status", equalTo ("active"));
    }

    //7. Verify the Gender = male of username is “Deeptimoyee Bandopadhyay”
    @Test
    public void test007(){
        response.body("find{it.name == 'Deeptimoyee Bandopadhyay'}.gender", equalTo("male"));
    }

}
