package ua.aleh1s.parkingservice.service;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.aleh1s.parkingservice.dto.CarDto;
import ua.aleh1s.parkingservice.entity.Car;
import ua.aleh1s.parkingservice.entitymapper.CarMapper;
import ua.aleh1s.parkingservice.exception.CarNumberAlreadyExists;
import ua.aleh1s.parkingservice.exception.ResourceNotFoundException;
import ua.aleh1s.parkingservice.repository.CarRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CarService {

    private final CarRepo carRepo;
    private final UserService userService;
    private final CarMapper carMapper;

    @Transactional
    public void save(int userId, CarDto carDto) {
        var number = carDto.getNumber();
        if (carRepo.existsByNumber(number))
            throw new CarNumberAlreadyExists("Car with number " + number + " already exists");

        var user = userService.getUserById(userId);
        var car = carMapper.apply(carDto);
        user.addCar(car);

        carRepo.save(car);
    }

    public Car getCarById(int carId) {
        return carRepo.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + carId + " not found"));
    }

    @Transactional
    public void delete(int carId) {
        var car = getCarById(carId);
        var owner = car.getOwner();
        owner.removeCar(car);
    }
}