package com.testexcelreader.excelreader.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testexcelreader.excelreader.entity.PeopleData;
import com.testexcelreader.excelreader.repository.PeopleDataRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ReadService {
    @Autowired
    private PeopleDataRepository peopleDataRepository;
    private int headerRowIndex =0;
    private int headerColumnIndex=0;
    private int lastColumnIndex=0;
    private int lastRowIndex=0;
    @Autowired
    private ObjectMapper objectMapper;

    public String readExcel(MultipartFile file) {
        String keyword="Project ID";
        try {
            //read the file to an inputStream
            InputStream fis=file.getInputStream();
            //Local file
            //FileInputStream fis=new FileInputStream(new File("src/main/resources/files/test.xlsx"));
            //get the file data of the Excel
            Workbook book= WorkbookFactory.create(fis);
            //get the specific sheet we want(If required index can be used)
            Sheet sheet= book.getSheet("Sheet1");
            this.lastRowIndex=sheet.getLastRowNum();
            if(!this.findHeaderRow(sheet, keyword)){
                return "No row found with the required details. Please review the data";
            }
            this.addDataToDB(sheet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    @Transactional()
    private void addDataToDB(Sheet sheet) {
        Row headerRow=sheet.getRow(headerRowIndex);
        List<String> headers= new ArrayList<>();
        for(Cell cell: headerRow){
            if(cell!=null) {
                headers.add(cell.toString().trim());
                System.out.println(cell.toString().trim());
            }
        }
        int dataColumnIndex=headerRowIndex+1;
        List<Map<String,String>> sheetData= new ArrayList<>();
        for(int rowIndex=dataColumnIndex;rowIndex<=lastRowIndex;rowIndex++){
            Row currentRow=sheet.getRow(rowIndex);
            Map<String,String> currentRowValues=new HashMap<>();
            int headerIndex=0;
            DataFormatter dataFormatter= new DataFormatter();
            for(int columnIndex=headerColumnIndex;columnIndex<lastColumnIndex;columnIndex++){
                currentRowValues.put(headers.get(headerIndex),dataFormatter.formatCellValue(currentRow.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
                headerIndex++;
            }
            sheetData.add(currentRowValues);
        }
        List<PeopleData> peopleDataList=new ArrayList<>();
        for(Map<String,String> objectJson: sheetData){
            if(objectJson.get("Project ID").isEmpty() || objectJson.get("Project ID").isBlank()){
                continue;
            }
            PeopleData data=objectMapper.convertValue(objectJson,PeopleData.class);
            peopleDataList.add(data);
        }
        List<PeopleData> existingPeopleData=this.peopleDataRepository.findAll();
        List<String> existingEmailAndPidList= existingPeopleData.stream().map(peopleData -> peopleData.getEmail()+"-"+peopleData.getpId()).toList();

        List<PeopleData> filteredNewUserList=peopleDataList.stream().filter(peopleData -> !existingEmailAndPidList.contains(peopleData.getEmail()+"-"+peopleData.getpId())).toList();
        if(!filteredNewUserList.isEmpty()) {
            this.peopleDataRepository.saveAll(filteredNewUserList);
        }
//        if(!peopleDataList.isEmpty()) {
//            this.peopleDataRepository.saveAll(peopleDataList);
//        }
    }

    private boolean findHeaderRow(Sheet sheet, String keyword){
        //Iterate each row of the sheet to check for the header start
        for(Row row: sheet){
            if(findHeaderIndexes(row,keyword)){
                return true;
            }
        }
        return false;
    }

    private boolean findHeaderIndexes(Row row, String keyword) {
        this.lastColumnIndex=row.getLastCellNum();
        for(Cell cell:row){
            if(cell.getCellType()==CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(keyword)){
                this.headerColumnIndex=cell.getColumnIndex();
                this.headerRowIndex=cell.getRowIndex();
                return true;
            }
        }
        return false;
    }

    public List<PeopleData> getPeopleDataList() {
        return this.peopleDataRepository.findAll();
    }

    public Long createPeopleData(PeopleData data) {
        Optional<PeopleData> existingUser=this.peopleDataRepository.findByEmailAndPId(data.getEmail(), data.getpId());
        if(existingUser.isPresent()){
            return -1L;
        }
        return this.peopleDataRepository.save(data).getPeopleId();
    }

    public PeopleData updatePeopleData(Long peopleId, PeopleData data) {
        Optional<PeopleData> peopleDataToBeUpdated=this.peopleDataRepository.findById(peopleId);
        PeopleData updatedUserdata=null;
        if(peopleDataToBeUpdated.isPresent()){
            updatedUserdata= this.peopleDataRepository.save(data);
        }
        return updatedUserdata;
    }

    public PeopleData getPeopleDataById(Long peopleId) {
        return this.peopleDataRepository.findById(peopleId).orElse(null);
    }
}
