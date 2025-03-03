package com.piyush.user.controller.admin;

import com.piyush.user.constants.AppConstants;
import com.piyush.user.dto.settingDTOs.DropDownResponse;
import com.piyush.user.dto.settingDTOs.RefCodeModelRequest;
import com.piyush.user.dto.settingDTOs.RefCodeModelResponse;
import com.piyush.user.entity.RefCode;
import com.piyush.user.repository.RefCodeRepository;
import com.piyush.user.service.admin.RefCodeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class SettingController {

    @Autowired
    private RefCodeRepository refCodeRepository;

    @Autowired
    private RefCodeService refCodeService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create")
    public ResponseEntity createRefcode(@RequestBody RefCodeModelRequest modelRequest){
        //model request to model entity
        modelRequest.setRefCode(modelRequest.getRefCode().toUpperCase());
        modelRequest.setCategory(modelRequest.getCategory().toUpperCase());
        modelRequest.setLongName(modelRequest.getLongName().toUpperCase());
        modelRequest.setActive(AppConstants.YES);
        RefCode refCode1 = refCodeRepository.findByRefCode(modelRequest.getRefCode().toUpperCase());
        if(refCode1!=null){
            return new ResponseEntity<>("Please Enter a Unique RefCode.", HttpStatus.OK);
        }

        RefCode refCode = mapper.map(modelRequest, RefCode.class);

        RefCode createdRefCode = refCodeService.createRefCode(refCode);

        if(createdRefCode!=null){
            return new ResponseEntity<>(createdRefCode, HttpStatus.OK);
        }
        return new ResponseEntity<>("Error In Ref Code Creation.", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getrefcode")
    @Transactional
    public ResponseEntity getAllRefcode(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir,
                                        @RequestParam(value = "filterType", required = false) String filterType,
                                        @RequestParam(value = "filterValue", required = false) String filterValue,
                                        @RequestParam(value = "filterText", required = false) String filterText){

        System.out.println(filterType);
        System.out.println(filterValue);
        System.out.println(filterText);

        RefCodeModelResponse allRefCode = refCodeService.getAllRefCode(pageNo, pageSize, sortBy, sortDir, filterType,filterValue,filterText);
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

    @DeleteMapping("/deleteRefCode/{refCode}")
    @Transactional
    public ResponseEntity deleteRefCodeById(@PathVariable String refCode){

        boolean deleted = refCodeService.deleteRefCodeById(refCode);
        if(deleted){
            return new ResponseEntity<>("RefCode Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong.", HttpStatus.NOT_FOUND);
    }



    @GetMapping("/getusertypedropdown/{userCategory}")
    @Transactional
    public ResponseEntity getUserTypeDropdown(@PathVariable String userCategory){

        List<DropDownResponse> allRefCode = refCodeService.getUserTypeDropdown(userCategory);
        if(allRefCode!=null){
            return new ResponseEntity<>(allRefCode, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/refcode/{refcode}")
    public ResponseEntity getLongNameByRefcode(@PathVariable String refcode){
        RefCode refCode = refCodeService.findByRefCode(refcode);
        if(refCode!=null){
            return new ResponseEntity<>(refCode.getLongName(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

}
