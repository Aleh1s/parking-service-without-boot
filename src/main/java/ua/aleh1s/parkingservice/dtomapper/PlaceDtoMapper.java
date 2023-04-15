package ua.aleh1s.parkingservice.dtomapper;

import org.springframework.stereotype.Component;
import ua.aleh1s.parkingservice.dto.PlaceDto;
import ua.aleh1s.parkingservice.entity.Place;

import java.util.function.Function;

@Component
public class PlaceDtoMapper implements Function<Place, PlaceDto> {
    @Override
    public PlaceDto apply(Place place) {
        return PlaceDto.builder()
                .id(place.getId())
                .floor(place.getFloor())
                .place(place.getPlace())
                .isTaken(place.isTaken())
                .build();
    }
}
