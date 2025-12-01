package org.jasper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlParser {
    public static List<Map<String, ?>> parse() throws IOException {
        ObjectMapper mapper = new XmlMapper();
        InputStream xmlStream = FileProvider.readXml();

        JsonNode extract = mapper.readTree(xmlStream);
        JsonNode allHolidaysEntries = extract.get("holydays");

        List<Map<String, ?>> mapHolidaysList = new ArrayList<>();

        for(var c : allHolidaysEntries){
            Map<String, ?> entry = mapper.convertValue(c, Map.class);
            mapHolidaysList.add(entry);
        }

        return mapHolidaysList;
    }
}
