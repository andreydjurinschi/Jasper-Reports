package org.jasper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jasper.pojo.HolidayPojo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataInserter {

    public static void insertData() throws IOException {
        List<HolidayPojo> allHolidays = new ArrayList<>();
        ObjectMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        JsonNode root = mapper.readTree(FileProvider.readXml());
        JsonNode holidays = root.get("holydays");

        for(var h : holidays){
            HolidayPojo pojo = new HolidayPojo();
            JsonNode name = h.get("NAME");
            JsonNode country = h.get("COUNTRY");
            JsonNode date = h.get("DATE");

            pojo.setNAME(name.asText());
            pojo.setCOUNTRY(country.asText());
            pojo.setDATE(LocalDate.parse(date.asText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            allHolidays.add(pojo);
        }

        for (var h : allHolidays){
            Repository.insertHoliday(h);
        }
    }

/*    public static void main(String[] a) throws IOException {
        insertData();
    }*/
}
