package ua.aleh1s.parkingservice.service;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.aleh1s.parkingservice.entity.User;
import ua.aleh1s.parkingservice.exception.ResourceNotFoundException;
import ua.aleh1s.parkingservice.repository.UserRepo;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    @Transactional
    public void save(User user) {
        userRepo.save(user);
    }

    public User getUserById(int userId) {
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        Hibernate.initialize(user.getCars());
        return user;
    }

    public User getUserByPhone(String phone) {
        return userRepo.findUserByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User with phone " + phone + " not found"));
    }

    public boolean existsUserByPhone(String phone) {
        return userRepo.existsUsersByPhone(phone);
    }
}