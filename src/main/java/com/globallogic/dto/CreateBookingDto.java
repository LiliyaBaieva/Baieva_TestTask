package com.globallogic.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateBookingDto {

    private String firstname;
    private String lastname;
    private Double totalprice;
    private Boolean depositpaid;
    private BookingDatesDto bookingdates;
    private String additionalneeds;

}
