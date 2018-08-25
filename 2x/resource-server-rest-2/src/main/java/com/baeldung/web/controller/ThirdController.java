package com.baeldung.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baeldung.service.ThirdService;
import com.baeldung.web.dto.Third;

@Controller
public class ThirdController {

    private ThirdService thirdService;

    public ThirdController(ThirdService thirdService) {
        this.thirdService = thirdService;
    }

    // API

    @RequestMapping(method = RequestMethod.GET, value = "/third/{id}")
    @ResponseBody
    public Third findById(@PathVariable final long id) {
        return thirdService.findById(id);
    }

}
