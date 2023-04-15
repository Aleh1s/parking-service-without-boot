package ua.aleh1s.parkingservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "place")
@Getter
@Setter
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "floor")
    private int floor;

    @Column(name = "place")
    private int place;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "place", cascade = ALL, fetch = LAZY)
    private Set<Booking> bookings = new HashSet<>();

    @Transient
    private boolean isTaken;

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setPlace(this);
    }

}
