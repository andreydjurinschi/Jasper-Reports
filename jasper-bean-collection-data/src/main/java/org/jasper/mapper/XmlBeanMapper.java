package org.jasper.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jasper.FileProvider;
import org.jasper.pojo.HolidayPojo;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanMapper {
    public static List<HolidayPojo> parse() throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
        InputStream stream = FileProvider.readXml();
        JsonNode node = mapper.readTree(stream);
        JsonNode allHolidays = node.get("holydays");

        List<HolidayPojo> holidayPojoList = new ArrayList<>();

        for(var holiday : allHolidays){
            HolidayPojo pojo = new HolidayPojo();
            JsonNode date = holiday.get("DATE");
            JsonNode name = holiday.get("NAME");
            JsonNode country = holiday.get("COUNTRY");
            pojo.setNAME(name.asText());
            pojo.setCOUNTRY(country.asText());
            pojo.setDATE(LocalDate.parse(date.asText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            holidayPojoList.add(pojo);
        }
        return holidayPojoList;

    }
}
