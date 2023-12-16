package com.globallogic.fwRA;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class UserHelperRA extends BaseHelperRA{


    public String loginWithUser(String username, String password) {

        return given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\" : \"" + username + "\",\n" +
                        "    \"password\" : \"" + password + "\"\n" +
                        "}")
                .when().post("/auth").
                then().extract().response().jsonPath().getString("token");
    }


}
