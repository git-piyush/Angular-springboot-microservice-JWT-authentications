package com.piyush.setting.service.serviceImpl;

import com.piyush.setting.dto.DropDownResponse;
import com.piyush.setting.dto.RefCodeDTO;
import com.piyush.setting.dto.RefCodeModelResponse;
import com.piyush.setting.entity.RefCode;
import com.piyush.setting.repository.RefCodeRepository;
import com.piyush.setting.util.AppConstant;
import jakarta.persistence.Tuple;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefCodeServiceImpl implements RefCodeService {

    @Autowired
    private RefCodeRepository refCodeRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public RefCode createRefCode(RefCode refCode) {
        return refCodeRepository.save(refCode);
    }

    @Override
    public RefCodeModelResponse getAllRefCode(int pageNo, int pageSize, String sortBy, String sortDir, String filterType, String filterValue, String filterText) {

        Sort sort = sortDir.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<RefCode> page = null;
        if(filterType==null || filterType==""){
            page = refCodeRepository.findByActive(AppConstant.YES,pageable);
        }else{
            if(filterType!=null && filterType.equalsIgnoreCase("REFCODE")) {
                page = refCodeRepository.findByRefCode(filterText,pageable);
            }else if(filterType!=null && filterType.equalsIgnoreCase("CATEGORY")){
                page = refCodeRepository.findByCategory(filterValue,pageable);
            }else if(filterType!=null && filterType.equalsIgnoreCase("LONGNAME")){
                page = refCodeRepository.findByLongNameContaining(filterText,pageable);
            }else if(filterType!=null && filterType.equalsIgnoreCase("ACTIVE")){
                page = refCodeRepository.findByActive(filterText,pageable);
            }
        }
        //get the content from page
        List<RefCode> refList = page.getContent();
        List<RefCodeDTO> allRefCode = refList.stream().map(refCode-> new RefCodeDTO(refCode.getRefCode(), refCode.getCategory(), refCode.getLongName(), refCode.getActive())).collect(Collectors.toList());
        RefCodeModelResponse res = new RefCodeModelResponse();
        res.setContent(allRefCode);
        res.setPageNo(page.getNumber());
        res.setPageSize(page.getSize());
        res.setLast(page.isLast());
        res.setTotalElements(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        return res;
    }

    @Override
    public List<DropDownResponse> getAllRefCodeByCategory(String category) {
        List<RefCode> refCodes = refCodeRepository.findByCategory(category);
        List<DropDownResponse> list = new ArrayList<>();
        if(refCodes!=null){
            for(RefCode refCode: refCodes){
                DropDownResponse dropDown = new DropDownResponse();
                dropDown.setValue(refCode.getRefCode());
                dropDown.setLabel(refCode.getLongName());
                list.add(dropDown);
            }
           return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<DropDownResponse> getRefcodeCategoryList() {
        List<String> refCodes = refCodeRepository.findDistinctRefCodeByCategoryColumn();
        List<DropDownResponse> list = new ArrayList<>();
        if(refCodes!=null){
            for(String refCode: refCodes){
                DropDownResponse dropDown = new DropDownResponse();
                dropDown.setValue(refCode);
                dropDown.setLabel(refCode);
                list.add(dropDown);
            }
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean deleteRefCodeById(String refCode) {

       try{
           if(refCode!=null) {
               refCodeRepository.deleteByRefCode(refCode);
               return true;
           }
       }catch (Exception e){
            return false;
       }
        return false;
    }


}
