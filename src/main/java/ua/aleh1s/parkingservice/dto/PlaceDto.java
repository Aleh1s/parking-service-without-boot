package ua.aleh1s.parkingservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceDto {

    private Integer id;
    private int floor;
    private int place;
    private boolean isTaken;

}
