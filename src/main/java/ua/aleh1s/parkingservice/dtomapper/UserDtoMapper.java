package ua.aleh1s.parkingservice.dtomapper;

import org.springframework.stereotype.Component;
import ua.aleh1s.parkingservice.dto.UserDto;
import ua.aleh1s.parkingservice.entity.User;

import java.util.function.Function;

@Component
public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
