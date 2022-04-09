package edu.ac.community.service.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public abstract class BaseController {

    private HttpServletRequest httpServletRequest;

}
