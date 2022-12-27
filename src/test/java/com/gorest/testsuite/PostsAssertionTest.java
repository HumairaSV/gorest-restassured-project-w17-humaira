package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class PostsAssertionTest {


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

    //1. Verify the if the total record is 25
    @Test
    public void postAssert1(){
        response.body("data.size()", equalTo(25));
    }

    //2. Verify the if the title of id = 2670 is equal to ”Taedium fuga victus sustineo tot benigne casso solus trucido tutis cursus vox capio damnatio cultellus defleo consequatur impedit.”
    @Test
    public void postAssert2(){
        response.body("find{it.id == 2670}.title", equalTo("Taedium fuga victus sustineo tot benigne casso solus trucido tutis cursus vox capio damnatio cultellus defleo consequatur impedit."));
    }

    //3. Check the single user_id in the Array list (5410)
    @Test
    public void postAssert3(){
        response.body("user_id", hasItem(5410));

    }

    //4. Check the multiple ids in the ArrayList (2662, 2643, 2617)
    @Test
    public void postAssert4(){
        response.body("id", hasItems(2662, 2643, 2617));
    }

    /*5. Verify the body of userid = 5437 is equal “Praesentium patrocinor sophismata.
    Deprecator aeneus acervus. Supellex qui aperiam. Succedo suffoco canis.
    Approbo consequatur debeo. Victus vir nobis. Varietas super amo.
    Terreo baiulus desino. Adipisci caterva concedo. Torqueo abutor dens. Claudeo dicta tantillus.
     Cohors campana delectatio. Iure sortitus abutor. Accedo deprimo cenaculum. Summisse supra curtus. Necessitatibus delinquo cunabula. Patrocinor tepesco amplitudo. Vulticulus solutio solium. Succedo vorax baiulus.”*/
    @Test
    public void postAssert5(){
        response.body("find{it.user_id == 5437}.body", equalTo("Praesentium patrocinor sophismata. Deprecator aeneus acervus. Supellex qui aperiam. Succedo suffoco canis. Approbo consequatur debeo. Victus vir nobis. Varietas super amo. Terreo baiulus desino. Adipisci caterva concedo. Torqueo abutor dens. Claudeo dicta tantillus. Cohors campana delectatio. Iure sortitus abutor. Accedo deprimo cenaculum. Summisse supra curtus. Necessitatibus delinquo cunabula. Patrocinor tepesco amplitudo. Vulticulus solutio solium. Succedo vorax baiulus."));
    }

}
