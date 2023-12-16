package com.globallogic.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookingDatesDto {

    // Shoul be refactoring from String to Date.
    private String checkin;
    private String checkout;

}
