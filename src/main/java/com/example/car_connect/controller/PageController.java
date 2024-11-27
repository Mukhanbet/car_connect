package com.example.car_connect.controller;

import com.example.car_connect.model.dto.auth.LoginRequest;
import com.example.car_connect.model.dto.auth.RegisterRequest;
import com.example.car_connect.model.dto.car.CarFilter;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.car.CarResponseDetail;
import com.example.car_connect.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/pages")
@AllArgsConstructor
public class PageController {
    private final CarService carService;

    @GetMapping("/auth")
    public String login(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("carFilter", new CarFilter());
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/booking/{id}")
    public String booking(
            @PathVariable UUID id,
            Model model
    ) {
        CarResponseDetail detail = carService.getDetail(id);

        model.addAttribute("detail", detail);
        return "booking";
    }

    @GetMapping("/car")
    public String car(Model model) {
        List<CarResponse> cars = carService.all();
        model.addAttribute("cars", cars);
        return "car";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable UUID id,
            Model model
    ) {
        CarResponseDetail detail = carService.getDetail(id);
        List<CarResponse> cars = carService.getRelatedCars(id);

        model.addAttribute("detail", detail);
        model.addAttribute("relatedCars", cars);

        return "detail";
    }

    @GetMapping("/service")
    public String service(Model model) {
        return "service";
    }

    @GetMapping("/team")
    public String team(Model model) {
        return "team";
    }

    @GetMapping("/testimonial")
    public String testimonial(Model model) {
        return "testimonial";
    }
}
