package ua.aleh1s.parkingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.aleh1s.parkingservice.dto.BookingDto;
import ua.aleh1s.parkingservice.entity.Booking;
import ua.aleh1s.parkingservice.entity.Car;
import ua.aleh1s.parkingservice.entity.Place;
import ua.aleh1s.parkingservice.repository.BookingRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepo bookingRepo;
    private final CarService carService;
    private final PlaceService placeService;

    public Optional<BookingDto> findBookingByCarId(Integer carId) {
        var bookingOptional = bookingRepo.findBookingByCarId(carId);

        if (bookingOptional.isPresent()) {
            var booking = bookingOptional.get();

            var place = booking.getPlace();
            return Optional.of(map(booking, place));
        }

        return Optional.empty();
    }

    private BookingDto map(Booking booking, Place place) {
        if (booking.getCheckOut().isBefore(LocalDateTime.now()))
            booking.setExpired(true);

        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return BookingDto.builder()
                .id(booking.getId())
                .checkIn(booking.getCheckIn().format(dateTimeFormatter))
                .checkOut(booking.getCheckOut().format(dateTimeFormatter))
                .floor(place.getFloor())
                .place(place.getPlace())
                .isExpired(booking.isExpired())
                .build();
    }

    @Transactional
    public void bookPlace(Integer placeId, Integer carId, LocalDateTime checkIn, LocalDateTime checkOut) {
        var car = carService.getCarById(carId);
        var place = placeService.getPlaceById(placeId);

        var booking = Booking.builder()
                .checkIn(checkIn)
                .checkOut(checkOut)
                .build();

        car.addBooking(booking);
        place.addBooking(booking);

        bookingRepo.save(booking);
    }

    @Transactional
    public void delete(Integer bookingId) {
        bookingRepo.deleteById(bookingId);
    }
}
