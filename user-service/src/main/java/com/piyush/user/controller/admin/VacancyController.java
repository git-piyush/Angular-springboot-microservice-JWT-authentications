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

    @GetMapping("/vacancy")
    public ResponseEntity getAllVacancy(){
        System.out.println("get all vacancy");
        try {
            List<VacancyResponse> vacancyList = vacancyService.getAllVacancy();
            if(vacancyList!=null){
                return new ResponseEntity<>(vacancyList, HttpStatus.OK);
            }
            if(vacancyList==null){
                ErrorMsg msg = new ErrorMsg();
                msg.setMsg("No Record found.");
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
        }catch (Exception e){
            ErrorMsg msg = new ErrorMsg();
            msg.setMsg("Something went wrong.");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        ErrorMsg msg = new ErrorMsg();
        msg.setMsg("Something went wrong.");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
