package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
//import io.restassured.RestAssured;

import  io.restassured.RestAssured.*;
import  org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Apitest {

	public static void main(String[] args) {
        // Base URI
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi/currentprice.json";

        // Step 1: Send GET request
        Response response = given()
                .when()
                .get()
                .then()
                .statusCode(200) // Verify HTTP status code is 200
                .extract()
                .response();

        // Step 2: Verify response contains 3 BPIs (USD, GBP, EUR)
        response.then().body("bpi.USD", notNullValue());
        response.then().body("bpi.GBP", notNullValue());
        response.then().body("bpi.EUR", notNullValue());

        // Step 3: Verify GBP description equals 'British Pound Sterling'
        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
        if ("British Pound Sterling".equals(gbpDescription)) {
            System.out.println("Test passed: GBP description is correct.");
        } else {
            System.out.println("Test failed: GBP description is incorrect. Found: " + gbpDescription);
        }
    }
}

