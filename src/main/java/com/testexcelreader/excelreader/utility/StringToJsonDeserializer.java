//package com.testexcelreader.excelreader.utility;
//
//import com.fasterxml.jackson.core.JacksonException;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import jdk.jfr.Category;
//import org.hibernate.annotations.Comment;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class StringToJsonDeserializer extends JsonDeserializer<Long> {
//
//    @Override
//    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
//        try{
//           return Long.parseLong(jsonParser.getText().trim());
//        }catch (NumberFormatException exception){
//            return null;
//        }
//    }
//}
