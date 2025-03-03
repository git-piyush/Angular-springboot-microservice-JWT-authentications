package com.piyush.user.service.admin;

import com.piyush.user.entity.Vacancy;
import org.springframework.stereotype.Service;

@Service
public interface VacancyService {
    Vacancy saveVacancy(Vacancy vacancy);
}
