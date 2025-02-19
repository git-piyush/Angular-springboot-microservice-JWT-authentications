package com.piyush.setting.controller;

import com.piyush.setting.dto.DropDownResponse;
import com.piyush.setting.dto.RefCodeModelRequest;
import com.piyush.setting.dto.RefCodeModelResponse;
import com.piyush.setting.entity.RefCode;
import com.piyush.setting.service.serviceImpl.RefCodeService;
import com.piyush.setting.util.AppConstant;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/refcode")
public class RefCodeController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RefCodeService refCodeService;

    @PostMapping("/create")
    public ResponseEntity createRefcode(@RequestBody RefCodeModelRequest modelRequest){
        //model request to model entity
        RefCode refCode = mapper.map(modelRequest, RefCode.class);

        System.out.println(refCode.getRefCode());

        RefCode createdRefCode = refCodeService.createRefCode(refCode);

        if(createdRefCode!=null){
            return new ResponseEntity<>(createdRefCode, HttpStatus.OK);
        }
        return new ResponseEntity<>("Error In Ref Code Creation.", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getrefcode")
    @Transactional
    public ResponseEntity getAllRefcode(@RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = AppConstant.DEFAULT_SORT_DIR, required = false) String sortDir,
                                         @RequestParam(value = "filterType", required = false) String filterType,
                                         @RequestParam(value = "filterText", required = false) String filterText){


        RefCodeModelResponse allRefCode = refCodeService.getAllRefCode(pageNo, pageSize, sortBy, sortDir, filterType,filterText);
        if(allRefCode.getContent()!=null && !allRefCode.getContent().isEmpty()){
            return new ResponseEntity<>(allRefCode, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getrefcode/{category}")
    @Transactional
    public ResponseEntity getAllRefcodeByCategory(@PathVariable String category){

        System.out.println(category);

        List<DropDownResponse> allRefCode = refCodeService.getAllRefCodeByCategory(category);
        if(allRefCode!=null){
            return new ResponseEntity<>(allRefCode, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getrefcodecategorylist")
    @Transactional
    public ResponseEntity getRefcodeCategoryList(){

        List<DropDownResponse> allRefCode = refCodeService.getRefcodeCategoryList();
        if(allRefCode!=null){
            return new ResponseEntity<>(allRefCode, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

}
