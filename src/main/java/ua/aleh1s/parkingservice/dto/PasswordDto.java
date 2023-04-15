package ua.aleh1s.parkingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordDto {
    @Size(min = 4, max = 20, message = "Password length should be between 4 and 20 symbols")
    @NotBlank(message = "Password should not be blank")
    private String password;
}
