package com.testexcelreader.excelreader.controller;

import com.testexcelreader.excelreader.entity.PeopleData;
import com.testexcelreader.excelreader.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin()
@RestController
public class FileUploadController {

    @Autowired
    public ReadService service;

    @PostMapping("upload")
    public ResponseEntity<String> readExcel(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(service.readExcel(file));
    }

    @GetMapping("peopleData")
    public List<PeopleData> getPeopleData(){
        return service.getPeopleDataList();
    }

    @PostMapping("addPeopleData")
    public ResponseEntity<String> addPeopleData(@RequestBody PeopleData data){
        Long id=service.createPeopleData(data);
        HttpHeaders headerValues=new HttpHeaders();
        String responseMessage="";
        HttpStatus httpStatus=HttpStatus.CREATED;
        if(id!=-1L) {
            headerValues.add("id", id.toString());
            responseMessage="Data was added successfully for the given details";
        }else{
            responseMessage="User Already Exists";
            httpStatus= HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus)
                .headers(headerValues)
                .body(responseMessage);
    }

    @PutMapping("updatePeopleData/{peopleId}")
    public ResponseEntity<String> addPeopleData(@PathVariable Long peopleId, @RequestBody PeopleData data){
        PeopleData updatedData=service.updatePeopleData(peopleId, data);
        if(updatedData!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Data was updated successfully with the given details");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Data was not found for the given id");
        }
    }

    @GetMapping("getData/{peopleId}")
    public ResponseEntity<PeopleData> getPeopleData(@PathVariable Long peopleId){
        PeopleData dataFound=service.getPeopleDataById(peopleId);
        if(dataFound!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(dataFound);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
