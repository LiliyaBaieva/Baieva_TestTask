package com.globallogic.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ResponseBookingDto {

    private Integer bookingid;
    private CreateBookingDto createdBooking;

}
