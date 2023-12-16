package com.globallogic.fwRA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globallogic.dto.BookingDatesDto;
import com.globallogic.dto.CreateBookingDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;

public class BookingHelper extends BaseHelperRA{

    public Response createNewBooking(String firstname, String lastname, double totalprice, boolean depositpaid,
                                     String checkin, String checkout, String additionalneeds, String token) {
        BookingDatesDto bookingDates = BookingDatesDto.builder()
                .checkin(checkin)
                .checkout(checkout)
                .build();

        CreateBookingDto booking = CreateBookingDto.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(totalprice)
                .depositpaid(depositpaid)
                .bookingdates(bookingDates)
                .additionalneeds(additionalneeds)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(booking);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON).body(booking)
                .when().post("/booking");
    }

    public void deleteBooking(int bookingId, String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete("/booking/" + bookingId);
    }

    public Response updateBooking(CreateBookingDto booking, String token, int bookingId) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(booking);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON).body(booking)
                .when().put("/booking/" + bookingId);
    }

    public boolean isBookingContainsId(int bookingId, Response response) {
        String body = response.then().extract().body().asPrettyString();
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(body, JsonArray.class);

        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            if (jsonObject.has("bookingid") && jsonObject.get("bookingid").getAsInt() == bookingId) {
                return true;
            }
        }
        return false;
    }
}
