package com.piyush.user.service.admin;

import com.piyush.user.entity.Vacancy;
import com.piyush.user.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
