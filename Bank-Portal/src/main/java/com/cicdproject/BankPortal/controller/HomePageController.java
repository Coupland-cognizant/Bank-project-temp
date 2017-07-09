package com.cicdproject.BankPortal.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HomePageController {

	@RequestMapping(value = {"/"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String accountPage() {
		return "Hello World";
	}
}
