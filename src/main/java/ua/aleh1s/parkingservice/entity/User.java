package ua.aleh1s.parkingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "person")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "password", nullable = false)
    private String password;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "owner", cascade = ALL, orphanRemoval = true)
    private Set<Car> cars = new HashSet<>();

    public void addCar(Car car) {
        this.cars.add(car);
        car.setOwner(this);
    }

    public void removeCar(Car car) {
        this.cars.remove(car);
        car.setOwner(null);
    }
}
