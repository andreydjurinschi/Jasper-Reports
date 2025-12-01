package org.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRCompiler;
import net.sf.jasperreports.engine.design.JRReportCompileData;
import org.jasper.mapper.XmlBeanMapper;
import org.jasper.pojo.HolidayPojo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JasperBeanGenerator {
    public static void generate(String filename, String dirName) throws IOException, JRException {
        List<HolidayPojo> holidayList = XmlBeanMapper.parse();
        JasperReport report = JasperCompileManager.compileReport(FileProvider.readJRXMLForBean());
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(holidayList);

        JasperPrint print = JasperFillManager.fillReport(report, null, source);
        JasperExportManager.exportReportToPdfFile(print, ExportProvider.exportToDesktop(dirName)+"/"+filename);
    }

    public static void main(String[] ar) throws JRException, IOException {
        generate("beanReport.pdf", "test");
    }
}
