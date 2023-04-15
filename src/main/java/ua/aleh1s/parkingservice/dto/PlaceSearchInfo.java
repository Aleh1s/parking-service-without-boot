package ua.aleh1s.parkingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class PlaceSearchInfo {
    @NotNull(message = "Floor cannot be null")
    private Integer floor;
    @NotNull(message = "Check-in cannot be null")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime checkIn;
    @NotNull(message = "Check-out cannot be null")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime checkOut;
}
