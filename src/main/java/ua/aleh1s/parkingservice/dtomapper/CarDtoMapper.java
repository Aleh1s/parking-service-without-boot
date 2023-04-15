package ua.aleh1s.parkingservice.dtomapper;

import org.springframework.stereotype.Component;
import ua.aleh1s.parkingservice.dto.CarDto;
import ua.aleh1s.parkingservice.entity.Car;

import java.util.function.Function;

@Component
public class CarDtoMapper implements Function<Car, CarDto> {
    @Override
    public CarDto apply(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .number(car.getNumber())
                .build();
    }
}
