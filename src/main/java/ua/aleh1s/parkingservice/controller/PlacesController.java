package ua.aleh1s.parkingservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.aleh1s.parkingservice.dto.PlaceSearchInfo;
import ua.aleh1s.parkingservice.service.PlaceService;

@Controller
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlacesController {

    private final PlaceService placeService;
    private final HttpServletRequest request;

    @GetMapping
    public String showSearchForm(
            @ModelAttribute("searchInfo") PlaceSearchInfo searchInfo,
            @RequestParam("car-id") Integer carId
    ) {
        var session = request.getSession(false);
        session.setAttribute("car_id", carId);

        return "places/all";
    }

    @PostMapping
    public String getPlaces(
            @Valid @ModelAttribute("searchInfo") PlaceSearchInfo searchInfo,
            BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors())
            return "places/all";

        model.addAttribute("placeDtoList", placeService.getPlaces(searchInfo));
        return "places/all";
    }
}
