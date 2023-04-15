package ua.aleh1s.parkingservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.aleh1s.parkingservice.dto.PlaceSearchInfo;
import ua.aleh1s.parkingservice.service.BookingService;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final HttpServletRequest request;
    private final BookingService bookingService;

    @PostMapping("/{place-id}")
    public String bookPlace(
            @ModelAttribute("searchInfo") PlaceSearchInfo searchInfo,
            @PathVariable("place-id") Integer placeId
    ) {
        var session = request.getSession(false);

        var carId = (Integer) session.getAttribute("car_id");
        var checkIn = searchInfo.getCheckIn();
        var checkOut = searchInfo.getCheckOut();

        bookingService.bookPlace(placeId, carId, checkIn, checkOut);

        session.removeAttribute("car_id");
        return "redirect:/cars/" + carId;
    }

    @DeleteMapping("/{booking-id}/cars/{car-id}")
    public String deleteBooking(
            @PathVariable("booking-id") Integer bookingId,
            @PathVariable("car-id") Integer carId
    ) {
        bookingService.delete(bookingId);
        return "redirect:/cars/" + carId;
    }

}
