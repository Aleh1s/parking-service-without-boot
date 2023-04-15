package ua.aleh1s.parkingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.aleh1s.parkingservice.entity.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    boolean existsByNumber(String number);

}
