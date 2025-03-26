package com.piyush.user.service.admin;

import com.piyush.user.dto.vacancyDTOs.VacancyResponse;
import com.piyush.user.entity.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    Vacancy saveVacancy(Vacancy vacancy);

    List<VacancyResponse> getAllVacancy();
}
