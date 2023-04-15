package ua.aleh1s.parkingservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.aleh1s.parkingservice.dto.LoginDto;
import ua.aleh1s.parkingservice.dto.PasswordDto;
import ua.aleh1s.parkingservice.dto.SignupDto;
import ua.aleh1s.parkingservice.dto.UserDto;
import ua.aleh1s.parkingservice.exception.PhoneAlreadyExistsException;
import ua.aleh1s.parkingservice.exception.ResourceNotFoundException;
import ua.aleh1s.parkingservice.exception.WrongPasswordException;
import ua.aleh1s.parkingservice.service.AuthService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public String showSignupForm(@ModelAttribute("signupDto") SignupDto signupDto) {
        return "auth/signup";
    }

    @PostMapping("/customer/sign-up")
    public String signUpCustomer(@ModelAttribute("signupDto") @Valid SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "auth/signup";

        try {
            authService.registerCustomer(signupDto);
        } catch (PhoneAlreadyExistsException e) {
            bindingResult.rejectValue("phone", "", "Phone already exists!");
            return "auth/signup";
        }

        return "redirect:/auth/login";
    }

    @PostMapping("/manager/sign-up")
    public String signUpManager(@ModelAttribute("signupDto") @Valid SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "auth/signup";

        try {
            authService.registerManager(signupDto);
        } catch (PhoneAlreadyExistsException e) {
            bindingResult.rejectValue("phone", "", "Phone already exists!");
            return "auth/signup";
        }

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute("loginDto") LoginDto loginDto) {
        return "auth/login";
    }

    @PostMapping("/log-in")
    public String logIn(
            @ModelAttribute("loginDto") LoginDto loginDto,
            BindingResult bindingResult,
            HttpServletRequest request) {
        var userDto = (UserDto) null;

        try {
            userDto = authService.logIn(loginDto);
        } catch (ResourceNotFoundException e) {
            bindingResult.rejectValue("phone", "", "User with entered phone does not exist");
        } catch (WrongPasswordException e) {
            bindingResult.rejectValue("password", "", "Wrong password!");
        }

        if (bindingResult.hasErrors())
            return "auth/login";

        assert userDto != null;

        var session = request.getSession();
        session.setAttribute("user_id", userDto.getId());
        session.setAttribute("user_role", userDto.getRole());

        return "redirect:/users/profile";
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(@ModelAttribute("passwordDto") PasswordDto passwordDto) {
        return "auth/change_password";
    }

    @PatchMapping("/change-password")
    public String changePassword(
            @Valid @ModelAttribute("passwordDto") PasswordDto passwordDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors())
            return "auth/change_password";

        var userId = (Integer) request.getSession(false).getAttribute("user_id");
        authService.changePassword(userId, passwordDto.getPassword());
        return "redirect:/users/profile";
    }
}
