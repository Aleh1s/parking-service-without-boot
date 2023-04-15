package ua.aleh1s.parkingservice.exception;

public class CarNumberAlreadyExists extends RuntimeException {

    public CarNumberAlreadyExists() {
    }

    public CarNumberAlreadyExists(String message) {
        super(message);
    }

    public CarNumberAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public CarNumberAlreadyExists(Throwable cause) {
        super(cause);
    }
}
