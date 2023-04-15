package ua.aleh1s.parkingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.aleh1s.parkingservice.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsUsersByPhone(String phone);
    Optional<User> findUserByPhone(String phone);
}
