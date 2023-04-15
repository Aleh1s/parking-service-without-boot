package ua.aleh1s.parkingservice.service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.aleh1s.parkingservice.dto.LoginDto;
import ua.aleh1s.parkingservice.dto.SignupDto;
import ua.aleh1s.parkingservice.dto.UserDto;
import ua.aleh1s.parkingservice.dtomapper.UserDtoMapper;
import ua.aleh1s.parkingservice.entity.User;
import ua.aleh1s.parkingservice.entity.UserRole;
import ua.aleh1s.parkingservice.entitymapper.UserMapper;
import ua.aleh1s.parkingservice.exception.PhoneAlreadyExistsException;
import ua.aleh1s.parkingservice.exception.WrongPasswordException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Transactional
    public void registerCustomer(SignupDto signupDto) {
        registerUser(signupDto, UserRole.CUSTOMER);
    }

    @Transactional
    public void registerManager(SignupDto signupDto) {
        registerUser(signupDto, UserRole.MANAGER);
    }

    private void registerUser(SignupDto signupDto, UserRole role) {
        var phone = signupDto.getPhone();
        if (userService.existsUserByPhone(phone))
            throw new PhoneAlreadyExistsException("User with phone " + phone + " already exists");

        var user = userMapper.apply(signupDto);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setRole(role);

        userService.save(user);
    }

    public UserDto logIn(LoginDto loginDto) {
        var user = userService.getUserByPhone(loginDto.getPhone());

        if (!BCrypt.checkpw(loginDto.getPassword(), user.getPassword()))
            throw new WrongPasswordException("Wrong password!!!");

        return userDtoMapper.apply(user);
    }

    @Transactional
    public void changePassword(Integer userId, String newPassword) {
        var user = userService.getUserById(userId);
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    }
}
