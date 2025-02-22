package com.piyush.setting.service.serviceImpl;

import com.piyush.setting.dto.DropDownResponse;
import com.piyush.setting.dto.RefCodeModelResponse;
import com.piyush.setting.entity.RefCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RefCodeService {

    RefCode createRefCode(RefCode refCode);

    RefCodeModelResponse getAllRefCode(int pageNo, int pageSize, String sortBy, String sortDir, String filterType, String filterValue, String filterText);

    List<DropDownResponse> getAllRefCodeByCategory(String category);

    List<DropDownResponse> getRefcodeCategoryList();

    boolean deleteRefCodeById(String refCode);
}
