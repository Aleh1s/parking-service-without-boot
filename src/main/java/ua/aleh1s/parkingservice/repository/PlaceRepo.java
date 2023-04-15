package ua.aleh1s.parkingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.aleh1s.parkingservice.entity.Place;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Integer> {
    @Query("from Place p left join fetch p.bookings where p.floor = :floor order by p.place")
    List<Place> findPlacesByFloorOrderByPlace(@Param("floor") int floor);
}
