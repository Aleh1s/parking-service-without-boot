package ua.aleh1s.parkingservice.exception;

public class PhoneAlreadyExistsException extends RuntimeException {

    public PhoneAlreadyExistsException() {
    }

    public PhoneAlreadyExistsException(String message) {
        super(message);
    }

    public PhoneAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
