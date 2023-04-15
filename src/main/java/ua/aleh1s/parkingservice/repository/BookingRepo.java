package ua.aleh1s.parkingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.aleh1s.parkingservice.entity.Booking;

import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    Optional<Booking> findBookingByCarId(Integer carId);

}
