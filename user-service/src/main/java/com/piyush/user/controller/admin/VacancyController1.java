package com.piyush.user.controller.admin;

import com.piyush.user.dto.ErrorMsg;
import com.piyush.user.dto.vacancyDTOs.VacancyRequest;
import com.piyush.user.dto.vacancyDTOs.VacancyResponse;
import com.piyush.user.entity.Vacancy;
import com.piyush.user.service.admin.VacancyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancy")
public class VacancyController1 {

    @GetMapping("/get-hello")
    public String hello(){
        return "Hello";
    }

}
