package com.piyush.user.service.admin;

import com.piyush.user.dto.StudentDTO;
import com.piyush.user.dto.vacancyDTOs.VacancyResponse;
import com.piyush.user.entity.Vacancy;
import com.piyush.user.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyServiceImpl implements VacancyService{

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public Vacancy saveVacancy(Vacancy vacancy) {
        Vacancy vacancy1 = null;
        if(vacancy!=null){
            vacancy1 = vacancyRepository.save(vacancy);
        }
        if(vacancy1!=null){
            return vacancy1;
        }
        return null;
    }

    @Override
    public List<VacancyResponse> getAllVacancy() {

        List<VacancyResponse> allVacancy = vacancyRepository.findAll().stream().map(vacancy -> new VacancyResponse(vacancy.getId(),
                vacancy.getApplicantType(),vacancy.getBranch(), vacancy.getDesignations(),
                vacancy.getSpecialization(), vacancy.getStartDate(), vacancy.getEndDate(),
                vacancy.getNoOfVacancy(), vacancy.getRefCodeCourse())).collect(Collectors.toList());;
        if(allVacancy!=null && allVacancy.size()>0){
            return allVacancy;
        }
        return null;
    }
}
