package ua.aleh1s.parkingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "booking")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Booking {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_in", nullable = false)
    private LocalDateTime checkIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_out", nullable = false)
    private LocalDateTime checkOut;

    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Transient
    private boolean isExpired;
}
