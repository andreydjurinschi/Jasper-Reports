package org.jasper;

import java.io.InputStream;

public class FileProvider {
    public static InputStream readXml(){
        return FileProvider.class.getClassLoader().getResourceAsStream("holidays.xml");
    }
    public static InputStream readJRXML(){
        return FileProvider.class.getClassLoader().getResourceAsStream("CedacriReport1.jrxml");
    }
    public static InputStream readJRXMLForBean(){
        return FileProvider.class.getClassLoader().getResourceAsStream("CedacriBeanReport.jrxml");
    }
}
