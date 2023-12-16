package com.globallogic.TestsRA;

import com.globallogic.dto.CreateBookingDto;
import com.globallogic.dto.ResponseBookingDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.given;

public class CreateBookingRATests extends TestBaseRA{

    private String token;
    Integer bookingId;
    Response responseCreateBook;
    Response responseUpdatedBook;

    @BeforeMethod
    public void precondition(){
        token = appRA.getUser().loginWithUser("admin", "password123");
    }

//    @AfterClass
//    public void postCondition(){
//    }

    @Test(priority = 1)
    public void createBookingRAPositiveTests(){

        responseCreateBook = appRA.getBooking().createNewBooking("John", "Smith", 500.00,
                true,"2024-01-01", "2025-01-01", "Park place", token);

        bookingId = Integer.parseInt(responseCreateBook.then().extract().response().jsonPath().getString("bookingid"));

        String firstname = responseCreateBook.then().extract().response().jsonPath().getString("booking.firstname");
        String lastname = responseCreateBook.then().extract().response().jsonPath().getString("booking.lastname");
        Double totalprice = Double.parseDouble(responseCreateBook.then().extract().response().jsonPath()
                .getString("booking.totalprice"));
        Boolean depositpaid = Boolean.parseBoolean(responseCreateBook.then().extract().response().jsonPath()
                .getString("booking.depositpaid"));
        String checkin = responseCreateBook.then().extract().response().jsonPath().getString(
                "booking.bookingdates.checkin");
        String checkout = responseCreateBook.then().extract().response().jsonPath().getString(
                "booking.bookingdates.checkout");
        String additionalneeds = responseCreateBook.then().extract().response().jsonPath().getString("booking.additionalneeds");


        responseCreateBook.then().assertThat().statusCode(200);
        Assert.assertEquals(firstname, "John");
        Assert.assertEquals(lastname, "Smith");
        Assert.assertEquals(totalprice, 500);
        Assert.assertEquals(depositpaid, true);
        Assert.assertEquals(checkin, "2024-01-01");
        Assert.assertEquals(checkout, "2025-01-01");
        Assert.assertEquals(additionalneeds, "Park place");

    }

    @Test(priority = 2)
    public void updateBookTest(){
        ResponseBookingDto responseBookingDto = responseCreateBook.then().extract().response()
                .as(ResponseBookingDto.class);

        CreateBookingDto booking = responseBookingDto.getCreatedBooking();
        booking.setTotalprice(1000.00);
        responseUpdatedBook = appRA.getBooking().updateBooking(booking, token, bookingId);

        Double totalprice = Double.parseDouble(responseUpdatedBook.then().extract().response().jsonPath()
                .getString("totalprice"));
        Assert.assertEquals(totalprice, 1000.00);
    }

    @Test(priority = 3)
    public void isMyBookingExist(){
        Response response = given().header("Authorization", token).when().get("/booking");
        response.then().assertThat().statusCode(200);

        Assert.assertTrue(appRA.getBooking().isBookingContainsId(bookingId, response));

//
        appRA.getBooking().deleteBooking(bookingId, token);
    }


}
















