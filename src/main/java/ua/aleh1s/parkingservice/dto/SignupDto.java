package ua.aleh1s.parkingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupDto {
    @NotBlank(message = "First name should not be blank")
    @Size(min = 2, max = 50, message = "First name length should be between 2 and 50 symbols")
    private String firstName;
    @NotBlank(message = "Last name should not be blank")
    @Size(min = 2, max = 50, message = "Last name length should be between 2 and 50 symbols")
    private String lastName;
    @Pattern(regexp = "0\\d{9}", message = "Phone is invalid. Example: 0xx-xxx-xx-xx")
    @NotBlank(message = "Phone should not be blank")
    private String phone;
    @Size(min = 4, max = 20, message = "Password length should be between 4 and 20 symbols")
    @NotBlank(message = "Password should not be blank")
    private String password;
}
