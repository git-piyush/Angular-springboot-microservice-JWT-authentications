package com.piyush.user.controller.admin;

import com.piyush.user.dto.ErrorMsg;
import com.piyush.user.dto.VacancyRequest;
import com.piyush.user.entity.Vacancy;
import com.piyush.user.service.admin.VacancyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/admin")
public class VacancyController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/vacancy")
    public ResponseEntity createVacancy(@RequestBody VacancyRequest modelRequest){
        try {
            if(modelRequest!=null){
                Vacancy vacancy = modelMapper.map(modelRequest, Vacancy.class);
                Vacancy vacancy1 = vacancyService.saveVacancy(vacancy);
            }
            ErrorMsg msg = new ErrorMsg();
            msg.setMsg("Job has been created.");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }catch (Exception e){
            ErrorMsg msg = new ErrorMsg();
            msg.setMsg("Something went wrong.");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }

}
