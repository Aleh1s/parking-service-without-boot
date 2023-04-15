package ua.aleh1s.parkingservice.entitymapper;

import org.springframework.stereotype.Component;
import ua.aleh1s.parkingservice.dto.SignupDto;
import ua.aleh1s.parkingservice.entity.User;

import java.util.function.Function;

@Component
public class UserMapper implements Function<SignupDto, User> {
    @Override
    public User apply(SignupDto signupDto) {
        return User.builder()
                .firstName(signupDto.getFirstName())
                .lastName(signupDto.getLastName())
                .phone(signupDto.getPhone())
                .password(signupDto.getPassword())
                .build();
    }
}
