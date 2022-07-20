package com.api.parkingcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class ParkingControlApplication {
	public static void main(String[] args) {SpringApplication.run(ParkingControlApplication.class,args);}
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(HttpServletRequest request, BindingResult result, ModelMap model)
	{
		String redirectUrl = "https://parking-mml.herokuapp.com/swagger-ui/index.html";
		return "redirect:" + redirectUrl;
	}
	}


