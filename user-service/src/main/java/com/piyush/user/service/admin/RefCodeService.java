package com.piyush.user.service.admin;

import com.piyush.user.dto.settingDTOs.DropDownResponse;
import com.piyush.user.dto.settingDTOs.RefCodeModelResponse;
import com.piyush.user.entity.RefCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RefCodeService {

    RefCode createRefCode(RefCode refCode);

    RefCodeModelResponse getAllRefCode(int pageNo, int pageSize, String sortBy, String sortDir, String filterType, String filterValue, String filterText);

    List<DropDownResponse> getAllRefCodeByCategory(String category);

    List<DropDownResponse> getRefcodeCategoryList();

    boolean deleteRefCodeById(String refCode);

    List<DropDownResponse> getUserTypeDropdown(String userCategory);

    RefCode findByRefCode(String refcode);
}
