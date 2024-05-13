package com.proyecto.crm.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginController {
    @GetMapping({"/", "login"})
    public String getLogin(@RequestParam(name = "error", required = false) String error, Model model, HttpServletRequest httpServletRequest){
        model.addAttribute("Titulo", "Plataforma centralizada");
        return "login";
    }
}
