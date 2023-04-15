package ua.aleh1s.parkingservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.aleh1s.parkingservice.dto.CarDto;
import ua.aleh1s.parkingservice.dtomapper.CarDtoMapper;
import ua.aleh1s.parkingservice.entity.Place;
import ua.aleh1s.parkingservice.exception.CarNumberAlreadyExists;
import ua.aleh1s.parkingservice.service.BookingService;
import ua.aleh1s.parkingservice.service.CarService;
import ua.aleh1s.parkingservice.service.PlaceService;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarDtoMapper carDtoMapper;
    private final BookingService bookingService;

    @GetMapping("/new")
    public String showAddCarForm(@ModelAttribute("carDto") CarDto carDto) {
        return "cars/new";
    }

    @PostMapping
    public String createCar(
            @Valid @ModelAttribute("carDto") CarDto carDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors())
            return "cars/new";

        var userId = (Integer) request.getSession(false).getAttribute("user_id");
        try {
            carService.save(userId, carDto);
        } catch (CarNumberAlreadyExists e) {
            bindingResult.rejectValue("number", "", "Car number already exists");
            return "cars/new";
        }

        return "redirect:/users/profile";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") Integer id) {
        carService.delete(id);
        return "redirect:/users/profile";
    }

    @GetMapping("/{id}")
    public String getCarById(@PathVariable("id") Integer id, Model model) {
        var car = carService.getCarById(id);
        var bookingDto = bookingService.findBookingByCarId(id);
        model.addAttribute("carDto", carDtoMapper.apply(car));
        model.addAttribute("bookingDto", bookingDto.orElse(null));
        return "cars/one";
    }
}
