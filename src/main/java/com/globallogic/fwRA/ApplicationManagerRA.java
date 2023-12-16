package com.globallogic.fwRA;

public class ApplicationManagerRA {

    UserHelperRA user;
    BookingHelper booking;
    public static String BASE_URL = "https://restful-booker.herokuapp.com";

    public ApplicationManagerRA(){}

    public void init (){

        user = new UserHelperRA();
        booking = new BookingHelper();

    }

    public UserHelperRA getUser() {
        return user;
    }

    public BookingHelper getBooking() {
        return booking;
    }
}
