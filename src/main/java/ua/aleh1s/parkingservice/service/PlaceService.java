package ua.aleh1s.parkingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.aleh1s.parkingservice.dto.PlaceDto;
import ua.aleh1s.parkingservice.dto.PlaceSearchInfo;
import ua.aleh1s.parkingservice.dtomapper.PlaceDtoMapper;
import ua.aleh1s.parkingservice.entity.Booking;
import ua.aleh1s.parkingservice.entity.Place;
import ua.aleh1s.parkingservice.exception.ResourceNotFoundException;
import ua.aleh1s.parkingservice.repository.PlaceRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepo placeRepo;
    private final PlaceDtoMapper placeDtoMapper;

    public List<PlaceDto> getPlaces(PlaceSearchInfo searchInfo) {
        var places = placeRepo.findPlacesByFloorOrderByPlace(searchInfo.getFloor());

        for (Place place : places) {
            var bookings = place.getBookings();
            var isTaken = bookings.stream()
                    .anyMatch(booking -> isPlaceTaken(booking, searchInfo.getCheckIn(), searchInfo.getCheckOut()));
            place.setTaken(isTaken);
        }

        return places.stream()
                .map(placeDtoMapper)
                .toList();
    }

    public boolean isPlaceTaken(Booking placeBooking, LocalDateTime checkIn, LocalDateTime checkOut) {
        if (placeBooking == null)
            return false;

        var bookingCheckIn = placeBooking.getCheckIn();
        var bookingCheckOut = placeBooking.getCheckOut();

        return !bookingCheckIn.isAfter(checkOut) && !bookingCheckOut.isBefore(checkIn);
    }

    public Place getPlaceById(Integer id) {
        return placeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with id " + id + " not found"));
    }
}
