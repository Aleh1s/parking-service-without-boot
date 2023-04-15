package ua.aleh1s.parkingservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.aleh1s.parkingservice.dtomapper.CarDtoMapper;
import ua.aleh1s.parkingservice.dtomapper.UserDtoMapper;
import ua.aleh1s.parkingservice.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;
    private final CarDtoMapper carDtoMapper;

    @GetMapping("/profile")
    public String showProfile(HttpServletRequest request, Model model) {
        var session = request.getSession(false);
        var userId = (int) session.getAttribute("user_id");

        var user = userService.getUserById(userId);
        var carDtoList = user.getCars().stream()
                .map(carDtoMapper)
                .toList();

        model.addAttribute("userDto", userDtoMapper.apply(user));
        model.addAttribute("carDtoList", carDtoList);

        return "users/profile";
    }
}
