package ua.aleh1s.parkingservice.entitymapper;

import org.springframework.stereotype.Component;
import ua.aleh1s.parkingservice.dto.CarDto;
import ua.aleh1s.parkingservice.entity.Car;

import java.util.function.Function;

@Component
public class CarMapper implements Function<CarDto, Car> {
    @Override
    public Car apply(CarDto carDto) {
        return Car.builder()
                .number(carDto.getNumber())
                .build();
    }
}
