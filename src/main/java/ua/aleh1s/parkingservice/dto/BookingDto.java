package ua.aleh1s.parkingservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter
public class BookingDto {

    private Integer id;
    private String checkIn;
    private String checkOut;
    private Integer floor;
    private Integer place;
    private boolean isExpired;

}
