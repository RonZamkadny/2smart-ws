package com.ronx.smart.configuration;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/x")
public class FrontController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView landingPage(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("view/landing.html");
    }
}
