package ua.aleh1s.parkingservice.dto;


import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {
    private Integer id;
    @Pattern(regexp = "[A-Z]{2}\\d{4}[A-Z]{2}", message = "Invalid car number. Example: AB5432AM")
    private String number;
}
