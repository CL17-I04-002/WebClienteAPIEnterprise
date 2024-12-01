package com.proyecto.crm.controller;

import com.proyecto.crm.entity.Enterprise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("enterprise")
public class EnterpriseController {
    @GetMapping
    public String getEnterprise(Model model){

        //model.addAttribute(enterprise);
        return "enterprise";
    }
}
