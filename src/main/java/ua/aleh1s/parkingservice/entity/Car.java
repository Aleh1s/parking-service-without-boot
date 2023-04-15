package ua.aleh1s.parkingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "car")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Car {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToOne(mappedBy = "car", fetch = LAZY, cascade = REMOVE, orphanRemoval = true)
    private Booking booking;

    public void addBooking(Booking booking) {
        this.booking = booking;
        booking.setCar(this);
    }
}