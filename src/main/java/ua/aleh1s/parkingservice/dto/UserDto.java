package ua.aleh1s.parkingservice.dto;

import lombok.Builder;
import lombok.Data;
import ua.aleh1s.parkingservice.entity.UserRole;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private UserRole role;
}
